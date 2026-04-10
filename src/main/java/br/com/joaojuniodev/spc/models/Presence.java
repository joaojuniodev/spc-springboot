package br.com.joaojuniodev.spc.models;

import br.com.joaojuniodev.spc.models.enums.PresencaStatusEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"catequizando_id", "missa_id", "catequista_id"})
    }
)
public class Presence implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "catequizando_id", nullable = false)
    private Catechumen catequizando;

    @ManyToOne
    @JoinColumn(name = "missa_id", nullable = false)
    private Missa missa;

    @ManyToOne
    @JoinColumn(name = "catequista_id", nullable = false)
    private Catequista catequista;

    @Column(nullable = false)
    private PresencaStatusEnum status;
    
    private String justification;

    public Presence() {}

    public Presence(Long id, Catechumen catequizando, Missa missa, Catequista catequista, PresencaStatusEnum status, String justification) {
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

    public Catechumen getCatequizando() {
        return catequizando;
    }

    public void setCatequizando(Catechumen catequizando) {
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

    public Catequista getCatequista() {
        return catequista;
    }

    public void setCatequista(Catequista catequista) {
        this.catequista = catequista;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Presence presenca = (Presence) o;
        return Objects.equals(getId(), presenca.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
