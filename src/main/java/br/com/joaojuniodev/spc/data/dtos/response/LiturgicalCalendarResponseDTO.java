package br.com.joaojuniodev.spc.data.dtos.response;

import java.time.LocalDate;
import java.util.Objects;

public class LiturgicalCalendarResponseDTO {

    private Long id;
    private String title;
    private LocalDate date;

    public LiturgicalCalendarResponseDTO() {}

    public LiturgicalCalendarResponseDTO(Long id, String title, LocalDate date) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        LiturgicalCalendarResponseDTO that = (LiturgicalCalendarResponseDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getTitle());
        result = 31 * result + Objects.hashCode(getDate());
        return result;
    }
}