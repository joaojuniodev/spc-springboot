package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.request.CatechumenRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechumens.CatechumenResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.PresenceResponseDTO;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.models.Catechumen;
import br.com.joaojuniodev.spc.models.LiturgicalCalendar;
import br.com.joaojuniodev.spc.models.Mass;
import br.com.joaojuniodev.spc.models.Presence;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.repositories.*;
import br.com.joaojuniodev.spc.repositories.specs.CatechumenSpecification;
import br.com.joaojuniodev.spc.repositories.specs.MassSpecification;
import br.com.joaojuniodev.spc.repositories.specs.PresenceSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CatechumenService {

    private final Logger logger = LoggerFactory.getLogger(CatechumenService.class.getName());

    @Autowired
    private CatechumenRepository repository;

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private MassRepository massRepository;

    @Autowired
    private LiturgicalCalendarRepository liturgicalCalendarRepository;

    @Autowired
    private PresenceRepository presenceRepository;

    @Autowired
    private ObjectMapperManually mapper;

    public List<CatechumenResponseDTO> filter(String fullName, Long stepId, Long catechistId, NameOfTheCommunityOrParishEnum communityOrParish) {

        logger.info("Filtering Catechumens");

        CatechumenSpecification spec = new CatechumenSpecification();
        spec.addToSpecifications(fullName, stepId, catechistId, communityOrParish);

        return this.repository
            .findAll(spec.apply())
            .stream()
            .map(catechumen -> {
                var dto = this.mapper.convertCatechumenEntityToResponseDTO(catechumen);
                processFrequency(dto);
                return dto;
            })
            .toList();
    }

    public CatechumenResponseDTO getById(Long id) {

        logger.info("Finding By Id Catechumen");

        var entity = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        return processFrequency(this.mapper.convertCatechumenEntityToResponseDTO(entity));
    }

    private CatechumenResponseDTO processFrequency(CatechumenResponseDTO catechumen) {

        logger.info("Process Frequency");

        var totalDatesOfLiturgicalCalendar = liturgicalCalendarRepository.findAll();

        int totalMasses = getTotalMasses(totalDatesOfLiturgicalCalendar);
        int totalMassesToThisToday = getMassesToThisToday(totalDatesOfLiturgicalCalendar);
        List<Presence> presencesOfCatechumen = getPresencesOfCatechumen(catechumen);

        var presencesAtMasses = presencesOfCatechumen.toArray().length;

        if (totalMasses < 1 || presencesAtMasses < 1) {
            catechumen.setCurrentFrequency(0);
            catechumen.setTotalFrequency(0);
        }

        logger.info("Processing Frequency of Catechumen");

        int CURRENT_FREQUENCY = (presencesAtMasses * 100) / totalMassesToThisToday;
        int TOTAL_FREQUENCY = (presencesAtMasses * 100) / totalMasses;

        catechumen.setCurrentFrequency(CURRENT_FREQUENCY);
        catechumen.setTotalFrequency(TOTAL_FREQUENCY);

        return catechumen;
    }

    private List<Presence> getPresencesOfCatechumen(CatechumenResponseDTO catechumen) {
        PresenceSpecification specPresence = new PresenceSpecification();
        specPresence.addToSpecifications(catechumen.getId(), null);

        return presenceRepository.findAll(specPresence.apply());
    }

    private Integer getTotalMasses(List<LiturgicalCalendar> totalDatesOfLiturgicalCalendar) {
        List<Mass> masses = massRepository.findAll();
        return returnedMassLength(masses, totalDatesOfLiturgicalCalendar);
    }

    private Integer getMassesToThisToday(List<LiturgicalCalendar> totalDatesOfLiturgicalCalendar) {
        var today = LocalDateTime.now();

        MassSpecification specMass = new MassSpecification();
        specMass.addToSpecifications(null, today);

        List<Mass> masses = massRepository.findAll(specMass.apply());
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

    public CatechumenResponseDTO create(CatechumenRequestDTO catechumen) {

        logger.info("Creating Catechumen");

        return this.mapper.convertCatechumenEntityToResponseDTO(
            this.repository.save(this.mapper.convertCatechumenRequestToEntity(catechumen))
        );
    }

    public CatechumenResponseDTO update(CatechumenRequestDTO catechumen) {

        logger.info("Updating Catechumen");

        var step = catechumen.getStepId() != null
            ? this.stepRepository.findById(catechumen.getStepId()).orElseThrow()
            : null;

        var entity = this.repository.findById(catechumen.getId())
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + catechumen.getId()));
        entity.setFirstName(catechumen.getFirstName());
        entity.setLastName(catechumen.getLastName());

        if (step != null) entity.setStep(step);

        return this.mapper.convertCatechumenEntityToResponseDTO(
            this.repository.save(this.mapper.convertCatechumenRequestToEntity(catechumen))
        );
    }

    public void delete(Long id) {

        logger.info("Deleting By Id Catechumen");

        var entity = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        this.repository.delete(entity);
    }
}
