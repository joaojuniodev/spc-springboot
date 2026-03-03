package br.com.joaojuniodev.spc.models;

import br.com.joaojuniodev.spc.models.enums.PresencaStatusEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Presenca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Catequizando catequizando;

    @OneToOne
    private Missa missa;

    @Column(nullable = false)
    private PresencaStatusEnum status;
    
    private String justification;

    public Presenca() {}

    public Presenca(Long id, Catequizando catequizando, Missa missa, PresencaStatusEnum status, String justification) {
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

    public Catequizando getCatequizando() {
        return catequizando;
    }

    public void setCatequizando(Catequizando catequizando) {
        this.catequizando = catequizando;
    }

    public Missa getMissa() {
        return missa;
    }

    public void setMissa(Missa missa) {
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

        Presenca presenca = (Presenca) o;
        return Objects.equals(getId(), presenca.getId()) && Objects.equals(getCatequizando(), presenca.getCatequizando()) && Objects.equals(getMissa(), presenca.getMissa()) && getStatus() == presenca.getStatus() && Objects.equals(getJustification(), presenca.getJustification());
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
