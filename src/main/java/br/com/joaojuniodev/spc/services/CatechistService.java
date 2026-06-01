package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.request.catechist.CatechistRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechist.CatechistResponseDTO;
import br.com.joaojuniodev.spc.exceptions.ConflictInTheDatabaseException;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.models.Step;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.repositories.CatechistRepository;
import br.com.joaojuniodev.spc.repositories.PresenceRepository;
import br.com.joaojuniodev.spc.repositories.StepRepository;
import br.com.joaojuniodev.spc.repositories.specs.CatechistSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatechistService {

    private final Logger logger = LoggerFactory.getLogger(CatechistService.class.getName());

    @Autowired
    private CatechistRepository repository;

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private PresenceRepository presenceRepository;

    @Autowired
    private ObjectMapperManually mapper;

    public List<CatechistResponseDTO> filter(Long stepId, String fullName, NameOfTheCommunityOrParishEnum communityOrParish) {

        logger.info("Filtering Catechists");

        CatechistSpecification spec = new CatechistSpecification();
        spec.addToSpecifications(stepId, fullName, communityOrParish);

        return this.repository
            .findAll(spec.apply())
            .stream()
            .map(this.mapper::convertCatechistEntityToResponseDTO)
            .toList();
    }

    public CatechistResponseDTO getById(Long id) {

        logger.info("Finding By Id Catechist");

        var entity = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        return this.mapper.convertCatechistEntityToResponseDTO(entity);
    }

    public CatechistResponseDTO create(CatechistRequestDTO catechist) {

        logger.info("Creating Catechist");

        if (catechist.getStep().getId() == null) {
            Step stepCreated = stepRepository.save(this.mapper.convertStepRequestToEntity(catechist.getStep()));
            catechist.getStep().setId(stepCreated.getId());
        }

        return this.mapper.convertCatechistEntityToResponseDTO(
            this.repository.save(this.mapper.convertCatechistRequestToEntity(catechist))
        );
    }

    public CatechistResponseDTO update(CatechistRequestDTO catechist) {

        logger.info("Updating Catechist");

        var entity = repository.findById(catechist.getId())
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + catechist.getId()));
        entity.setFirstName(catechist.getFirstName());
        entity.setLastName(catechist.getLastName());
        entity.setNameCommunityOrParish(catechist.getCommunityOrParish());

        if (catechist.getStep() != null) {
            Step step = stepRepository.findById(catechist.getStep().getId()).orElseThrow();
            if (catechist.getStep().getStepName() != null) {
                step.setStepName(catechist.getStep().getStepName());
            }
            if (catechist.getStep().getCommunityOrParish() != null) {
                step.setNameCommunityOrParish(catechist.getCommunityOrParish());
            }
            this.stepRepository.save(step);
        }

        return this.mapper.convertCatechistEntityToResponseDTO(
            this.repository.save(this.mapper.convertCatechistRequestToEntity(catechist))
        );
    }

    public void delete(Long id) {

        logger.info("Deleting By Id Catechist");

        if (this.presenceRepository.existsByUserId(id)) {
            throw new ConflictInTheDatabaseException(
                "User Id: " + id + ", has presences linked and cannot be deleted!"
            );
        }

        var entity = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));

        var stepId = entity.getStep().getId();

        var stepEntity = this.stepRepository.findById(stepId)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + stepId));

        this.repository.delete(entity);
        this.stepRepository.delete(stepEntity);
        this.repository.flush();
    }
}
