package br.com.joaojuniodev.spc.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Missa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private Integer hour;

    @OneToMany(mappedBy = "missa")
    private List<Presenca> presenca;

    public Missa() {}

    public Missa(Long id, String title, LocalDateTime dateTime, Integer hour) {
        this.id = id;
        this.title = title;
        this.dateTime = dateTime;
        this.hour = hour;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
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

        Missa missa = (Missa) o;
        return Objects.equals(getId(), missa.getId()) && Objects.equals(getTitle(), missa.getTitle()) && Objects.equals(getDateTime(), missa.getDateTime()) && Objects.equals(getHour(), missa.getHour());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getTitle());
        result = 31 * result + Objects.hashCode(getDateTime());
        result = 31 * result + Objects.hashCode(getHour());
        return result;
    }
}
