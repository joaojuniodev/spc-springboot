package br.com.joaojuniodev.spc.data.dtos.response.catechumens;

import br.com.joaojuniodev.spc.data.dtos.response.catechist.CatequistaResponseByCatequizandoDTO;
import br.com.joaojuniodev.spc.models.enums.EtapaEnum;

import java.util.List;

public class CatechumenResponseByPresenceDTO {

    private Long id;
    private String fullName;
    private EtapaEnum step;
    private List<CatequistaResponseByCatequizandoDTO> catechists;

    public CatechumenResponseByPresenceDTO() {}

    public CatechumenResponseByPresenceDTO(Long id, String fullName, EtapaEnum step, List<CatequistaResponseByCatequizandoDTO> catechists) {
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
