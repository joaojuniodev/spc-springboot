package br.com.joaojuniodev.spc.data.dtos.response.step;

import br.com.joaojuniodev.spc.data.dtos.response.catechist.CatequistaResponseByEtapaDTO;
import br.com.joaojuniodev.spc.data.dtos.response.catechumens.CatechumenResponseByStepDTO;
import br.com.joaojuniodev.spc.models.enums.EtapaEnum;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EtapaResponseDTO {

    private Long id;
    private EtapaEnum etapa;
    private NameOfTheCommunityOrParishEnum communityOrParish;
    private List<CatequistaResponseByEtapaDTO> catequistas;
    private List<CatechumenResponseByStepDTO> catequizandos = new ArrayList<>();

    public EtapaResponseDTO() {}

    public EtapaResponseDTO(Long id, EtapaEnum etapa, NameOfTheCommunityOrParishEnum communityOrParish, List<CatequistaResponseByEtapaDTO> catequistas, List<CatechumenResponseByStepDTO> catequizandos) {
        this.id = id;
        this.etapa = etapa;
        this.communityOrParish = communityOrParish;
        this.catequistas = catequistas;
        this.catequizandos = catequizandos;
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

    public List<CatequistaResponseByEtapaDTO> getCatequistas() {
        return catequistas;
    }

    public void setCatequistas(List<CatequistaResponseByEtapaDTO> catequistas) {
        this.catequistas = catequistas;
    }

    public List<CatechumenResponseByStepDTO> getCatequizandos() {
        return catequizandos;
    }

    public void setCatequizandos(List<CatechumenResponseByStepDTO> catequizandos) {
        this.catequizandos = catequizandos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        EtapaResponseDTO that = (EtapaResponseDTO) o;
        return Objects.equals(getId(), that.getId()) && getEtapa() == that.getEtapa() && getCommunityOrParish() == that.getCommunityOrParish() && Objects.equals(getCatequistas(), that.getCatequistas()) && Objects.equals(getCatequizandos(), that.getCatequizandos());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getEtapa());
        result = 31 * result + Objects.hashCode(getCommunityOrParish());
        result = 31 * result + Objects.hashCode(getCatequistas());
        result = 31 * result + Objects.hashCode(getCatequizandos());
        return result;
    }
}