package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.request.MassRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.mass.MassResponseDTO;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.models.Mass;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.repositories.LiturgicalCalendarRepository;
import br.com.joaojuniodev.spc.repositories.MassRepository;
import br.com.joaojuniodev.spc.repositories.specs.MassSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MassService {

    private final Logger logger = LoggerFactory.getLogger(MassService.class.getName());

    @Autowired
    private MassRepository repository;

    @Autowired
    private LiturgicalCalendarRepository liturgicalCalendarRepository;

    @Autowired
    private ObjectMapperManually mapper;

    public List<MassResponseDTO> filter(NameOfTheCommunityOrParishEnum communityOrParish, LocalDateTime occurredUntil) {

        logger.info("Filtering All Masses");

        MassSpecification spec = new MassSpecification();
        spec.addToSpecifications(communityOrParish, occurredUntil);

        return this.repository
            .findAll(spec.apply())
            .stream()
            .map(this.mapper::convertMassEntityToResponseDTO)
            .toList();
    }

    public MassResponseDTO getById(Long id) {

        logger.info("Finding by Id Mass");

        var entity = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        return this.mapper.convertMassEntityToResponseDTO(entity);
    }

    public List<LocalDateTime> getMassesDatesByCommunityOrParish(NameOfTheCommunityOrParishEnum communityOrParish) {

        logger.info("Finding Masses Dates by Community or Parish");

        return this.repository.findAllMassesDates(communityOrParish);
    }

    public MassResponseDTO create(MassRequestDTO mass) {

        logger.info("Creating Mass");

        LocalDateTime dateTimeThisCreatedMass = LocalDateTime.parse(mass.getDateTime());

        for (Mass m : this.repository.findAll()) {
            if (m.getDateTime().equals(dateTimeThisCreatedMass) && m.getLocation().equals(mass.getLocation())) {
                throw new RuntimeException("Já existe missa neste dia e horário");
            }
        }

        return this.mapper.convertMassEntityToResponseDTO(
            this.repository.save(this.mapper.convertMassRequestToEntity(mass))
        );
    }

    public MassResponseDTO update(MassRequestDTO mass) {

        logger.info("Updating Mass");

        var entity = this.repository.findById(mass.getId())
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + mass.getId()));

        var massOfLiturgicalCalendar = this.liturgicalCalendarRepository.findById(mass.getMassOfLiturgicalCalendarId())
            .orElseThrow(() -> new RuntimeException("Not found Mass of Liturgical Calendar this ID: " + mass.getId()));

        entity.setTitle(massOfLiturgicalCalendar.getTitle());
        entity.setDateTime(LocalDateTime.parse(mass.getDateTime()));

        return this.mapper.convertMassEntityToResponseDTO(this.repository.save(entity));
    }

    public void delete(Long id) {

        logger.info("Deleting By Id Mass");

        var entity = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        this.repository.delete(entity);
    }
}