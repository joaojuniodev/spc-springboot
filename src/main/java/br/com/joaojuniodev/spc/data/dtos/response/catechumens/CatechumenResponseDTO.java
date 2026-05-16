package br.com.joaojuniodev.spc.data.dtos.response.catechumens;

import br.com.joaojuniodev.spc.data.dtos.response.step.StepByCatechumenResponseDTO;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;

import java.time.LocalDate;
import java.util.Objects;

public class CatechumenResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private NameOfTheCommunityOrParishEnum communityOrParish;
    private StepByCatechumenResponseDTO step;
    private Integer currentFrequency;
    private Integer totalFrequency;

    public CatechumenResponseDTO() {}

    public CatechumenResponseDTO(Long id, String firstName, String lastName, LocalDate birthDate, NameOfTheCommunityOrParishEnum communityOrParish, StepByCatechumenResponseDTO step) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.communityOrParish = communityOrParish;
        this.step = step;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public NameOfTheCommunityOrParishEnum getCommunityOrParish() {
        return communityOrParish;
    }

    public void setCommunityOrParish(NameOfTheCommunityOrParishEnum communityOrParish) {
        this.communityOrParish = communityOrParish;
    }

    public StepByCatechumenResponseDTO getStep() {
        return step;
    }

    public void setStep(StepByCatechumenResponseDTO step) {
        this.step = step;
    }

    public Integer getCurrentFrequency() {
        return currentFrequency;
    }

    public void setCurrentFrequency(Integer currentFrequency) {
        this.currentFrequency = currentFrequency;
    }

    public Integer getTotalFrequency() {
        return totalFrequency;
    }

    public void setTotalFrequency(Integer totalFrequency) {
        this.totalFrequency = totalFrequency;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CatechumenResponseDTO that = (CatechumenResponseDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getBirthDate(), that.getBirthDate()) && getCommunityOrParish() == that.getCommunityOrParish() && Objects.equals(getStep(), that.getStep());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getFirstName());
        result = 31 * result + Objects.hashCode(getLastName());
        result = 31 * result + Objects.hashCode(getBirthDate());
        result = 31 * result + Objects.hashCode(getCommunityOrParish());
        result = 31 * result + Objects.hashCode(getStep());
        return result;
    }
}
