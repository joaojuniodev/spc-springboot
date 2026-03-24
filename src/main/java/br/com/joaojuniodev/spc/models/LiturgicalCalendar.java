package br.com.joaojuniodev.spc.models;

import br.com.joaojuniodev.spc.services.MissaService;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class LiturgicalCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private LocalDate date;

    @OneToMany(mappedBy = "massOfLiturgicalCalendar")
    private List<Missa> missas;

    public LiturgicalCalendar() {}

    public LiturgicalCalendar(Long id, String title, LocalDate date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Missa> getMissas() {
        return missas;
    }

    public void setMissas(List<Missa> missas) {
        this.missas = missas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        LiturgicalCalendar that = (LiturgicalCalendar) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}