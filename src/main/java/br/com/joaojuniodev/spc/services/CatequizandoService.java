package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.request.CatequizandoRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.CatequizandoResponseDTO;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.models.enums.EtapaEnum;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.repositories.CatequizandoRepository;
import br.com.joaojuniodev.spc.repositories.EtapaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CatequizandoService {

    private final Logger logger = LoggerFactory.getLogger(CatequizandoService.class.getName());

    @Autowired
    private CatequizandoRepository repository;

    @Autowired
    private EtapaRepository etapaRepository;

    @Autowired
    private ObjectMapperManually mapper;

    @Transactional
    public List<CatequizandoResponseDTO> findAll() {

        logger.info("Finding All Catequizandos");

        return repository.findAll()
            .stream()
            .map(entity -> mapper.convertCatequizandoEntityToResponseDTO(entity)).toList();
    }

    @Transactional
    public CatequizandoResponseDTO findById(Long id) {

        logger.info("Finding By Id Catequizandos");

        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        return mapper.convertCatequizandoEntityToResponseDTO(entity);
    }

    @Transactional
    public List<CatequizandoResponseDTO> findByNameOfCommunityOrParish(NameOfTheCommunityOrParishEnum communityOrParish) {

        logger.info("Finding Catechumens by name of community or parish");

        return repository.findByNameCommunityOrParish(communityOrParish)
            .stream()
            .map(entity -> mapper.convertCatequizandoEntityToResponseDTO(entity)).toList();
    }

    @Transactional
    public List<CatequizandoResponseDTO> findByEtapaId(Long etapaId) {

        logger.info("Finding Catequizandos by etapaId");

        return repository.findByEtapaId(etapaId).get()
            .stream()
            .map(entity -> mapper.convertCatequizandoEntityToResponseDTO(entity)).toList();
    }

    @Transactional
    public List<CatequizandoResponseDTO> findByCatechistAndStep(String catechistName, EtapaEnum stepEnum) {

        logger.info("Finding Catechumens by catechistName and stepEnum");

        return repository.findByCatechistNameAndStep(catechistName, stepEnum)
            .stream()
            .map(entity -> mapper.convertCatequizandoEntityToResponseDTO(entity)).toList();
    }

    @Transactional
    public List<CatequizandoResponseDTO> searchByFirstName(String fullName) {

        logger.info("Searching Catequizando by FullName");

        return repository.searchByFirstName(fullName)
            .stream()
            .map(entity -> mapper.convertCatequizandoEntityToResponseDTO(entity)).toList();
    }

    public CatequizandoResponseDTO create(CatequizandoRequestDTO catequizando) {

        logger.info("Creating Catequizando");

        return mapper.convertCatequizandoEntityToResponseDTO(
            repository.save(mapper.convertCatequizandoRequestToEntity(catequizando))
        );
    }

    public CatequizandoResponseDTO update(CatequizandoRequestDTO catequizando) {

        logger.info("Updating Catequizando");

        var etapa = catequizando.getEtapaId() != null
            ? etapaRepository.findById(catequizando.getEtapaId()).orElseThrow()
            : null;

        var entity = repository.findById(catequizando.getId())
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + catequizando.getId()));
        entity.setFirstName(catequizando.getFirstName());
        entity.setLastName(catequizando.getLastName());

        if (etapa != null) entity.setEtapa(etapa);

        return mapper.convertCatequizandoEntityToResponseDTO(
            repository.save(mapper.convertCatequizandoRequestToEntity(catequizando))
        );
    }

    public void delete(Long id) {

        logger.info("Deleting By Id Catequizando");

        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        repository.delete(entity);
    }
}
