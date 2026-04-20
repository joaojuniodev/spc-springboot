package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.request.CatechistRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechist.CatechistResponseDTO;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.models.Catechist;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.repositories.CatechistRepository;
import br.com.joaojuniodev.spc.repositories.specs.CatechistSpecification;
import br.com.joaojuniodev.spc.repositories.specs.CatechumenSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CatechistService {

    private final Logger logger = LoggerFactory.getLogger(CatechistService.class.getName());

    @Autowired
    private CatechistRepository repository;

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

        return this.mapper.convertCatechistEntityToResponseDTO(
            this.repository.save(this.mapper.convertCatechistRequestToEntity(catechist))
        );
    }

    public void delete(Long id) {

        logger.info("Deleting By Id Catechist");

        var entity = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        this.repository.delete(entity);
    }
}
