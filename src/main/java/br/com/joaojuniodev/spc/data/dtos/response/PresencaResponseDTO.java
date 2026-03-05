package br.com.joaojuniodev.spc.data.dtos.response;

import br.com.joaojuniodev.spc.models.enums.PresencaStatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Objects;

public class PresencaResponseDTO {

    private Long id;
    private CatequizandoResponseDTO catequizando;
    private MissaResponseDTO missa;
    @Enumerated(EnumType.STRING)
    private PresencaStatusEnum status;
    private String justification;

    public PresencaResponseDTO() {}

    public PresencaResponseDTO(Long id, CatequizandoResponseDTO catequizando, MissaResponseDTO missa, PresencaStatusEnum status, String justification) {
        this.id = id;
        this.catequizando = catequizando;
        this.missa = missa;
        this.status = status;
        this.justification = justification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CatequizandoResponseDTO getCatequizando() {
        return catequizando;
    }

    public void setCatequizando(CatequizandoResponseDTO catequizando) {
        this.catequizando = catequizando;
    }

    public MissaResponseDTO getMissa() {
        return missa;
    }

    public void setMissa(MissaResponseDTO missa) {
        this.missa = missa;
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

        PresencaResponseDTO that = (PresencaResponseDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCatequizando(), that.getCatequizando()) && Objects.equals(getMissa(), that.getMissa()) && getStatus() == that.getStatus() && Objects.equals(getJustification(), that.getJustification());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getCatequizando());
        result = 31 * result + Objects.hashCode(getMissa());
        result = 31 * result + Objects.hashCode(getStatus());
        result = 31 * result + Objects.hashCode(getJustification());
        return result;
    }
}
