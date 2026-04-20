package br.com.joaojuniodev.spc.data.dtos.response.step;

import br.com.joaojuniodev.spc.data.dtos.response.catechist.CatechistResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechist.CatechistSummaryDTO;
import br.com.joaojuniodev.spc.models.enums.StepNameEnum;

import java.util.List;
import java.util.Objects;

public class StepByCatechumenResponseDTO {

    private Long id;
    private StepNameEnum stepName;
    private List<CatechistSummaryDTO> catechists;

    public StepByCatechumenResponseDTO(Long id, StepNameEnum stepName, List<CatechistSummaryDTO> catechists) {
        this.id = id;
        this.stepName = stepName;
        this.catechists = catechists;
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

    public List<CatechistSummaryDTO> getCatechists() {
        return catechists;
    }

    public void setCatechists(List<CatechistSummaryDTO> catechists) {
        this.catechists = catechists;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        StepByCatechumenResponseDTO that = (StepByCatechumenResponseDTO) o;
        return Objects.equals(getId(), that.getId()) && getStepName() == that.getStepName() && Objects.equals(getCatechists(), that.getCatechists());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getStepName());
        result = 31 * result + Objects.hashCode(getCatechists());
        return result;
    }
}
