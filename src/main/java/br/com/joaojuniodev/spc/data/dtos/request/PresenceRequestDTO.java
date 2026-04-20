package br.com.joaojuniodev.spc.data.dtos.request;

import br.com.joaojuniodev.spc.models.enums.PresenceStatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Objects;

public class PresenceRequestDTO {

    private Long id;
    private Long catechistId;
    private Long catechumenId;
    private Long massId;
    @Enumerated(EnumType.STRING)
    private PresenceStatusEnum status;
    private String justification;

    public PresenceRequestDTO() {}

    public PresenceRequestDTO(Long id, Long catechistId, Long catechumenId, Long massId, PresenceStatusEnum status, String justification) {
        this.id = id;
        this.catechistId = catechistId;
        this.catechumenId = catechumenId;
        this.massId = massId;
        this.status = status;
        this.justification = justification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCatechistId() {
        return catechistId;
    }

    public void setCatechistId(Long catechistId) {
        this.catechistId = catechistId;
    }

    public Long getCatechumenId() {
        return catechumenId;
    }

    public void setCatechumenId(Long catechumenId) {
        this.catechumenId = catechumenId;
    }

    public Long getMassId() {
        return massId;
    }

    public void setMassId(Long massId) {
        this.massId = massId;
    }

    public PresenceStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PresenceStatusEnum status) {
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

        PresenceRequestDTO that = (PresenceRequestDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCatechistId(), that.getCatechistId()) && Objects.equals(getCatechumenId(), that.getCatechumenId()) && Objects.equals(getMassId(), that.getMassId()) && getStatus() == that.getStatus() && Objects.equals(getJustification(), that.getJustification());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getCatechistId());
        result = 31 * result + Objects.hashCode(getCatechumenId());
        result = 31 * result + Objects.hashCode(getMassId());
        result = 31 * result + Objects.hashCode(getStatus());
        result = 31 * result + Objects.hashCode(getJustification());
        return result;
    }
}
