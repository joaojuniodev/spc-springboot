package br.com.joaojuniodev.spc.data.dtos.request;

import br.com.joaojuniodev.spc.models.enums.EtapaEnum;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;

import java.util.Arrays;
import java.util.Objects;

public class EtapaRequestDTO {

    private Long id;
    private EtapaEnum etapa;
    private NameOfTheCommunityOrParishEnum communityOrParish;
    private Long[] catequistasId;

    public EtapaRequestDTO() {}

    public EtapaRequestDTO(Long id, EtapaEnum etapa, NameOfTheCommunityOrParishEnum communityOrParish, Long[] catequistasId) {
        this.id = id;
        this.etapa = etapa;
        this.communityOrParish = communityOrParish;
        this.catequistasId = catequistasId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EtapaEnum getEtapa() {
        return etapa;
    }

    public void setEtapa(EtapaEnum etapa) {
        this.etapa = etapa;
    }

    public NameOfTheCommunityOrParishEnum getCommunityOrParish() {
        return communityOrParish;
    }

    public void setCommunityOrParish(NameOfTheCommunityOrParishEnum communityOrParish) {
        this.communityOrParish = communityOrParish;
    }

    public Long[] getCatequistasId() {
        return catequistasId;
    }

    public void setCatequistasId(Long[] catequistasId) {
        this.catequistasId = catequistasId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        EtapaRequestDTO that = (EtapaRequestDTO) o;
        return Objects.equals(getId(), that.getId()) && getEtapa() == that.getEtapa() && getCommunityOrParish() == that.getCommunityOrParish() && Arrays.equals(getCatequistasId(), that.getCatequistasId());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getEtapa());
        result = 31 * result + Objects.hashCode(getCommunityOrParish());
        result = 31 * result + Arrays.hashCode(getCatequistasId());
        return result;
    }
}
