package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.request.PresenceRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.PresenceRegisterDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.PresenceRegisterRetroactiveDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.PresenceResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.PresenceUserSummaryDTO;
import br.com.joaojuniodev.spc.exceptions.ConflictInTheDatabaseException;
import br.com.joaojuniodev.spc.exceptions.NotFoundException;
import br.com.joaojuniodev.spc.exceptions.RequiredFieldsException;
import br.com.joaojuniodev.spc.mapper.ObjectMapperManually;
import br.com.joaojuniodev.spc.models.Catechumen;
import br.com.joaojuniodev.spc.models.Mass;
import br.com.joaojuniodev.spc.models.Presence;
import br.com.joaojuniodev.spc.models.User;
import br.com.joaojuniodev.spc.models.enums.PresenceStatusEnum;
import br.com.joaojuniodev.spc.repositories.CatechumenRepository;
import br.com.joaojuniodev.spc.repositories.MassRepository;
import br.com.joaojuniodev.spc.repositories.PresenceRepository;
import br.com.joaojuniodev.spc.repositories.specs.PresenceSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private MassRepository massRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ObjectMapperManually mapper;

    public List<PresenceResponseDTO> filter(Long catechumenId, Long massId, String titleMass, String fullNameCatechumen) {

        logger.info("Filtering All Presences");

        PresenceSpecification spec = new PresenceSpecification();
        spec.addToSpecifications(catechumenId, massId, titleMass, fullNameCatechumen);

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

    public PresenceRegisterDTO registerPresences(List<PresenceRequestDTO> presences) {

        logger.info("Registering Presences");

        presences.forEach(this::register);

        User user = getAuthenticatedUser();
        Integer numberOfCatechumens = presences.size();
        Long massId = presences.stream().findFirst().get().getMassId();

        notificationService.notifyPresenceRegistered(
            user.getUsername(),
            user.getFullName(),
            presences.size(),
            massId
        );

        return new PresenceRegisterDTO(user.getUsername(), user.getFullName(), numberOfCatechumens);
    }

    public PresenceRegisterRetroactiveDTO registerRetroactive(PresenceRequestDTO presence) {

        logger.info("Registering Presence retroactive");

        register(presence);

        User user = getAuthenticatedUser();

        Catechumen catechumen = catechumenRepository.findById(presence.getCatechumenId())
            .orElseThrow(() -> new NotFoundException("Catechumen ID: " + presence.getCatechumenId() + " not found"));
        Mass mass = massRepository.findById(presence.getMassId())
            .orElseThrow(() -> new NotFoundException("Mass ID: " + presence.getMassId() + " not found"));

        String catechumenFullName = catechumen.getFirstName() + " " + catechumen.getLastName();
        String massTitle = mass.getTitle();
        Long massId = mass.getId();

        notificationService.notifyPresenceRetroactiveRegistered(
            user.getUsername(),
            user.getFullName(),
            catechumenFullName,
            massTitle,
            massId
        );

        return new PresenceRegisterRetroactiveDTO(
            user.getUsername(),
            user.getFullName(),
            catechumenFullName,
            presence.getJustification()
        );
    }

    private void register(PresenceRequestDTO presence) {
        Presence entity = this.mapper.convertPresenceRequestToEntity(presence);

        verifyAttendanceExists(entity);
        ensureJustification(presence.getStatus(), presence.getJustification());

        this.repository.save(entity);

        markMassAsRegisteredAttendance(presence);
    }

    public List<PresenceUserSummaryDTO> getSummaryByMass(Long massId) {

        logger.info("Finding User Summary by Mass");

        return this.repository.findUserSummaryByMassId(massId);
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

    private User getAuthenticatedUser() {
        return (User) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();
    }

    private void verifyAttendanceExists(Presence entity) {
        if (this.repository.existsByMassIdAndCatechumenId(entity.getMass().getId(), entity.getCatechumen().getId())) {
            throw new ConflictInTheDatabaseException("Catechumen Id: " + entity.getCatechumen().getId() + ", has already been saved!");
        }
    }

    private void markMassAsRegisteredAttendance(PresenceRequestDTO presence) {
        Mass massWithRegisteredPresence = massRepository.findById(presence.getMassId())
            .orElseThrow(() -> new NotFoundException("Mass ID: " + presence.getMassId() + " not found"));
        massWithRegisteredPresence.setRegisteredAttendance(true);
        massRepository.save(massWithRegisteredPresence);
    }

    private void ensureJustification(PresenceStatusEnum status, String justification) {
        if ((status.equals(PresenceStatusEnum.PRESENT_LATE) || status.equals(PresenceStatusEnum.ABSENT)) && justification == null) {
            throw new RequiredFieldsException("Justify the recurring delay in registering attendance");
        }
    }
}
