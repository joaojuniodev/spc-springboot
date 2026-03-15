package br.com.joaojuniodev.spc.data.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Objects;

public class MissaRequestDTO {

    private Long id;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private String dateTime;
    private Boolean registeredAttendance;

    public MissaRequestDTO() {}

    public MissaRequestDTO(Long id, String title, String dateTime) {
        this.id = id;
        this.title = title;
        this.dateTime = dateTime;
        this.registeredAttendance = false;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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

        MissaRequestDTO that = (MissaRequestDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDateTime(), that.getDateTime()) && Objects.equals(getRegisteredAttendance(), that.getRegisteredAttendance());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getTitle());
        result = 31 * result + Objects.hashCode(getDateTime());
        result = 31 * result + Objects.hashCode(getRegisteredAttendance());
        return result;
    }
}
