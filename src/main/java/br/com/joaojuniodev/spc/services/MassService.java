package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.request.MassRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.mass.MassResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.mass.NumberOfMassesDTO;
import br.com.joaojuniodev.spc.exceptions.ConflictInTheDatabaseException;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.models.LiturgicalCalendar;
import br.com.joaojuniodev.spc.models.Mass;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.repositories.LiturgicalCalendarRepository;
import br.com.joaojuniodev.spc.repositories.MassRepository;
import br.com.joaojuniodev.spc.repositories.PresenceRepository;
import br.com.joaojuniodev.spc.repositories.specs.MassSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MassService {

    private final Logger logger = LoggerFactory.getLogger(MassService.class.getName());

    @Autowired
    private MassRepository repository;

    @Autowired
    private PresenceRepository presenceRepository;

    @Autowired
    private LiturgicalCalendarRepository liturgicalCalendarRepository;

    @Autowired
    private ObjectMapperManually mapper;

    public List<MassResponseDTO> filter(NameOfTheCommunityOrParishEnum communityOrParish, String title, LocalDateTime occurredUntil) {

        logger.info("Filtering All Masses");

        MassSpecification spec = new MassSpecification();
        spec.addToSpecifications(communityOrParish, title, occurredUntil);

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

        if (communityOrParish != null) {
            return this.repository.findAllMassesDatesByCommunityOrParish(communityOrParish);
        }

        return this.repository.findAllMassesDates();
    }

    public NumberOfMassesDTO getNumberOfMasses() {
        logger.info("Finding for number of Masses");

        var totalDatesOfLiturgicalCalendar = liturgicalCalendarRepository.findAll();

        var totalMasses = getTotalMasses(totalDatesOfLiturgicalCalendar);
        var totalMassesToThisToday = getMassesToThisToday(totalDatesOfLiturgicalCalendar);
        return new NumberOfMassesDTO(totalMasses, totalMassesToThisToday);
    }

    public Integer getTotalMasses(List<LiturgicalCalendar> totalDatesOfLiturgicalCalendar) {
        List<Mass> masses = repository.findAll();
        return returnedMassLength(masses, totalDatesOfLiturgicalCalendar);
    }

    public Integer getMassesToThisToday(List<LiturgicalCalendar> totalDatesOfLiturgicalCalendar) {
        var today = LocalDateTime.now();

        MassSpecification specMass = new MassSpecification();
        specMass.addToSpecifications(null, null, today);

        List<Mass> masses = repository.findAll(specMass.apply());
        return returnedMassLength(masses, totalDatesOfLiturgicalCalendar);
    }

    private int returnedMassLength(List<Mass> masses, List<LiturgicalCalendar> totalDatesOfLiturgicalCalendar) {
        var counterMass = new ArrayList<Long>();

        for (Mass mass : masses) {
            for (LiturgicalCalendar liturgicalCalendar : totalDatesOfLiturgicalCalendar) {
                if (Objects.equals(liturgicalCalendar.getId(), mass.getMassOfLiturgicalCalendar().getId())) {
                    if (!counterMass.contains(liturgicalCalendar.getId())) {
                        counterMass.add(liturgicalCalendar.getId());
                    }
                }
            }
        }

        return counterMass.toArray().length;
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

        var existsPresenceByMassId = presenceRepository.existsByMassId(id);

        if (existsPresenceByMassId) {
            throw new ConflictInTheDatabaseException("Existem presenças registradas a essa Missa");
        }

        var entity = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        this.repository.delete(entity);
    }
}