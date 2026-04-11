package br.com.joaojuniodev.spc.mapper;

import br.com.joaojuniodev.spc.data.dtos.request.*;
import br.com.joaojuniodev.spc.data.dtos.response.catechist.CatequistaResponseByCatequizandoDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechist.CatequistaResponseByEtapaDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechist.CatequistaResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechumens.CatechumenResponseByStepDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechumens.CatechumenResponseByPresenceDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechumens.CatechumenResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.liturgicalCalendar.LiturgicalCalendarResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.mass.MissaResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.CatechumenIsPresentDTO;
import br.com.joaojuniodev.spc.data.dtos.response.presence.PresencaResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.step.EtapaByCatequizandoResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.step.EtapaResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.step.StepOfCatechistResponseDTO;
import br.com.joaojuniodev.spc.models.*;
import br.com.joaojuniodev.spc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ObjectMapperManually {

    @Autowired
    private EtapaRepository etapaRepository;

    @Autowired
    private CatequistaRepository catequistaRepository;

    @Autowired
    private CatequizandoRepository catequizandoRepository;

    @Autowired
    private MissaRepository missaRepository;

    @Autowired
    private LiturgicalCalendarRepository liturgicalCalendarRepository;

    public ObjectMapperManually() {}

    public Catequista convertCatequistaRequestToEntity(CatequistaRequestDTO catequista) {
        var etapa = etapaRepository.findById(catequista.getEtapaId()).get();
        return new Catequista(
            catequista.getId(),
            catequista.getFirstName(),
            catequista.getLastName(),
            catequista.getNameCommunityOrParish(),
            etapa
        );
    }

    public CatequistaResponseDTO convertCatequistaEntityToResponseDTO(Catequista entity) {
        var stepDTO = entity.getEtapa() != null
            ? new StepOfCatechistResponseDTO(entity.getEtapa().getId(), entity.getEtapa().getEtapa())
            : null;
        return new CatequistaResponseDTO(
            entity.getId(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getNameCommunityOrParish(),
            stepDTO
        );
    }

    public Catechumen convertCatequizandoRequestToEntity(CatequizandoRequestDTO catequizando) {
        Etapa etapa = null;
        if (catequizando.getEtapaId() != null) etapa = etapaRepository.findById(catequizando.getEtapaId()).orElseThrow();
        return new Catechumen(
            catequizando.getId(),
            catequizando.getFirstName(),
            catequizando.getLastName(),
            LocalDate.parse(catequizando.getBirthDate()),
            catequizando.getNameCommunityOrParish(),
            etapa
        );
    }

    public CatechumenResponseDTO convertCatequizandoEntityToResponseDTO(Catechumen entity) {
        return new CatechumenResponseDTO(
            entity.getId(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getBirthDate(),
            entity.getNameCommunityOrParish(),
            convertEtapaEntityToByCatequizandoResponseDTO(entity.getEtapa())
        );
    }

    @Transactional
    public CatechumenResponseByPresenceDTO convertCatequizandoEntityToByPresencaResponseDTO(Catechumen entity) {
        var catequistas = catequistaRepository.findByStepId(entity.getEtapa().getId());
        return new CatechumenResponseByPresenceDTO(
            entity.getId(),
            entity.getFirstName()+" "+entity.getLastName(),
            entity.getEtapa().getEtapa(),
            catequistas.stream().map(this::convertCatequistaEntityToResponseByCatequizandoDTO).toList()
        );
    }

    @Transactional
    public CatequistaResponseByCatequizandoDTO convertCatequistaEntityToResponseByCatequizandoDTO(Catequista entity) {
        return new CatequistaResponseByCatequizandoDTO(entity.getFirstName()+" "+entity.getLastName());
    }

    public CatechumenIsPresentDTO convertCatechumenToCatechumensIsPresentDTO(Catechumen entity) {
        return new CatechumenIsPresentDTO(entity.getId(), entity.getFirstName(), entity.getLastName());
    }

    public Etapa convertEtapaRequestToEntity(EtapaRequestDTO etapa) {
        List<Catequista> catequistas = new ArrayList<>();
        var catequizandos = catequizandoRepository.findByEtapaId(etapa.getId()).orElseThrow();

        if (etapa.getCatequistasId().length > 0) {
            Arrays.stream(Arrays.stream(etapa.getCatequistasId())
                .map(id -> catequistas.add(catequistaRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("Not found this ID [Catequista]: " + id)
                ))).toArray());
        }

        return new Etapa(etapa.getId(), etapa.getEtapa(), etapa.getCommunityOrParish(), catequistas, catequizandos);
    }

    public EtapaResponseDTO convertEtapaEntityToResponseDTO(Etapa entity) {
        return new EtapaResponseDTO(
            entity.getId(),
            entity.getEtapa(),
            entity.getNameCommunityOrParish(),
            entity.getCatequistas().stream().map(this::convertCatequistaEntityToByEtapaResponseDTO).toList(),
            entity.getCatequizandos().stream().map(this::convertCatequizandoEntityToByEtapaResponseDTO).toList()
        );
    }

    public CatequistaResponseByEtapaDTO convertCatequistaEntityToByEtapaResponseDTO(Catequista catequista) {
        return new CatequistaResponseByEtapaDTO(catequista.getId(), catequista.getFirstName(), catequista.getLastName());
    }

    public CatechumenResponseByStepDTO convertCatequizandoEntityToByEtapaResponseDTO(Catechumen catequizando) {
        return new CatechumenResponseByStepDTO(catequizando.getId(), catequizando.getFirstName(), catequizando.getLastName(), catequizando.getBirthDate());
    }

    public EtapaByCatequizandoResponseDTO convertEtapaEntityToByCatequizandoResponseDTO(Etapa entity) {
        return new EtapaByCatequizandoResponseDTO(
            entity.getId(),
            entity.getEtapa(),
            entity.getCatequistas().stream().map(this::convertCatequistaEntityToResponseDTO).toList()
        );
    }

    public Missa convertMissaRequestToEntity(MissaRequestDTO missa) {
        var massOfLiturgicalCalendar = liturgicalCalendarRepository.findById(missa.getMassOfLiturgicalCalendarId()).get();
        return new Missa(
            missa.getId(),
            massOfLiturgicalCalendar.getTitle(),
            LocalDateTime.parse(missa.getDateTime()),
            missa.getNameCommunityOrParish(),
            missa.getLocation(),
            massOfLiturgicalCalendar
        );
    }

    public MissaResponseDTO convertMissaEntityToResponseDTO(Missa entity) {
        return new MissaResponseDTO(
            entity.getId(),
            entity.getTitle(),
            entity.getDateTime(),
            entity.getLocation(),
            entity.getNameCommunityOrParish(),
            entity.getMassOfLiturgicalCalendar().getId(),
            entity.getRegisteredAttendance() != null
        );
    }

    public Presence convertPresencaRequestToEntity(PresencaRequestDTO presenca) {
        Catequista catequista = null;
        Catechumen catequizando = null;
        Missa missa = null;
        if (presenca.getCatequizandoId() != null) catequizando = catequizandoRepository.findById(presenca.getCatequizandoId()).orElseThrow();
        if (presenca.getMissaId() != null) missa = missaRepository.findById(presenca.getMissaId()).orElseThrow();
        if (presenca.getCatequistaId() != null) catequista = catequistaRepository.findById(presenca.getCatequistaId()).orElseThrow();
        return new Presence(presenca.getId(), catequizando, missa, catequista, presenca.getStatus(), presenca.getJustification());
    }

    public PresencaResponseDTO convertPresencaEntityToResponseDTO(Presence entity) {
        return new PresencaResponseDTO(
            entity.getId(),
            convertCatequizandoEntityToByPresencaResponseDTO(entity.getCatequizando()),
            convertMissaEntityToResponseDTO(entity.getMissa()),
            convertCatequistaEntityToByEtapaResponseDTO(entity.getCatequista()),
            entity.getStatus(),
            entity.getJustification()
        );
    }

    public LiturgicalCalendarResponseDTO convertLiturgicalCalendarEntityToResponseDTO(LiturgicalCalendar entity) {
        return new LiturgicalCalendarResponseDTO(entity.getId(), entity.getTitle(), entity.getDate());
    }
}
