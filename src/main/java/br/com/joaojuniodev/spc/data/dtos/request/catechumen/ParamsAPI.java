package br.com.joaojuniodev.spc.data.dtos.request.catechumen;

import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;

public class ParamsAPI {

    private String fullName;
    private Long stepId;
    private Long catechistId;
    private NameOfTheCommunityOrParishEnum communityOrParish;

    public ParamsAPI() {}

    public ParamsAPI(String fullName, Long stepId, Long catechistId, NameOfTheCommunityOrParishEnum communityOrParish) {
        this.fullName = fullName;
        this.stepId = stepId;
        this.catechistId = catechistId;
        this.communityOrParish = communityOrParish;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getStepId() {
        return stepId;
    }

    public void setStepId(Long stepId) {
        this.stepId = stepId;
    }

    public Long getCatechistId() {
        return catechistId;
    }

    public void setCatechistId(Long catechistId) {
        this.catechistId = catechistId;
    }

    public NameOfTheCommunityOrParishEnum getCommunityOrParish() {
        return communityOrParish;
    }

    public void setCommunityOrParish(NameOfTheCommunityOrParishEnum communityOrParish) {
        this.communityOrParish = communityOrParish;
    }
}
