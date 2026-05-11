package br.com.joaojuniodev.spc.data.dtos.response.mass;

import br.com.joaojuniodev.spc.data.dtos.response.liturgicalCalendar.LiturgicalCalendarSummaryDTO;
import br.com.joaojuniodev.spc.models.enums.MassLocationEnum;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;

import java.time.LocalDateTime;
import java.util.Objects;

public class MassResponseDTO {

    private Long id;
    private String title;
    private LocalDateTime dateTime;
    private MassLocationEnum location;
    private NameOfTheCommunityOrParishEnum communityOrParish;
    private LiturgicalCalendarSummaryDTO massOfLiturgicalCalendar;
    private Boolean registeredAttendance;

    public MassResponseDTO() {}

    public MassResponseDTO(Long id, String title, LocalDateTime dateTime, MassLocationEnum location, NameOfTheCommunityOrParishEnum communityOrParish, LiturgicalCalendarSummaryDTO massOfLiturgicalCalendar, Boolean registeredAttendance) {
        this.id = id;
        this.title = title;
        this.dateTime = dateTime;
        this.location = location;
        this.communityOrParish = communityOrParish;
        this.massOfLiturgicalCalendar = massOfLiturgicalCalendar;
        this.registeredAttendance = registeredAttendance;
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

    public MassLocationEnum getLocation() {
        return location;
    }

    public void setLocation(MassLocationEnum location) {
        this.location = location;
    }

    public LiturgicalCalendarSummaryDTO getMassOfLiturgicalCalendar() {
        return massOfLiturgicalCalendar;
    }

    public void setMassOfLiturgicalCalendar(LiturgicalCalendarSummaryDTO massOfLiturgicalCalendar) {
        this.massOfLiturgicalCalendar = massOfLiturgicalCalendar;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public NameOfTheCommunityOrParishEnum getCommunityOrParish() {
        return communityOrParish;
    }

    public void setCommunityOrParish(NameOfTheCommunityOrParishEnum communityOrParish) {
        this.communityOrParish = communityOrParish;
    }

    public Boolean getRegisteredAttendance() {
        return registeredAttendance;
    }

    public void setRegisteredAttendance(Boolean registeredAttendance) {
        this.registeredAttendance = registeredAttendance;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        MassResponseDTO that = (MassResponseDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDateTime(), that.getDateTime()) && getLocation() == that.getLocation() && getCommunityOrParish() == that.getCommunityOrParish() && Objects.equals(getMassOfLiturgicalCalendar(), that.getMassOfLiturgicalCalendar()) && Objects.equals(getRegisteredAttendance(), that.getRegisteredAttendance());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getTitle());
        result = 31 * result + Objects.hashCode(getDateTime());
        result = 31 * result + Objects.hashCode(getLocation());
        result = 31 * result + Objects.hashCode(getCommunityOrParish());
        result = 31 * result + Objects.hashCode(getMassOfLiturgicalCalendar());
        result = 31 * result + Objects.hashCode(getRegisteredAttendance());
        return result;
    }
}
