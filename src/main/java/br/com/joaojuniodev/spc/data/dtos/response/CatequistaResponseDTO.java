package br.com.joaojuniodev.spc.data.dtos.response;

import java.util.Objects;

public class CatequistaResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private StepOfCatechistResponseDTO stepOfCatechistResponseDTO;

    public CatequistaResponseDTO() {}

    public CatequistaResponseDTO(Long id, String firstName, String lastName, StepOfCatechistResponseDTO stepOfCatechistResponseDTO) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stepOfCatechistResponseDTO = stepOfCatechistResponseDTO;
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

    public StepOfCatechistResponseDTO getStepOfCatechistResponseDTO() {
        return stepOfCatechistResponseDTO;
    }

    public void setStepOfCatechistResponseDTO(StepOfCatechistResponseDTO stepOfCatechistResponseDTO) {
        this.stepOfCatechistResponseDTO = stepOfCatechistResponseDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CatequistaResponseDTO that = (CatequistaResponseDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getStepOfCatechistResponseDTO(), that.getStepOfCatechistResponseDTO());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getFirstName());
        result = 31 * result + Objects.hashCode(getLastName());
        result = 31 * result + Objects.hashCode(getStepOfCatechistResponseDTO());
        return result;
    }
}
