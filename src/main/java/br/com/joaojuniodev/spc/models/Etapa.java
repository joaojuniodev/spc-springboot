package br.com.joaojuniodev.spc.models;

import br.com.joaojuniodev.spc.models.enums.EtapaEnum;
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

    @OneToOne
    private Catequista catequista;

    @OneToMany(mappedBy = "etapa", cascade = CascadeType.ALL)
    private List<Catequizando> catequizandos = new ArrayList<>();

    public Etapa() {}

    public Etapa(Long id, EtapaEnum etapa, Catequista catequista, List<Catequizando> catequizandos) {
        this.id = id;
        this.etapa = etapa;
        this.catequista = catequista;
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

    public void setEtapa(EtapaEnum etapa) {
        this.etapa = etapa;
    }

    public Catequista getCatequista() {
        return catequista;
    }

    public void setCatequista(Catequista catequista) {
        this.catequista = catequista;
    }

    public List<Catequizando> getCatequizandos() {
        return catequizandos;
    }

    public void setCatequizandos(List<Catequizando> catequizandos) {
        this.catequizandos = catequizandos;
    }

    public void addCatequizando(Catequizando catequizando) {
        this.catequizandos.add(catequizando);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Etapa etapa = (Etapa) o;
        return Objects.equals(getId(), etapa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
