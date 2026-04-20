package br.com.joaojuniodev.spc.mapper;

import br.com.joaojuniodev.spc.data.dtos.request.*;
import br.com.joaojuniodev.spc.data.dtos.response.catechist.CatechistResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechist.CatechistSummaryDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechumens.CatechumenResponseByPresenceDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechumens.CatechumenResponseByStepDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechumens.CatechumenResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.liturgicalCalendar.LiturgicalCalendarResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.mass.MassResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.CatechumenIsPresentDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.PresenceResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.step.StepByCatechumenResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.step.StepOfCatechistResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.step.StepResponseDTO;
import br.com.joaojuniodev.spc.models.*;
import br.com.joaojuniodev.spc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class ObjectMapperManually {

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private CatechistRepository catechistRepository;

    @Autowired
    private CatechumenRepository catechumenRepository;

    @Autowired
    private MassRepository massRepository;

    @Autowired
    private LiturgicalCalendarRepository liturgicalCalendarRepository;

    public ObjectMapperManually() {}

    public Catechist convertCatechistRequestToEntity(CatechistRequestDTO catechist) {
        var step = stepRepository.findById(catechist.getStepId()).get();
        return new Catechist(
            catechist.getId(),
            catechist.getFirstName(),
            catechist.getLastName(),
            catechist.getNameCommunityOrParish(),
            step
        );
    }

    public CatechistResponseDTO convertCatechistEntityToResponseDTO(Catechist entity) {
        var stepDTO = entity.getStep() != null
            ? new StepOfCatechistResponseDTO(entity.getStep().getId(), entity.getStep().getStepName())
            : null;
        return new CatechistResponseDTO(
            entity.getId(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getNameCommunityOrParish(),
            stepDTO
        );
    }

    public Catechumen convertCatechumenRequestToEntity(CatechumenRequestDTO catechumen) {
        Step step = null;
        if (catechumen.getStepId() != null) step = stepRepository.findById(catechumen.getStepId()).orElseThrow();
        return new Catechumen(
            catechumen.getId(),
            catechumen.getFirstName(),
            catechumen.getLastName(),
            LocalDate.parse(catechumen.getBirthDate()),
            catechumen.getNameCommunityOrParish(),
            step
        );
    }

    public CatechumenResponseDTO convertCatechumenEntityToResponseDTO(Catechumen entity) {
        return new CatechumenResponseDTO(
            entity.getId(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getBirthDate(),
            entity.getNameCommunityOrParish(),
            convertStepEntityToByCatechumenResponseDTO(entity.getStep())
        );
    }

    @Transactional
    public CatechumenResponseByPresenceDTO convertCatechumenEntityToByPresenceResponseDTO(Catechumen entity) {
        var catechists = catechistRepository.findByStepId(entity.getStep().getId());
        return new CatechumenResponseByPresenceDTO(
            entity.getId(),
            entity.getFirstName()+" "+entity.getLastName(),
            entity.getStep().getStepName(),
            catechists.stream().map(this::convertCatechistEntityToSummaryDTO).toList()
        );
    }

    public CatechistSummaryDTO convertCatechistEntityToSummaryDTO(Catechist entity) {
        return new CatechistSummaryDTO(entity.getId(), entity.getFirstName(), entity.getLastName());
    }

    public CatechumenIsPresentDTO convertCatechumenToCatechumensIsPresentDTO(Catechumen entity) {
        return new CatechumenIsPresentDTO(entity.getId(), entity.getFirstName(), entity.getLastName());
    }

    public Step convertStepRequestToEntity(StepRequestDTO step) {
        Set<Catechist> catechists = new HashSet<>();
        Set<Catechumen> catechumens = (Set<Catechumen>) catechumenRepository.findByStepId(step.getId()).orElseThrow();

        if (step.getCatechistsId().length > 0) {
            Arrays.stream(Arrays.stream(step.getCatechistsId())
                .map(id -> catechists.add(catechistRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("Not found this ID [Catechist]: " + id)
                ))).toArray());
        }

        return new Step(step.getId(), step.getStepName(), step.getCommunityOrParish(), catechists, catechumens);
    }

    public StepResponseDTO convertStepEntityToResponseDTO(Step entity) {
        return new StepResponseDTO(
            entity.getId(),
            entity.getStepName(),
            entity.getNameCommunityOrParish(),
            entity.getCatechists().stream().map(this::convertCatechistEntityToSummaryDTO).toList(),
            entity.getCatechumens().stream().map(this::convertCatechumenEntityToByStepResponseDTO).toList()
        );
    }

    public CatechumenResponseByStepDTO convertCatechumenEntityToByStepResponseDTO(Catechumen catechumen) {
        return new CatechumenResponseByStepDTO(catechumen.getId(), catechumen.getFirstName(), catechumen.getLastName(), catechumen.getBirthDate());
    }

    public StepByCatechumenResponseDTO convertStepEntityToByCatechumenResponseDTO(Step entity) {
        return new StepByCatechumenResponseDTO(
            entity.getId(),
            entity.getStepName(),
            entity.getCatechists().stream().map(this::convertCatechistEntityToSummaryDTO).toList()
        );
    }

    public Mass convertMassRequestToEntity(MassRequestDTO mass) {
        var massOfLiturgicalCalendar = liturgicalCalendarRepository.findById(mass.getMassOfLiturgicalCalendarId()).get();
        return new Mass(
            mass.getId(),
            massOfLiturgicalCalendar.getTitle(),
            LocalDateTime.parse(mass.getDateTime()),
            mass.getNameCommunityOrParish(),
            mass.getLocation(),
            massOfLiturgicalCalendar
        );
    }

    public MassResponseDTO convertMassEntityToResponseDTO(Mass entity) {
        return new MassResponseDTO(
            entity.getId(),
            entity.getTitle(),
            entity.getDateTime(),
            entity.getLocation(),
            entity.getNameCommunityOrParish(),
            entity.getMassOfLiturgicalCalendar().getId(),
            entity.getRegisteredAttendance() != null
        );
    }

    public Presence convertPresenceRequestToEntity(PresenceRequestDTO presence) {
        Catechist catechist = null;
        Catechumen catechumen = null;
        Mass mass = null;
        if (presence.getCatechumenId() != null) catechumen = catechumenRepository.findById(presence.getCatechumenId()).orElseThrow();
        if (presence.getMassId() != null) mass = massRepository.findById(presence.getMassId()).orElseThrow();
        if (presence.getCatechistId() != null) catechist = catechistRepository.findById(presence.getCatechistId()).orElseThrow();
        return new Presence(presence.getId(), catechumen, mass, catechist, presence.getStatus(), presence.getJustification());
    }

    public PresenceResponseDTO convertPresenceEntityToResponseDTO(Presence entity) {
        return new PresenceResponseDTO(
            entity.getId(),
            convertCatechumenEntityToByPresenceResponseDTO(entity.getCatechumen()),
            convertMassEntityToResponseDTO(entity.getMass()),
            convertCatechistEntityToSummaryDTO(entity.getCatechist()),
            entity.getStatus(),
            entity.getJustification()
        );
    }

    public LiturgicalCalendarResponseDTO convertLiturgicalCalendarEntityToResponseDTO(LiturgicalCalendar entity) {
        return new LiturgicalCalendarResponseDTO(entity.getId(), entity.getTitle(), entity.getDate());
    }
}
