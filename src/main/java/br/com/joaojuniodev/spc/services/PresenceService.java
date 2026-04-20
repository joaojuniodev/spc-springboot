package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.request.PresenceRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.CatechumenIsPresentDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.PresenceResponseDTO;
import br.com.joaojuniodev.spc.exceptions.ConflicWhenSavingInTheDatabaseException;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.models.Mass;
import br.com.joaojuniodev.spc.models.Presence;
import br.com.joaojuniodev.spc.repositories.CatechistRepository;
import br.com.joaojuniodev.spc.repositories.CatechumenRepository;
import br.com.joaojuniodev.spc.repositories.MassRepository;
import br.com.joaojuniodev.spc.repositories.PresenceRepository;
import br.com.joaojuniodev.spc.repositories.specs.PresenceSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresenceService {

    private final Logger logger = LoggerFactory.getLogger(PresenceService.class.getName());

    @Autowired
    private PresenceRepository repository;

    @Autowired
    private CatechumenRepository catechumenRepository;

    @Autowired
    private CatechistRepository catechistRepository;

    @Autowired
    private MassRepository massRepository;

    @Autowired
    private ObjectMapperManually mapper;

    public List<PresenceResponseDTO> filter(Long catechumenId, String titleMass) {

        logger.info("Filtering All Presences");

        PresenceSpecification spec = new PresenceSpecification();
        spec.addToSpecifications(catechumenId, titleMass);

        return this.repository
            .findAll(spec.apply())
            .stream()
            .map(this.mapper::convertPresenceEntityToResponseDTO)
            .toList();
    }

    public PresenceResponseDTO getById(Long id) {

        logger.info("Finding By Id Presence");

        var entity = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        return this.mapper.convertPresenceEntityToResponseDTO(entity);
    }

    public PresenceResponseDTO create(PresenceRequestDTO presence) {

        logger.info("Creating Presence");

        Presence entity = this.mapper.convertPresenceRequestToEntity(presence);

        if (this.repository.existsByMassIdAndCatechumenId(entity.getMass().getId(), entity.getCatechumen().getId())) {
            throw new ConflicWhenSavingInTheDatabaseException("Catechumen Id: " + entity.getCatechumen().getId() + ", has already been saved!");
        }

        var savedPresence = this.repository.save(entity);

        Mass massWithRegisteredPresence = massRepository.findById(presence.getMassId()).get();
        massWithRegisteredPresence.setRegisteredAttendance(true);
        massRepository.save(massWithRegisteredPresence);

        return this.mapper.convertPresenceEntityToResponseDTO(savedPresence);
    }

    public PresenceResponseDTO update(PresenceRequestDTO presence) {

        logger.info("Updating Presence");

        var catechumen = presence.getCatechumenId() != null
            ? catechumenRepository.findById(presence.getCatechumenId()).orElseThrow()
            : null;

        var mass = presence.getMassId() != null
            ? massRepository.findById(presence.getMassId()).orElseThrow()
            : null;

        var entity = this.repository.findById(presence.getId())
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + presence.getId()));
        entity.setCatechumen(catechumen);
        entity.setMass(mass);
        entity.setJustification(presence.getJustification());
        entity.setStatus(presence.getStatus());

        return this.mapper.convertPresenceEntityToResponseDTO(this.repository.save(entity));
    }

    public void delete(Long id) {

        logger.info("Deleting By Id Presence");

        var entity = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found this ID: " + id));
        this.repository.delete(entity);
    }
}
