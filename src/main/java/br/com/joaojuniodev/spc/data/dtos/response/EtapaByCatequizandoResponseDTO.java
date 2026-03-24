package br.com.joaojuniodev.spc.data.dtos.response;

import br.com.joaojuniodev.spc.models.enums.EtapaEnum;

import java.util.List;
import java.util.Objects;

public class EtapaByCatequizandoResponseDTO {

    private Long id;
    private EtapaEnum etapa;
    private List<CatequistaResponseDTO> catequistas;

    public EtapaByCatequizandoResponseDTO(Long id, EtapaEnum etapa, List<CatequistaResponseDTO> catequistas) {
        this.id = id;
        this.etapa = etapa;
        this.catequistas = catequistas;
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

    public List<CatequistaResponseDTO> getCatequistas() {
        return catequistas;
    }

    public void setCatequistas(List<CatequistaResponseDTO> catequistas) {
        this.catequistas = catequistas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        EtapaByCatequizandoResponseDTO that = (EtapaByCatequizandoResponseDTO) o;
        return Objects.equals(getId(), that.getId()) && getEtapa() == that.getEtapa() && Objects.equals(getCatequistas(), that.getCatequistas());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getEtapa());
        result = 31 * result + Objects.hashCode(getCatequistas());
        return result;
    }
}
