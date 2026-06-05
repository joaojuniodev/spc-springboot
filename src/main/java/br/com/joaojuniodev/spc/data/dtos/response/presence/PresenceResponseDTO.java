package br.com.joaojuniodev.spc.data.dtos.response.presence;

import br.com.joaojuniodev.spc.data.dtos.response.catechumen.CatechumenResponseByPresenceDTO;
import br.com.joaojuniodev.spc.data.dtos.response.mass.MassResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.user.UserSummaryDTO;
import br.com.joaojuniodev.spc.models.enums.PresenceStatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Objects;

public class PresenceResponseDTO {

    private Long id;
    private CatechumenResponseByPresenceDTO catechumen;
    private MassResponseDTO mass;
    private UserSummaryDTO user;
    @Enumerated(EnumType.STRING)
    private PresenceStatusEnum status;
    private String justification;

    public PresenceResponseDTO() {}

    public PresenceResponseDTO(Long id, CatechumenResponseByPresenceDTO catechumen, MassResponseDTO mass, UserSummaryDTO user, PresenceStatusEnum status, String justification) {
        this.id = id;
        this.catechumen = catechumen;
        this.mass = mass;
        this.user = user;
        this.status = status;
        this.justification = justification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CatechumenResponseByPresenceDTO getCatechumen() {
        return catechumen;
    }

    public void setCatechumen(CatechumenResponseByPresenceDTO catechumen) {
        this.catechumen = catechumen;
    }

    public MassResponseDTO getMass() {
        return mass;
    }

    public void setMass(MassResponseDTO mass) {
        this.mass = mass;
    }

    public UserSummaryDTO getUser() {
        return user;
    }

    public void setUser(UserSummaryDTO user) {
        this.user = user;
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

        PresenceResponseDTO that = (PresenceResponseDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCatechumen(), that.getCatechumen()) && Objects.equals(getMass(), that.getMass()) && Objects.equals(getUser(), that.getUser()) && getStatus() == that.getStatus() && Objects.equals(getJustification(), that.getJustification());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getCatechumen());
        result = 31 * result + Objects.hashCode(getMass());
        result = 31 * result + Objects.hashCode(getUser());
        result = 31 * result + Objects.hashCode(getStatus());
        result = 31 * result + Objects.hashCode(getJustification());
        return result;
    }
}
