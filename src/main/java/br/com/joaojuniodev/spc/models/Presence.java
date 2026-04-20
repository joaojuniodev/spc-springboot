package br.com.joaojuniodev.spc.models;

import br.com.joaojuniodev.spc.models.enums.PresenceStatusEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(
    name = "presences",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"catechumen_id", "mass_id", "catechist_id"})
    }
)
public class Presence implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "catechumen_id", nullable = false)
    private Catechumen catechumen;

    @ManyToOne
    @JoinColumn(name = "mass_id", nullable = false)
    private Mass mass;

    @ManyToOne
    @JoinColumn(name = "catechist_id", nullable = false)
    private Catechist catechist;

    @Column(nullable = false)
    private PresenceStatusEnum status;
    
    private String justification;

    public Presence() {}

    public Presence(Long id, Catechumen catechumen, Mass mass, Catechist catechist, PresenceStatusEnum status, String justification) {
        this.id = id;
        this.catechumen = catechumen;
        this.mass = mass;
        this.catechist = catechist;
        this.status = status;
        this.justification = justification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Catechumen getCatechumen() {
        return catechumen;
    }

    public void setCatechumen(Catechumen catechumen) {
        this.catechumen = catechumen;
    }

    public Mass getMass() {
        return mass;
    }

    public void setMass(Mass mass) {
        this.mass = mass;
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

    public Catechist getCatechist() {
        return catechist;
    }

    public void setCatechist(Catechist catechist) {
        this.catechist = catechist;
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
