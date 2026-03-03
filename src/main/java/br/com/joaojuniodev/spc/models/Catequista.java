package br.com.joaojuniodev.spc.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Catequista implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @OneToOne
    private Etapa etapa;

    @OneToMany(mappedBy = "catequista", cascade = CascadeType.ALL)
    private List<Catequizando> catequizandos;

    public Catequista() {}

    public Catequista(Long id, String fullName, Etapa etapa) {
        this.id = id;
        this.fullName = fullName;
        this.etapa = etapa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public List<Catequizando> getCatequizandos() {
        return catequizandos;
    }

    public void setCatequizandos(List<Catequizando> catequizandos) {
        this.catequizandos = catequizandos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Catequista that = (Catequista) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
