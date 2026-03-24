package br.com.joaojuniodev.spc.mapper;

import br.com.joaojuniodev.spc.data.dtos.request.*;
import br.com.joaojuniodev.spc.data.dtos.response.*;
import br.com.joaojuniodev.spc.models.*;
import br.com.joaojuniodev.spc.repositories.*;
import br.com.joaojuniodev.spc.services.EtapaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        return new Catequista(catequista.getId(), catequista.getFirstName(), catequista.getLastName(), catequista.getNameCommunityOrParish());
    }

    public CatequistaResponseDTO convertCatequistaEntityToResponseDTO(Catequista entity) {
        var stepDTO = etapaRepository.findByCatechistIdProjection(entity.getId())
            .map(projection -> new StepOfCatechistResponseDTO(projection.getId(), projection.getEtapa()))
            .orElse(new StepOfCatechistResponseDTO());
        return new CatequistaResponseDTO(
            entity.getId(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getCode(),
            stepDTO
        );
    }

    public Catequizando convertCatequizandoRequestToEntity(CatequizandoRequestDTO catequizando) {
        Etapa etapa = null;
        if (catequizando.getEtapaId() != null) etapa = etapaRepository.findById(catequizando.getEtapaId()).orElseThrow();
        return new Catequizando(
            catequizando.getId(),
            catequizando.getFirstName(),
            catequizando.getLastName(),
            LocalDate.parse(catequizando.getBirthDate()),
            catequizando.getNameCommunityOrParish(),
            etapa
        );
    }

    public CatequizandoResponseDTO convertCatequizandoEntityToResponseDTO(Catequizando entity) {
        return new CatequizandoResponseDTO(
            entity.getId(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getBirthDate(),
            entity.getCommunityOrParish(),
            convertEtapaEntityToByCatequizandoResponseDTO(entity.getEtapa())
        );
    }

    public Etapa convertEtapaRequestToEntity(EtapaRequestDTO etapa) {
        Catequista catequista = null;
        var catequizandos = catequizandoRepository.findByEtapaId(etapa.getId()).orElseThrow();

        if (etapa.getCatequistaId() != null) {
            catequista = catequistaRepository.findById(
                etapa.getCatequistaId()).orElseThrow(
                    () -> new RuntimeException("Not found this ID [Catequista]: " + etapa.getCatequistaId())
            );
        }

        return new Etapa(etapa.getId(), etapa.getEtapa(), catequista, catequizandos);
    }

    public EtapaResponseDTO convertEtapaEntityToResponseDTO(Etapa entity) {
        return new EtapaResponseDTO(entity.getId(), entity.getEtapa(),
            convertCatequistaEntityToResponseDTO(entity.getCatequista()),
            entity.getCatequizandos().stream().map(this::convertCatequizandoEntityToResponseDTO).toList());
    }

    public EtapaByCatequizandoResponseDTO convertEtapaEntityToByCatequizandoResponseDTO(Etapa entity) {
        return new EtapaByCatequizandoResponseDTO(entity.getId(), entity.getEtapa(), convertCatequistaEntityToResponseDTO(entity.getCatequista()));
    }

    public Missa convertMissaRequestToEntity(MissaRequestDTO missa) {
        var massOfLiturgicalCalendar = liturgicalCalendarRepository.findById(missa.getMassOfLiturgicalCalendarId()).get();
        return new Missa(
            missa.getId(),
            massOfLiturgicalCalendar.getTitle(),
            LocalDateTime.parse(missa.getDateTime())
        );
    }

    public MissaResponseDTO convertMissaEntityToResponseDTO(Missa entity) {
        return new MissaResponseDTO(
            entity.getId(),
            entity.getTitle(),
            entity.getDateTime(),
            entity.getMassOfLiturgicalCalendar().getId(),
            entity.getRegisteredAttendance() != null
        );
    }

    public Presenca convertPresencaRequestToEntity(PresencaRequestDTO presenca) {
        Catequizando catequizando = null;
        Missa missa = null;
        if (presenca.getCatequizandoId() != null) catequizando = catequizandoRepository.findById(presenca.getCatequizandoId()).orElseThrow();
        if (presenca.getMissaId() != null) missa = missaRepository.findById(presenca.getMissaId()).orElseThrow();
        return new Presenca(presenca.getId(), catequizando, missa, presenca.getStatus(), presenca.getJustification());
    }

    public PresencaResponseDTO convertPresencaEntityToResponseDTO(Presenca entity) {
        return new PresencaResponseDTO(
            entity.getId(),
            convertCatequizandoEntityToResponseDTO(entity.getCatequizando()),
            convertMissaEntityToResponseDTO(entity.getMissa()),
            entity.getStatus(),
            entity.getJustification()
        );
    }
}
