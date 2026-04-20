package br.com.joaojuniodev.spc.data.dtos.response.step;

import br.com.joaojuniodev.spc.models.enums.StepNameEnum;

import java.util.Objects;

public class StepOfCatechistResponseDTO {

    private Long id;
    private StepNameEnum stepName;

    public StepOfCatechistResponseDTO() {}

    public StepOfCatechistResponseDTO(Long id, StepNameEnum stepName) {
        this.id = id;
        this.stepName = stepName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StepNameEnum getStepName() {
        return stepName;
    }

    public void setStepName(StepNameEnum stepName) {
        this.stepName = stepName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        StepOfCatechistResponseDTO that = (StepOfCatechistResponseDTO) o;
        return Objects.equals(getId(), that.getId()) && getStepName() == that.getStepName();
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getStepName());
        return result;
    }
}
