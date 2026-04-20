package br.com.joaojuniodev.spc.data.dtos.response.step;

import br.com.joaojuniodev.spc.data.dtos.response.catechist.CatechistSummaryDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechumens.CatechumenResponseByStepDTO;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.models.enums.StepNameEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StepResponseDTO {

    private Long id;
    private StepNameEnum stepName;
    private NameOfTheCommunityOrParishEnum communityOrParish;
    private List<CatechistSummaryDTO> catechists;
    private List<CatechumenResponseByStepDTO> catechumens = new ArrayList<>();

    public StepResponseDTO() {}

    public StepResponseDTO(Long id, StepNameEnum stepName, NameOfTheCommunityOrParishEnum communityOrParish, List<CatechistSummaryDTO> catechists, List<CatechumenResponseByStepDTO> catechumens) {
        this.id = id;
        this.stepName = stepName;
        this.communityOrParish = communityOrParish;
        this.catechists = catechists;
        this.catechumens = catechumens;
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

    public List<CatechistSummaryDTO> getCatechists() {
        return catechists;
    }

    public void setCatechists(List<CatechistSummaryDTO> catechists) {
        this.catechists = catechists;
    }

    public List<CatechumenResponseByStepDTO> getCatechumens() {
        return catechumens;
    }

    public void setCatechumens(List<CatechumenResponseByStepDTO> catechumens) {
        this.catechumens = catechumens;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        StepResponseDTO that = (StepResponseDTO) o;
        return Objects.equals(getId(), that.getId()) && getStepName() == that.getStepName() && getCommunityOrParish() == that.getCommunityOrParish() && Objects.equals(getCatechists(), that.getCatechists()) && Objects.equals(getCatechumens(), that.getCatechumens());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getStepName());
        result = 31 * result + Objects.hashCode(getCommunityOrParish());
        result = 31 * result + Objects.hashCode(getCatechists());
        result = 31 * result + Objects.hashCode(getCatechumens());
        return result;
    }
}