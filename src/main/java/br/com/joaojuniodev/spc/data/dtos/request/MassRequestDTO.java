package br.com.joaojuniodev.spc.data.dtos.request;

import br.com.joaojuniodev.spc.models.enums.MassLocationEnum;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Objects;

public class MassRequestDTO {

    private Long id;
    private Long massOfLiturgicalCalendarId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private String dateTime;
    private MassLocationEnum location;
    private NameOfTheCommunityOrParishEnum communityOrParish;
    private Boolean registeredAttendance;

    public MassRequestDTO() {}

    public MassRequestDTO(Long id, Long massOfLiturgicalCalendarId, String dateTime, MassLocationEnum location, NameOfTheCommunityOrParishEnum communityOrParish) {
        this.id = id;
        this.massOfLiturgicalCalendarId = massOfLiturgicalCalendarId;
        this.dateTime = dateTime;
        this.location = location;
        this.communityOrParish = communityOrParish;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMassOfLiturgicalCalendarId() {
        return massOfLiturgicalCalendarId;
    }

    public void setMassOfLiturgicalCalendarId(Long massOfLiturgicalCalendarId) {
        this.massOfLiturgicalCalendarId = massOfLiturgicalCalendarId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public MassLocationEnum getLocation() {
        return location;
    }

    public void setLocation(MassLocationEnum location) {
        this.location = location;
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

        MassRequestDTO that = (MassRequestDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getMassOfLiturgicalCalendarId(), that.getMassOfLiturgicalCalendarId()) && Objects.equals(getDateTime(), that.getDateTime()) && getLocation() == that.getLocation() && getCommunityOrParish() == that.getCommunityOrParish() && Objects.equals(getRegisteredAttendance(), that.getRegisteredAttendance());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getMassOfLiturgicalCalendarId());
        result = 31 * result + Objects.hashCode(getDateTime());
        result = 31 * result + Objects.hashCode(getLocation());
        result = 31 * result + Objects.hashCode(getCommunityOrParish());
        result = 31 * result + Objects.hashCode(getRegisteredAttendance());
        return result;
    }
}