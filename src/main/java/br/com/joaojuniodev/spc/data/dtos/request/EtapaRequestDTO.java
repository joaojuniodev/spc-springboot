package br.com.joaojuniodev.spc.data.dtos.request;

import br.com.joaojuniodev.spc.models.enums.EtapaEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EtapaRequestDTO {

    private Long id;
    private EtapaEnum etapa;
    private CatequistaRequestDTO catequista;
    private List<CatequizandoRequestDTO> catequizandos = new ArrayList<>();

    public EtapaRequestDTO() {}

    public EtapaRequestDTO(Long id, EtapaEnum etapa, CatequistaRequestDTO catequista, List<CatequizandoRequestDTO> catequizandos) {
        this.id = id;
        this.etapa = etapa;
        this.catequista = catequista;
        this.catequizandos = catequizandos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EtapaEnum getEtapa() {
        return etapa;
    }

    public void setEtapa(EtapaEnum etapa) {
        this.etapa = etapa;
    }

    public CatequistaRequestDTO getCatequista() {
        return catequista;
    }

    public void setCatequista(CatequistaRequestDTO catequista) {
        this.catequista = catequista;
    }

    public List<CatequizandoRequestDTO> getCatequizandos() {
        return catequizandos;
    }

    public void setCatequizandos(List<CatequizandoRequestDTO> catequizandos) {
        this.catequizandos = catequizandos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        EtapaRequestDTO that = (EtapaRequestDTO) o;
        return Objects.equals(getId(), that.getId()) && getEtapa() == that.getEtapa() && Objects.equals(getCatequista(), that.getCatequista()) && Objects.equals(getCatequizandos(), that.getCatequizandos());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getEtapa());
        result = 31 * result + Objects.hashCode(getCatequista());
        result = 31 * result + Objects.hashCode(getCatequizandos());
        return result;
    }
}
