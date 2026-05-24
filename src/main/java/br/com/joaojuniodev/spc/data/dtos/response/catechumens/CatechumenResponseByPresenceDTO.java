package br.com.joaojuniodev.spc.data.dtos.response.catechumens;

import br.com.joaojuniodev.spc.data.dtos.response.catechist.CatechistSummaryDTO;
import br.com.joaojuniodev.spc.models.enums.StepNameEnum;

import java.util.List;

public class CatechumenResponseByPresenceDTO {

    private Long id;
    private String fullName;
    private StepNameEnum step;
    private List<CatechistSummaryDTO> catechists;

    public CatechumenResponseByPresenceDTO() {}

    public CatechumenResponseByPresenceDTO(Long id, String fullName, StepNameEnum step, List<CatechistSummaryDTO> catechists) {
        this.id = id;
        this.fullName = fullName;
        this.step = step;
        this.catechists = catechists;
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

    public StepNameEnum getStep() {
        return step;
    }

    public void setStep(StepNameEnum step) {
        this.step = step;
    }

    public List<CatechistSummaryDTO> getCatechists() {
        return catechists;
    }

    public void setCatechists(List<CatechistSummaryDTO> catechists) {
        this.catechists = catechists;
    }
}
