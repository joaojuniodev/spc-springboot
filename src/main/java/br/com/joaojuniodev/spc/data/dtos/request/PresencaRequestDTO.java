package br.com.joaojuniodev.spc.data.dtos.request;

import br.com.joaojuniodev.spc.models.enums.PresencaStatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Objects;

public class PresencaRequestDTO {

    private Long id;
    private Long catequistaId;
    private Long catequizandoId;
    private Long missaId;
    @Enumerated(EnumType.STRING)
    private PresencaStatusEnum status;
    private String justification;

    public PresencaRequestDTO() {}

    public PresencaRequestDTO(Long id, Long catequistaId, Long catequizandoId, Long missaId, PresencaStatusEnum status, String justification) {
        this.id = id;
        this.catequistaId = catequistaId;
        this.catequizandoId = catequizandoId;
        this.missaId = missaId;
        this.status = status;
        this.justification = justification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCatequistaId() {
        return catequistaId;
    }

    public void setCatequistaId(Long catequistaId) {
        this.catequistaId = catequistaId;
    }

    public Long getCatequizandoId() {
        return catequizandoId;
    }

    public void setCatequizandoId(Long catequizandoId) {
        this.catequizandoId = catequizandoId;
    }

    public Long getMissaId() {
        return missaId;
    }

    public void setMissaId(Long missaId) {
        this.missaId = missaId;
    }

    public PresencaStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PresencaStatusEnum status) {
        this.status = status;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        PresencaRequestDTO that = (PresencaRequestDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCatequizandoId(), that.getCatequizandoId()) && Objects.equals(getMissaId(), that.getMissaId()) && getStatus() == that.getStatus() && Objects.equals(getJustification(), that.getJustification());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getCatequizandoId());
        result = 31 * result + Objects.hashCode(getMissaId());
        result = 31 * result + Objects.hashCode(getStatus());
        result = 31 * result + Objects.hashCode(getJustification());
        return result;
    }
}
