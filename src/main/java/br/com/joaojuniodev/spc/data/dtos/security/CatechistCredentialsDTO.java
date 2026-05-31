package br.com.joaojuniodev.spc.data.dtos.security;

import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;

import java.util.Objects;

public class CatechistCredentialsDTO {

    private Long catechistId;
    private NameOfTheCommunityOrParishEnum communityOrParish;

    public CatechistCredentialsDTO() {}

    public CatechistCredentialsDTO(Long catechistId, NameOfTheCommunityOrParishEnum communityOrParish) {
        this.catechistId = catechistId;
        this.communityOrParish = communityOrParish;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CatechistCredentialsDTO that = (CatechistCredentialsDTO) o;
        return Objects.equals(getCatechistId(), that.getCatechistId()) && getCommunityOrParish() == that.getCommunityOrParish();
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getCatechistId());
        result = 31 * result + Objects.hashCode(getCommunityOrParish());
        return result;
    }
}
