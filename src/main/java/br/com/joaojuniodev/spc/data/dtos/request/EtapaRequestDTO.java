package br.com.joaojuniodev.spc.data.dtos.request;

import br.com.joaojuniodev.spc.models.enums.EtapaEnum;

import java.util.Objects;

public class EtapaRequestDTO {

    private Long id;
    private EtapaEnum etapa;
    private Long catequistaId;

    public EtapaRequestDTO() {}

    public EtapaRequestDTO(Long id, EtapaEnum etapa, Long catequistaId) {
        this.id = id;
        this.etapa = etapa;
        this.catequistaId = catequistaId;
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

    public Long getCatequistaId() {
        return catequistaId;
    }

    public void setCatequistaId(Long catequistaId) {
        this.catequistaId = catequistaId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        EtapaRequestDTO that = (EtapaRequestDTO) o;
        return Objects.equals(getId(), that.getId()) && getEtapa() == that.getEtapa() && Objects.equals(getCatequistaId(), that.getCatequistaId());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getEtapa());
        result = 31 * result + Objects.hashCode(getCatequistaId());
        return result;
    }
}
