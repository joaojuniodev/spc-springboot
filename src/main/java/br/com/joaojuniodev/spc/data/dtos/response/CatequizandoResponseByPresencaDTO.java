package br.com.joaojuniodev.spc.data.dtos.response;

import br.com.joaojuniodev.spc.models.enums.EtapaEnum;

import java.util.List;

public class CatequizandoResponseByPresencaDTO {

    private String fullName;
    private EtapaEnum step;
    private List<CatequistaResponseByCatequizandoDTO> catechists;

    public CatequizandoResponseByPresencaDTO() {}

    public CatequizandoResponseByPresencaDTO(String fullName, EtapaEnum step, List<CatequistaResponseByCatequizandoDTO> catechists) {
        this.fullName = fullName;
        this.step = step;
        this.catechists = catechists;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public EtapaEnum getStep() {
        return step;
    }

    public void setStep(EtapaEnum step) {
        this.step = step;
    }

    public List<CatequistaResponseByCatequizandoDTO> getCatechists() {
        return catechists;
    }

    public void setCatechists(List<CatequistaResponseByCatequizandoDTO> catechists) {
        this.catechists = catechists;
    }
}
