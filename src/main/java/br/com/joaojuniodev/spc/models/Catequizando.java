package br.com.joaojuniodev.spc.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Catequizando implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @OneToOne
    private Etapa etapa;

    @OneToOne
    private Catequista catequista;

    @OneToMany(mappedBy = "catequizando")
    private List<Presenca> presenca;

    public Catequizando() {}

    public Catequizando(Long id, String fullName, LocalDate birthDate, Etapa etapa, Catequista catequista) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.etapa = etapa;
        this.catequista = catequista;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public Catequista getCatequista() {
        return catequista;
    }

    public void setCatequista(Catequista catequista) {
        this.catequista = catequista;
    }

    public List<Presenca> getPresenca() {
        return presenca;
    }

    public void setPresenca(List<Presenca> presenca) {
        this.presenca = presenca;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Catequizando that = (Catequizando) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
