package br.com.joaojuniodev.spc.data.dtos.response;

import br.com.joaojuniodev.spc.models.Etapa;

import java.time.LocalDate;
import java.util.Objects;

public class CatequizandoResponseDTO {

    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private Etapa etapa;

    public CatequizandoResponseDTO() {}

    public CatequizandoResponseDTO(Long id, String fullName, LocalDate birthDate, Etapa etapa) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.etapa = etapa;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CatequizandoResponseDTO that = (CatequizandoResponseDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getFullName(), that.getFullName()) && Objects.equals(getBirthDate(), that.getBirthDate()) && Objects.equals(getEtapa(), that.getEtapa());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getFullName());
        result = 31 * result + Objects.hashCode(getBirthDate());
        result = 31 * result + Objects.hashCode(getEtapa());
        return result;
    }
}
