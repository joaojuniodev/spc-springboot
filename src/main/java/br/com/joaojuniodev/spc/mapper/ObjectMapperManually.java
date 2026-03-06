package br.com.joaojuniodev.spc.mapper;

import br.com.joaojuniodev.spc.data.dtos.request.CatequistaRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.request.CatequizandoRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.request.EtapaRequestDTO;
import br.com.joaojuniodev.spc.data.dtos.response.CatequistaResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.CatequizandoResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.EtapaResponseDTO;
import br.com.joaojuniodev.spc.models.Catequista;
import br.com.joaojuniodev.spc.models.Catequizando;
import br.com.joaojuniodev.spc.models.Etapa;
import br.com.joaojuniodev.spc.repositories.CatequistaRepository;
import br.com.joaojuniodev.spc.repositories.CatequizandoRepository;
import br.com.joaojuniodev.spc.repositories.EtapaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperManually {

    @Autowired
    private EtapaRepository etapaRepository;

    @Autowired
    private CatequistaRepository catequistaRepository;

    @Autowired
    private CatequizandoRepository catequizandoRepository;

    public ObjectMapperManually() {}

    public Catequista convertCatequistaRequestToEntity(CatequistaRequestDTO catequista) {
        return new Catequista(catequista.getId(), catequista.getFullName());
    }

    public CatequistaResponseDTO convertCatequistaEntityToResponseDTO(Catequista entity) {
        return new CatequistaResponseDTO(entity.getId(), entity.getFullName());
    }

    public Catequizando convertCatequizandoRequestToEntity(CatequizandoRequestDTO catequizando) {
        Etapa etapa = null;
        if (catequizando.getEtapaId() != null) etapa = etapaRepository.findById(catequizando.getId()).orElseThrow();
        return new Catequizando(catequizando.getId(), catequizando.getFullName(), catequizando.getBirthDate(), etapa);
    }

    public CatequizandoResponseDTO convertCatequizandoEntityToResponseDTO(Catequizando entity) {
        return new CatequizandoResponseDTO(entity.getId(), entity.getFullName(), entity.getBirthDate(), entity.getEtapa().getId());
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
}
