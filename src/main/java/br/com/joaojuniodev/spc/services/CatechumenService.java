package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.request.CatechumenRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechumens.CatechumenResponseDTO;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.models.Catechumen;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.repositories.CatechumenRepository;
import br.com.joaojuniodev.spc.repositories.StepRepository;
import br.com.joaojuniodev.spc.repositories.specs.CatechumenSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CatechumenService {

    private final Logger logger = LoggerFactory.getLogger(CatechumenService.class.getName());

    @Autowired
    private CatechumenRepository repository;

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private ObjectMapperManually mapper;

    public List<CatechumenResponseDTO> filter(String fullName, Long stepId, Long catechistId, NameOfTheCommunityOrParishEnum communityOrParish) {

        logger.info("Filtering Catechumens");

        CatechumenSpecification spec = new CatechumenSpecification();
        spec.addToSpecifications(fullName, stepId, catechistId, communityOrParish);

        return this.repository
            .findAll(spec.apply())
            .stream()
            .map(this.mapper::convertCatechumenEntityToResponseDTO)
            .toList();
    }

    @Transactional
    public CatechumenResponseDTO getById(Long id) {

        logger.info("Finding By Id Catechumen");

        var entity = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        return this.mapper.convertCatechumenEntityToResponseDTO(entity);
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
