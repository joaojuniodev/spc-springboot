package br.com.joaojuniodev.spc.models;

import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Catequista implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column
    private String lastName;

    @Enumerated(EnumType.STRING)
    private NameOfTheCommunityOrParishEnum nameCommunityOrParish;

    @ManyToOne
    @JoinColumn(name = "etapa_id")
    private Etapa etapa;

    @OneToMany
    private List<Presenca> presencas;

    public Catequista() {}

    public Catequista(Long id, String firstName, String lastName, NameOfTheCommunityOrParishEnum nameCommunityOrParish, Etapa etapa) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nameCommunityOrParish = nameCommunityOrParish;
        this.etapa = etapa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public NameOfTheCommunityOrParishEnum getNameCommunityOrParish() {
        return nameCommunityOrParish;
    }

    public void setNameCommunityOrParish(NameOfTheCommunityOrParishEnum nameCommunityOrParish) {
        this.nameCommunityOrParish = nameCommunityOrParish;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public List<Presenca> getPresencas() {
        return presencas;
    }

    public void setPresencas(List<Presenca> presencas) {
        this.presencas = presencas;
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
