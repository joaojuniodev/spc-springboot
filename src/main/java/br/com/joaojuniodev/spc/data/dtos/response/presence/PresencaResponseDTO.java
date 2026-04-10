package br.com.joaojuniodev.spc.data.dtos.response.presence;

import br.com.joaojuniodev.spc.data.dtos.response.catechist.CatequistaResponseByEtapaDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechumens.CatechumenResponseByPresenceDTO;
import br.com.joaojuniodev.spc.data.dtos.response.mass.MissaResponseDTO;
import br.com.joaojuniodev.spc.models.enums.PresencaStatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Objects;

public class PresencaResponseDTO {

    private Long id;
    private CatechumenResponseByPresenceDTO catequizando;
    private MissaResponseDTO missa;
    private CatequistaResponseByEtapaDTO catequista;
    @Enumerated(EnumType.STRING)
    private PresencaStatusEnum status;
    private String justification;

    public PresencaResponseDTO() {}

    public PresencaResponseDTO(Long id, CatechumenResponseByPresenceDTO catequizando, MissaResponseDTO missa, CatequistaResponseByEtapaDTO catequista, PresencaStatusEnum status, String justification) {
        this.id = id;
        this.catequizando = catequizando;
        this.missa = missa;
        this.catequista = catequista;
        this.status = status;
        this.justification = justification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CatechumenResponseByPresenceDTO getCatequizando() {
        return catequizando;
    }

    public void setCatequizando(CatechumenResponseByPresenceDTO catequizando) {
        this.catequizando = catequizando;
    }

    public MissaResponseDTO getMissa() {
        return missa;
    }

    public void setMissa(MissaResponseDTO missa) {
        this.missa = missa;
    }

    public CatequistaResponseByEtapaDTO getCatequista() {
        return catequista;
    }

    public void setCatequista(CatequistaResponseByEtapaDTO catequista) {
        this.catequista = catequista;
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
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCatequizando(), that.getCatequizando()) && Objects.equals(getMissa(), that.getMissa()) && Objects.equals(getCatequista(), that.getCatequista()) && getStatus() == that.getStatus() && Objects.equals(getJustification(), that.getJustification());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getCatequizando());
        result = 31 * result + Objects.hashCode(getMissa());
        result = 31 * result + Objects.hashCode(getCatequista());
        result = 31 * result + Objects.hashCode(getStatus());
        result = 31 * result + Objects.hashCode(getJustification());
        return result;
    }
}
