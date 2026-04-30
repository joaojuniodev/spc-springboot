package br.com.joaojuniodev.spc.data.dtos.request.catechist;

import br.com.joaojuniodev.spc.data.dtos.request.StepRequestDTO;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;

import java.util.Objects;

public class CatechistRequestDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private NameOfTheCommunityOrParishEnum communityOrParish;
    private StepRequestDTO step;

    public CatechistRequestDTO() {}

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

    public NameOfTheCommunityOrParishEnum getCommunityOrParish() {
        return communityOrParish;
    }

    public void setCommunityOrParish(NameOfTheCommunityOrParishEnum communityOrParish) {
        this.communityOrParish = communityOrParish;
    }

    public StepRequestDTO getStep() {
        return step;
    }

    public void setStep(StepRequestDTO step) {
        this.step = step;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CatechistRequestDTO that = (CatechistRequestDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && getCommunityOrParish() == that.getCommunityOrParish() && Objects.equals(getStep(), that.getStep());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getFirstName());
        result = 31 * result + Objects.hashCode(getLastName());
        result = 31 * result + Objects.hashCode(getCommunityOrParish());
        result = 31 * result + Objects.hashCode(getStep());
        return result;
    }
}
