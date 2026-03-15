package br.com.joaojuniodev.spc.mapper;

import br.com.joaojuniodev.spc.data.dtos.request.*;
import br.com.joaojuniodev.spc.data.dtos.response.*;
import br.com.joaojuniodev.spc.models.*;
import br.com.joaojuniodev.spc.repositories.CatequistaRepository;
import br.com.joaojuniodev.spc.repositories.CatequizandoRepository;
import br.com.joaojuniodev.spc.repositories.EtapaRepository;
import br.com.joaojuniodev.spc.repositories.MissaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public ObjectMapperManually() {}

    public Catequista convertCatequistaRequestToEntity(CatequistaRequestDTO catequista) {
        return new Catequista(catequista.getId(), catequista.getFirstName(), catequista.getLastName());
    }

    public CatequistaResponseDTO convertCatequistaEntityToResponseDTO(Catequista entity) {
        return new CatequistaResponseDTO(entity.getId(), entity.getFirstName(), entity.getLastName());
    }

    public Catequizando convertCatequizandoRequestToEntity(CatequizandoRequestDTO catequizando) {
        Etapa etapa = null;
        if (catequizando.getEtapaId() != null) etapa = etapaRepository.findById(catequizando.getEtapaId()).orElseThrow();
        return new Catequizando(
            catequizando.getId(),
            catequizando.getFirstName(),
            catequizando.getLastName(),
            LocalDate.parse(catequizando.getBirthDate()),
            etapa
        );
    }

    public CatequizandoResponseDTO convertCatequizandoEntityToResponseDTO(Catequizando entity) {
        return new CatequizandoResponseDTO(
            entity.getId(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getBirthDate(),
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
        return new Missa(missa.getId(), missa.getTitle(), LocalDateTime.parse(missa.getDateTime()));
    }

    public MissaResponseDTO convertMissaEntityToResponseDTO(Missa entity) {
        return new MissaResponseDTO(
            entity.getId(),
            entity.getTitle(),
            entity.getDateTime(),
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
