package br.com.joaojuniodev.spc.models;

import br.com.joaojuniodev.spc.models.enums.MassLocationEnum;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "masses")
public class Mass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;

    @Column
    private Boolean registeredAttendance;

    @Enumerated(EnumType.STRING)
    @Column
    private NameOfTheCommunityOrParishEnum nameCommunityOrParish;

    @Enumerated(EnumType.STRING)
    @Column
    private MassLocationEnum location;

    @OneToOne
    private LiturgicalCalendar massOfLiturgicalCalendar;

    @OneToMany(mappedBy = "mass")
    private Set<Presence> presences;

    public Mass() {}

    public Mass(Long id, String title, LocalDateTime dateTime, NameOfTheCommunityOrParishEnum nameCommunityOrParish, MassLocationEnum location, LiturgicalCalendar massOfLiturgicalCalendar) {
        this.id = id;
        this.title = title;
        this.dateTime = dateTime;
        this.nameCommunityOrParish = nameCommunityOrParish;
        this.location = location;
        this.massOfLiturgicalCalendar = massOfLiturgicalCalendar;
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

    public Set<Presence> getPresences() {
        return presences;
    }

    public void setPresences(Set<Presence> presences) {
        this.presences = presences;
    }

    public Boolean getRegisteredAttendance() {
        return registeredAttendance;
    }

    public void setRegisteredAttendance(Boolean registeredAttendance) {
        this.registeredAttendance = registeredAttendance;
    }

    public NameOfTheCommunityOrParishEnum getNameCommunityOrParish() {
        return nameCommunityOrParish;
    }

    public void setNameCommunityOrParish(NameOfTheCommunityOrParishEnum nameCommunityOrParish) {
        this.nameCommunityOrParish = nameCommunityOrParish;
    }

    public MassLocationEnum getLocation() {
        return location;
    }

    public void setLocation(MassLocationEnum location) {
        this.location = location;
    }

    public LiturgicalCalendar getMassOfLiturgicalCalendar() {
        return massOfLiturgicalCalendar;
    }

    public void setMassOfLiturgicalCalendar(LiturgicalCalendar massOfLiturgicalCalendar) {
        this.massOfLiturgicalCalendar = massOfLiturgicalCalendar;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Mass missa = (Mass) o;
        return Objects.equals(getId(), missa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
