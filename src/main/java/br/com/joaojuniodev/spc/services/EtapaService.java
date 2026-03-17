package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.request.EtapaRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.EtapaResponseDTO;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.repositories.CatequistaRepository;
import br.com.joaojuniodev.spc.repositories.EtapaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EtapaService {

    private final Logger logger = LoggerFactory.getLogger(CatequizandoService.class.getName());

    @Autowired
    private EtapaRepository repository;

    @Autowired
    private CatequistaRepository catequistaRepository;

    @Autowired
    private ObjectMapperManually mapper;

    @Transactional
    public List<EtapaResponseDTO> findAll() {

        logger.info("Finding All Etapas");

        return repository.findAll()
            .stream()
            .map(entity -> mapper.convertEtapaEntityToResponseDTO(entity)).toList();
    }

    @Transactional
    public EtapaResponseDTO findById(Long id) {

        logger.info("Finding By Id Etapa");

        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        return mapper.convertEtapaEntityToResponseDTO(entity);
    }

    @Transactional
    public EtapaResponseDTO findByCatechistId(Long catechistId) {

        logger.info("Finding Step by catechistId");

        var entity = repository.findByCatechistId(catechistId)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + catechistId));
        return mapper.convertEtapaEntityToResponseDTO(entity);
    }

    public EtapaResponseDTO create(EtapaRequestDTO etapa) {

        logger.info("Creating Etapa");

        return mapper.convertEtapaEntityToResponseDTO(
            repository.save(mapper.convertEtapaRequestToEntity(etapa))
        );
    }

    public EtapaResponseDTO update(EtapaRequestDTO etapa) {

        logger.info("Updating Etapa");

        var catequista = etapa.getCatequistaId() != null
            ? catequistaRepository.findById(etapa.getCatequistaId()).orElseThrow()
            : null;

        var entity = repository.findById(etapa.getId())
                .orElseThrow(() -> new RuntimeException("Not found this ID: " + etapa.getId()));
        if (catequista != null) entity.setCatequista(catequista);

        return mapper.convertEtapaEntityToResponseDTO(
            repository.save(mapper.convertEtapaRequestToEntity(etapa))
        );
    }

    public void delete(Long id) {

        logger.info("Deleting By Id Etapa");

        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        repository.delete(entity);
    }
}
