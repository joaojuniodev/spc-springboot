package br.com.joaojuniodev.spc.models;

import br.com.joaojuniodev.spc.models.enums.EtapaEnum;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Etapa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EtapaEnum etapa;

    @Enumerated(EnumType.STRING)
    @Column
    private NameOfTheCommunityOrParishEnum nameCommunityOrParish;

    @OneToMany(mappedBy = "etapa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Catequista> catequistas;

    @OneToMany(mappedBy = "etapa", cascade = CascadeType.ALL)
    private List<Catechumen> catequizandos = new ArrayList<>();

    public Etapa() {}

    public Etapa(Long id, EtapaEnum etapa, NameOfTheCommunityOrParishEnum nameCommunityOrParish, List<Catequista> catequistas, List<Catechumen> catequizandos) {
        this.id = id;
        this.etapa = etapa;
        this.nameCommunityOrParish = nameCommunityOrParish;
        this.catequistas = catequistas;
        this.catequizandos = catequizandos;
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

    public void getEtapa(EtapaEnum etapa) {
        this.etapa = etapa;
    }

    public List<Catequista> getCatequistas() {
        return catequistas;
    }

    public void setCatequistas(List<Catequista> catequistas) {
        this.catequistas = catequistas;
    }

    public void setEtapa(EtapaEnum etapa) {
        this.etapa = etapa;
    }

    public NameOfTheCommunityOrParishEnum getNameCommunityOrParish() {
        return nameCommunityOrParish;
    }

    public void setNameCommunityOrParish(NameOfTheCommunityOrParishEnum nameCommunityOrParish) {
        this.nameCommunityOrParish = nameCommunityOrParish;
    }

    public List<Catechumen> getCatequizandos() {
        return catequizandos;
    }

    public void setCatequizandos(List<Catechumen> catequizandos) {
        this.catequizandos = catequizandos;
    }

    public void addCatequizando(Catechumen catequizando) {
        this.catequizandos.add(catequizando);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Etapa step = (Etapa) o;
        return Objects.equals(getId(), step.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
