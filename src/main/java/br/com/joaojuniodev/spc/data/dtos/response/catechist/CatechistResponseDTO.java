package br.com.joaojuniodev.spc.data.dtos.response.catechist;

import br.com.joaojuniodev.spc.data.dtos.response.step.StepOfCatechistResponseDTO;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;

import java.util.Objects;

public class CatechistResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private NameOfTheCommunityOrParishEnum nameCommunityOrParish;
    private StepOfCatechistResponseDTO step;

    public CatechistResponseDTO() {}

    public CatechistResponseDTO(Long id, String firstName, String lastName, NameOfTheCommunityOrParishEnum nameCommunityOrParish, StepOfCatechistResponseDTO step) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nameCommunityOrParish = nameCommunityOrParish;
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

    public StepOfCatechistResponseDTO getStep() {
        return step;
    }

    public void setStep(StepOfCatechistResponseDTO stepOfCatechistResponseDTO) {
        this.step = stepOfCatechistResponseDTO;
    }

    public NameOfTheCommunityOrParishEnum getCommunityOrParish() {
        return nameCommunityOrParish;
    }

    public void setCommunityOrParish(NameOfTheCommunityOrParishEnum nameCommunityOrParish) {
        this.nameCommunityOrParish = nameCommunityOrParish;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CatechistResponseDTO that = (CatechistResponseDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && nameCommunityOrParish == that.nameCommunityOrParish && Objects.equals(getStep(), that.getStep());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getFirstName());
        result = 31 * result + Objects.hashCode(getLastName());
        result = 31 * result + Objects.hashCode(nameCommunityOrParish);
        result = 31 * result + Objects.hashCode(getStep());
        return result;
    }
}
