package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.request.CatequistaRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechist.CatequistaResponseDTO;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.repositories.CatequistaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatequistaService {

    private final Logger logger = LoggerFactory.getLogger(CatequistaService.class.getName());

    @Autowired
    private CatequistaRepository repository;

    @Autowired
    private ObjectMapperManually mapper;

    public List<CatequistaResponseDTO> findAll() {

        logger.info("Finding All Catequistas");

        return repository.findAll()
            .stream()
            .map(entity -> mapper.convertCatequistaEntityToResponseDTO(entity)).toList();
    }

    public CatequistaResponseDTO findById(Long id) {

        logger.info("Finding By Id Catequistas");

        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        return mapper.convertCatequistaEntityToResponseDTO(entity);
    }

    public List<CatequistaResponseDTO> findByNameOfCommunityOrParish(NameOfTheCommunityOrParishEnum communityOrParish) {

        logger.info("Finding Catechists by name of community or parish");

        return repository.findByNameCommunityOrParish(communityOrParish)
            .stream()
            .map(entity -> mapper.convertCatequistaEntityToResponseDTO(entity)).toList();
    }

    public CatequistaResponseDTO create(CatequistaRequestDTO catequista) {

        logger.info("Creating Catequista");

        return mapper.convertCatequistaEntityToResponseDTO(
            repository.save(mapper.convertCatequistaRequestToEntity(catequista))
        );
    }

    public CatequistaResponseDTO update(CatequistaRequestDTO catequista) {

        logger.info("Updating Catequista");

        var entity = repository.findById(catequista.getId())
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + catequista.getId()));
        entity.setFirstName(catequista.getFirstName());
        entity.setLastName(catequista.getLastName());

        return mapper.convertCatequistaEntityToResponseDTO(
            repository.save(mapper.convertCatequistaRequestToEntity(catequista))
        );
    }

    public void delete(Long id) {

        logger.info("Deleting By Id Catequistas");

        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        repository.delete(entity);
    }
}
