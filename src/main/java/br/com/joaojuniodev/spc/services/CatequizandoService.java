package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.request.CatequizandoRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.CatequizandoResponseDTO;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.repositories.CatequizandoRepository;
import br.com.joaojuniodev.spc.repositories.EtapaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.joaojuniodev.spc.mapper.ObjectMapperManually.convertCatequizandoEntityToResponseDTO;
import static br.com.joaojuniodev.spc.mapper.ObjectMapperManually.convertCatequizandoRequestToEntity;

@Service
public class CatequizandoService {

    private final Logger logger = LoggerFactory.getLogger(CatequizandoService.class.getName());

    @Autowired
    private CatequizandoRepository repository;

    @Autowired
    private EtapaRepository etapaRepository;

    public List<CatequizandoResponseDTO> findAll() {

        logger.info("Finding All Catequizandos");

        return repository.findAll()
            .stream()
            .map(ObjectMapperManually::convertCatequizandoEntityToResponseDTO).toList();
    }

    public CatequizandoResponseDTO findById(Long id) {

        logger.info("Finding By Id Catequizandos");

        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        return convertCatequizandoEntityToResponseDTO(entity);
    }

    public CatequizandoResponseDTO create(CatequizandoRequestDTO catequizando) {

        logger.info("Creating Catequizando");

        return convertCatequizandoEntityToResponseDTO(
            repository.save(convertCatequizandoRequestToEntity(catequizando))
        );
    }

    public CatequizandoResponseDTO update(CatequizandoRequestDTO catequizando) {

        logger.info("Updating Catequizando");

        var etapa = catequizando.getEtapaId() != null
            ? etapaRepository.findById(catequizando.getEtapaId()).orElseThrow()
            : null;

        var entity = repository.findById(catequizando.getId())
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + catequizando.getId()));
        entity.setFullName(catequizando.getFullName());

        if (etapa != null) entity.setEtapa(etapa);

        return convertCatequizandoEntityToResponseDTO(
            repository.save(convertCatequizandoRequestToEntity(catequizando))
        );
    }

    public void delete(Long id) {

        logger.info("Deleting By Id Catequizando");

        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        repository.delete(entity);
    }
}
