package br.com.joaojuniodev.spc.data.dtos.request;

import java.time.LocalDate;
import java.util.Objects;

public class CatequizandoRequestDTO {

    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private Long etapaId;

    public CatequizandoRequestDTO() {}

    public CatequizandoRequestDTO(Long id, String fullName, LocalDate birthDate, Long etapaId) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.etapaId = etapaId;
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

    public Long getEtapaId() {
        return etapaId;
    }

    public void setEtapaId(Long etapaId) {
        this.etapaId = etapaId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CatequizandoRequestDTO that = (CatequizandoRequestDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getFullName(), that.getFullName()) && Objects.equals(getBirthDate(), that.getBirthDate());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getFullName());
        result = 31 * result + Objects.hashCode(getBirthDate());
        return result;
    }
}
