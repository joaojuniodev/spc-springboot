package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.request.StepRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.step.StepResponseDTO;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.models.Catechist;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.repositories.CatechistRepository;
import br.com.joaojuniodev.spc.repositories.StepRepository;
import br.com.joaojuniodev.spc.repositories.specs.StepSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StepService {

    private final Logger logger = LoggerFactory.getLogger(StepService.class.getName());

    @Autowired
    private StepRepository repository;

    @Autowired
    private CatechistRepository catechistRepository;

    @Autowired
    private ObjectMapperManually mapper;

    public List<StepResponseDTO> filter(NameOfTheCommunityOrParishEnum communityOrParish) {

        logger.info("Filtering All Steps");

        StepSpecification spec = new StepSpecification();
        spec.addToSpecifications(communityOrParish);

        return this.repository
            .findAll(spec.apply())
            .stream()
            .map(this.mapper::convertStepEntityToResponseDTO)
            .toList();
    }

    public StepResponseDTO getById(Long id) {

        logger.info("Finding By Id Step");

        var entity = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        return this.mapper.convertStepEntityToResponseDTO(entity);
    }

    public StepResponseDTO create(StepRequestDTO step) {

        logger.info("Creating Step");

        return this.mapper.convertStepEntityToResponseDTO(
            this.repository.save(this.mapper.convertStepRequestToEntity(step))
        );
    }

    public StepResponseDTO update(StepRequestDTO step) {

        logger.info("Updating Step");

        Set<Catechist> catechists = new HashSet<>();

        if (step.getCatechistsId().length > 0) {
            Arrays.stream(step.getCatechistsId()).map(id ->  catechists.add(catechistRepository.findById(id).orElseThrow()));
        }

        var entity = this.repository.findById(step.getId())
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + step.getId()));
        entity.setCatechists(catechists);

        return this.mapper.convertStepEntityToResponseDTO(this.repository.save(entity));
    }

    public void delete(Long id) {

        logger.info("Deleting By Id Step");

        var entity = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        this.repository.delete(entity);
    }
}
