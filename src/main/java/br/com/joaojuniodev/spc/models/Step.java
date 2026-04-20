package br.com.joaojuniodev.spc.models;

import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.models.enums.StepNameEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "steps")
public class Step implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StepNameEnum stepName;

    @Enumerated(EnumType.STRING)
    @Column
    private NameOfTheCommunityOrParishEnum nameCommunityOrParish;

    @OneToMany(mappedBy = "step", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Catechist> catechists;

    @OneToMany(mappedBy = "step", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Catechumen> catechumens;

    public Step() {}

    public Step(Long id, StepNameEnum stepName, NameOfTheCommunityOrParishEnum nameCommunityOrParish, Set<Catechist> catechists, Set<Catechumen> catechumens) {
        this.id = id;
        this.stepName = stepName;
        this.nameCommunityOrParish = nameCommunityOrParish;
        this.catechists = catechists;
        this.catechumens = catechumens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StepNameEnum getStepName() {
        return stepName;
    }

    public void setStepName(StepNameEnum stepName) {
        this.stepName = stepName;
    }

    public Set<Catechist> getCatechists() {
        return catechists;
    }

    public void setCatechists(Set<Catechist> catechists) {
        this.catechists = catechists;
    }

    public void setStep(StepNameEnum stepName) {
        this.stepName = stepName;
    }

    public NameOfTheCommunityOrParishEnum getNameCommunityOrParish() {
        return nameCommunityOrParish;
    }

    public void setNameCommunityOrParish(NameOfTheCommunityOrParishEnum nameCommunityOrParish) {
        this.nameCommunityOrParish = nameCommunityOrParish;
    }

    public Set<Catechumen> getCatechumens() {
        return catechumens;
    }

    public void setCatechumens(Set<Catechumen> catechumens) {
        this.catechumens = catechumens;
    }

    public void addCatechumen(Catechumen catechumen) {
        this.catechumens.add(catechumen);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Step step = (Step) o;
        return Objects.equals(getId(), step.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
