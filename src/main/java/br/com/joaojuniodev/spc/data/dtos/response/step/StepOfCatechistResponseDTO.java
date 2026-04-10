package br.com.joaojuniodev.spc.data.dtos.response.step;

import br.com.joaojuniodev.spc.models.enums.EtapaEnum;

public class StepOfCatechistResponseDTO {

    private Long id;
    private EtapaEnum stepEnum;

    public StepOfCatechistResponseDTO() {}

    public StepOfCatechistResponseDTO(Long id, EtapaEnum stepEnum) {
        this.id = id;
        this.stepEnum = stepEnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EtapaEnum getStepEnum() {
        return stepEnum;
    }

    public void setStepEnum(EtapaEnum stepEnum) {
        this.stepEnum = stepEnum;
    }
}
