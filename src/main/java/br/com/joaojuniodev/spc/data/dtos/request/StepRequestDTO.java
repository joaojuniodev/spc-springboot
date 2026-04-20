package br.com.joaojuniodev.spc.data.dtos.request;

import br.com.joaojuniodev.spc.models.enums.StepNameEnum;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;

import java.util.Arrays;
import java.util.Objects;

public class StepRequestDTO {

    private Long id;
    private StepNameEnum stepName;
    private NameOfTheCommunityOrParishEnum communityOrParish;
    private Long[] catechistsId;

    public StepRequestDTO() {}

    public StepRequestDTO(Long id, StepNameEnum stepName, NameOfTheCommunityOrParishEnum communityOrParish, Long[] catechistsId) {
        this.id = id;
        this.stepName = stepName;
        this.communityOrParish = communityOrParish;
        this.catechistsId = catechistsId;
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

    public NameOfTheCommunityOrParishEnum getCommunityOrParish() {
        return communityOrParish;
    }

    public void setCommunityOrParish(NameOfTheCommunityOrParishEnum communityOrParish) {
        this.communityOrParish = communityOrParish;
    }

    public Long[] getCatechistsId() {
        return catechistsId;
    }

    public void setCatechistsId(Long[] catechistsId) {
        this.catechistsId = catechistsId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        StepRequestDTO that = (StepRequestDTO) o;
        return Objects.equals(getId(), that.getId()) && getStepName() == that.getStepName() && getCommunityOrParish() == that.getCommunityOrParish() && Arrays.equals(getCatechistsId(), that.getCatechistsId());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getStepName());
        result = 31 * result + Objects.hashCode(getCommunityOrParish());
        result = 31 * result + Arrays.hashCode(getCatechistsId());
        return result;
    }
}
