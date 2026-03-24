package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.request.PresencaRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.PresencaResponseDTO;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.models.Missa;
import br.com.joaojuniodev.spc.repositories.CatequizandoRepository;
import br.com.joaojuniodev.spc.repositories.MissaRepository;
import br.com.joaojuniodev.spc.repositories.PresencaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresencaService {
    //testando

    private final Logger logger = LoggerFactory.getLogger(CatequizandoService.class.getName());

    @Autowired
    private PresencaRepository repositoryyyyyyyyyyyy;

    @Autowired
    private CatequizandoRepository catequizandoRepository;

    @Autowired
    private MissaRepository missaRepository;

    @Autowired
    private ObjectMapperManually mapper;

    public List<PresencaResponseDTO> findAll() {

        logger.info("Finding All Presencas");

        return repository.findAll()
            .stream()
            .map(entity -> mapper.convertPresencaEntityToResponseDTO(entity)).toList();
    }

    public PresencaResponseDTO findById(Long id) {

        logger.info("Finding By Id Presenca");

        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        return mapper.convertPresencaEntityToResponseDTO(entity);
    }

    public List<PresencaResponseDTO> findByCatechumenId(Long catechumenId) {

        logger.info("Finding Presences by catechumentId");

        return repository.findByCatechumenId(catechumenId)
            .stream()
            .map(entity -> mapper.convertPresencaEntityToResponseDTO(entity)).toList();
    }

    public PresencaResponseDTO create(PresencaRequestDTO presenca) {

        logger.info("Creating Presenca");

        var entity = mapper.convertPresencaRequestToEntity(presenca);

        if (repository.existsByMissaIdAndCatequizandoId(entity.getMissa().getId(), entity.getCatequizando().getId())) {
            throw new RuntimeException("Presença já registrada.");
        }

        var savedPresence = repository.save(entity);

        Missa massWithRegisteredPresence = missaRepository.findById(presenca.getMissaId()).get();
        massWithRegisteredPresence.setRegisteredAttendance(true);
        missaRepository.save(massWithRegisteredPresence);

        return mapper.convertPresencaEntityToResponseDTO(savedPresence);
    }

    public PresencaResponseDTO update(PresencaRequestDTO presenca) {

        logger.info("Updating Presenca");

        var catequizando = presenca.getCatequizandoId() != null
            ? catequizandoRepository.findById(presenca.getCatequizandoId()).orElseThrow()
            : null;

        var missa = presenca.getCatequizandoId() != null
            ? missaRepository.findById(presenca.getMissaId()).orElseThrow()
            : null;

        var entity = repository.findById(presenca.getId())
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + presenca.getId()));
        entity.setCatequizando(catequizando);
        entity.setMissa(missa);
        entity.setJustification(presenca.getJustification());
        entity.setStatus(presenca.getStatus());

        return mapper.convertPresencaEntityToResponseDTO(
            repository.save(mapper.convertPresencaRequestToEntity(presenca))
        );
    }

    public void delete(Long id) {

        logger.info("Deleting By Id Presenca");

        var entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        repository.delete(entity);
    }
}
