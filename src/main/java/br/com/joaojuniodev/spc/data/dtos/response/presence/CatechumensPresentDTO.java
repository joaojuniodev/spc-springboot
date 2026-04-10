package br.com.joaojuniodev.spc.data.dtos.response.presence;

import br.com.joaojuniodev.spc.data.dtos.response.catechumens.CatechumenResponseDTO;
import br.com.joaojuniodev.spc.models.Catechumen;

import java.util.ArrayList;
import java.util.List;

public class CatechumensPresentDTO {

    List<CatechumenResponseDTO> catechumens = new ArrayList<>();

    public CatechumensPresentDTO() {}

    public CatechumensPresentDTO(List<CatechumenResponseDTO> catechumens) {
        this.catechumens = catechumens;
    }

    public List<CatechumenResponseDTO> getCatechumens() {
        return catechumens;
    }

    public void setCatechumens(List<CatechumenResponseDTO> catechumens) {
        this.catechumens = catechumens;
    }
}
