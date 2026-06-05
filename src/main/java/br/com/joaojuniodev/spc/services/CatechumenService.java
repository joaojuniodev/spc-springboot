package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.controllers.CatechumenController;
import br.com.joaojuniodev.spc.data.dtos.request.catechumen.CatechumenRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.request.catechumen.ParamsAPI;
import br.com.joaojuniodev.spc.data.dtos.response.catechumen.CatechumenDashboardResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechumen.CatechumenResponseDTO;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.models.Catechumen;
import br.com.joaojuniodev.spc.models.Presence;
import br.com.joaojuniodev.spc.repositories.CatechumenRepository;
import br.com.joaojuniodev.spc.repositories.LiturgicalCalendarRepository;
import br.com.joaojuniodev.spc.repositories.PresenceRepository;
import br.com.joaojuniodev.spc.repositories.StepRepository;
import br.com.joaojuniodev.spc.repositories.specs.CatechumenSpecification;
import br.com.joaojuniodev.spc.repositories.specs.PresenceSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatechumenService {

    private final Logger logger = LoggerFactory.getLogger(CatechumenService.class.getName());

    @Autowired
    private CatechumenRepository repository;

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private LiturgicalCalendarRepository liturgicalCalendarRepository;

    @Autowired
    private PresenceRepository presenceRepository;

    @Autowired
    private MassService massService;

    @Autowired
    private ObjectMapperManually mapper;

    @Autowired
    private PagedResourcesAssembler<CatechumenResponseDTO> assembler;

    public PagedModel<EntityModel<CatechumenResponseDTO>> filter(ParamsAPI paramsAPI, Pageable pageable) {

        logger.info("Filtering Catechumens by Pageable");

        CatechumenSpecification spec = new CatechumenSpecification();
        spec.addToSpecifications(paramsAPI.getFullName(), paramsAPI.getStepId(), paramsAPI.getCatechistId(), paramsAPI.getCommunityOrParish());

        var catechumens = this.repository.findAll(spec.apply(), pageable);
        return buildPagedModel(paramsAPI, pageable, catechumens);
    }

    public CatechumenDashboardResponseDTO retrieveDashboard() {
        logger.info("Retrieve Dashboard by Catechumens");

        var numberOfMasses = massService.getNumberOfMasses();
        var catechumens = this.repository.findAll()
            .stream()
            .map(catechumen -> {
                var dto = this.mapper.convertCatechumenEntityToResponseDTO(catechumen);
                processFrequency(dto);
                return dto;
            })
            .toList();

        final Integer totalCatechumens = catechumens.size();
        final Double mediumFrequency = calculateMediumFrequency(catechumens);
        final Integer inAttention = catechumensInAttention(catechumens);
        final Integer totalMasses = numberOfMasses.getTotalMasses();
        final Integer massesOccurred = numberOfMasses.getTotalMassesToThisToday();

        return new CatechumenDashboardResponseDTO(totalCatechumens, mediumFrequency, inAttention, totalMasses, massesOccurred);
    }

    public CatechumenResponseDTO getById(Long id) {

        logger.info("Finding By Id Catechumen");

        var entity = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        return processFrequency(this.mapper.convertCatechumenEntityToResponseDTO(entity));
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

    public CatechumenResponseDTO create(CatechumenRequestDTO catechumen) {

        logger.info("Creating Catechumen");

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

    private PagedModel<EntityModel<CatechumenResponseDTO>> buildPagedModel(ParamsAPI params, Pageable pageable, Page<Catechumen> catechumens) {
        Page<CatechumenResponseDTO> dtos = catechumens.map(catechumen -> {
            var dto = this.mapper.convertCatechumenEntityToResponseDTO(catechumen);
            processFrequency(dto);
            return dto;
        });

        Link filterAllLink = WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(CatechumenController.class)
                .getAll(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    String.valueOf(pageable.getSort()),
                    params.getFullName(),
                    params.getStepId(),
                    params.getCatechistId(),
                    params.getCommunityOrParish()
                ))
            .withSelfRel();

        return assembler.toModel(dtos, filterAllLink);
    }

    private CatechumenResponseDTO processFrequency(CatechumenResponseDTO catechumen) {

        logger.info("Process Frequency");

        var totalDatesOfLiturgicalCalendar = liturgicalCalendarRepository.findAll();

        int totalMasses = massService.getTotalMasses(totalDatesOfLiturgicalCalendar);
        int totalMassesToThisToday = massService.getMassesToThisToday(totalDatesOfLiturgicalCalendar);
        List<Presence> presencesOfCatechumen = getPresencesOfCatechumen(catechumen);

        var presencesAtMasses = presencesOfCatechumen.toArray().length;

        if (totalMasses < 1 || presencesAtMasses < 1) {
            catechumen.setCurrentFrequency(0);
            catechumen.setTotalFrequency(0);
        }

        logger.info("Processing Frequency of Catechumen");

        final int CURRENT_FREQUENCY = (presencesAtMasses * 100) / totalMassesToThisToday;
        final int TOTAL_FREQUENCY = (presencesAtMasses * 100) / totalMasses;
        final int ABSENCES = totalMassesToThisToday - presencesAtMasses;

        catechumen.setCurrentFrequency(CURRENT_FREQUENCY);
        catechumen.setTotalFrequency(TOTAL_FREQUENCY);
        catechumen.setPresences(presencesAtMasses);
        catechumen.setAbsences(ABSENCES);

        return catechumen;
    }

    private List<Presence> getPresencesOfCatechumen(CatechumenResponseDTO catechumen) {
        PresenceSpecification specPresence = new PresenceSpecification();
        specPresence.addToSpecifications(catechumen.getId(), null, null, null);

        return presenceRepository.findAll(specPresence.apply());
    }

    private Double calculateMediumFrequency(List<CatechumenResponseDTO> catechumens) {
        double frequency = 0;
        for (CatechumenResponseDTO catechumen : catechumens) {
            frequency += catechumen.getCurrentFrequency();
        }
        return frequency / catechumens.size();
    }

    private Integer catechumensInAttention(List<CatechumenResponseDTO> catechumens) {
        int inAttention = 0;
        for (CatechumenResponseDTO catechumen : catechumens) {
            if (catechumen.getCurrentFrequency() < 50) {
                inAttention++;
            }
        }
        return inAttention;
    }
}
