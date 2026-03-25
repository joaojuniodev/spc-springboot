package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.request.MissaRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.EtapaResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.MissaResponseDTO;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.models.LiturgicalCalendar;
import br.com.joaojuniodev.spc.models.Missa;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.repositories.LiturgicalCalendarRepository;
import br.com.joaojuniodev.spc.repositories.MissaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MissaService {

    private final Logger logger = LoggerFactory.getLogger(CatequizandoService.class.getName());

    @Autowired
    private MissaRepository repository;

    @Autowired
    private LiturgicalCalendarRepository liturgicalCalendarRepository;

    @Autowired
    private ObjectMapperManually mapper;

    public List<MissaResponseDTO> findAll() {

        logger.info("Finding All Missas");

        return repository.findAll()
            .stream()
            .map(entity -> mapper.convertMissaEntityToResponseDTO(entity)).toList();
    }

    public MissaResponseDTO findById(Long id) {

        logger.info("Finding By Id Missa");

        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        return mapper.convertMissaEntityToResponseDTO(entity);
    }

    public List<MissaResponseDTO> findByOccurredToThisToday() {

        logger.info("Finding Masses by occurred to this today");

        LocalDateTime startDate = LocalDateTime.of(2026,1,1,0,0);
        LocalDateTime today = LocalDateTime.now();

        return repository.findByMassesOccurredToThisDay(startDate, today)
            .stream()
            .map(entity -> mapper.convertMissaEntityToResponseDTO(entity)).toList();
    }

    public List<MissaResponseDTO> findByNameOfCommunityOrParish(NameOfTheCommunityOrParishEnum communityOrParish) {

        logger.info("Finding Masses by name of community or parish");

        return repository.findByNameCommunityOrParish(communityOrParish)
            .stream()
            .map(entity -> mapper.convertMissaEntityToResponseDTO(entity)).toList();
    }

    public List<LocalDateTime> findAllMassesDates(NameOfTheCommunityOrParishEnum nameCommunityOrParish) {

        logger.info("Finding all Masses dates");

        return repository.findAllMassesDates(nameCommunityOrParish);
    }

    public MissaResponseDTO create(MissaRequestDTO missa) {

        logger.info("Creating Missa");

        LocalDateTime dateTimeThisCreatedMissa = LocalDateTime.parse(missa.getDateTime());

        for (Missa m : repository.findAll()) {
            if (m.getDateTime().equals(dateTimeThisCreatedMissa)) {
                throw new RuntimeException("Já existe missa neste dia e horário");
            }
        }

        return mapper.convertMissaEntityToResponseDTO(
            repository.save(mapper.convertMissaRequestToEntity(missa))
        );
    }

    public MissaResponseDTO update(MissaRequestDTO missa) {

        logger.info("Updating Missa");

        var entity = repository.findById(missa.getId())
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + missa.getId()));
        var massOfLiturgialCalendar = liturgicalCalendarRepository.findById(missa.getMassOfLiturgicalCalendarId())
            .orElseThrow(() -> new RuntimeException("Not found Mass of Liturgical Calendar this ID: " + missa.getId()));
        entity.setTitle(massOfLiturgialCalendar.getTitle());
        entity.setDateTime(LocalDateTime.parse(missa.getDateTime()));

        return mapper.convertMissaEntityToResponseDTO(
            repository.save(mapper.convertMissaRequestToEntity(missa))
        );
    }

    public void delete(Long id) {

        logger.info("Deleting By Id Missa");

        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        repository.delete(entity);
    }
}