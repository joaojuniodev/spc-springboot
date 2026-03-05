package br.com.joaojuniodev.spc.mapper;

import br.com.joaojuniodev.spc.data.dtos.request.CatequistaRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.request.CatequizandoRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.CatequistaResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.CatequizandoResponseDTO;
import br.com.joaojuniodev.spc.models.Catequista;
import br.com.joaojuniodev.spc.models.Catequizando;
import br.com.joaojuniodev.spc.models.Etapa;
import br.com.joaojuniodev.spc.repositories.EtapaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ObjectMapperManually {

    @Autowired
    private static EtapaRepository etapaRepository;

    public static Catequista convertCatequistaRequestToEntity(CatequistaRequestDTO catequista) {
        return new Catequista(catequista.getId(), catequista.getFullName());
    }

    public static CatequistaResponseDTO convertCatequistaEntityToResponseDTO(Catequista entity) {
        return new CatequistaResponseDTO(entity.getId(), entity.getFullName());
    }

    public static Catequizando convertCatequizandoRequestToEntity(CatequizandoRequestDTO catequizando) {
        Etapa etapa = null;
        if (catequizando.getEtapaId() != null) etapa = etapaRepository.findById(catequizando.getId()).orElseThrow();
        return new Catequizando(catequizando.getId(), catequizando.getFullName(), catequizando.getBirthDate(), etapa);
    }

    public static CatequizandoResponseDTO convertCatequizandoEntityToResponseDTO(Catequizando entity) {
        return new CatequizandoResponseDTO(entity.getId(), entity.getFullName(), entity.getBirthDate(), entity.getEtapa().getId());
    }
}
