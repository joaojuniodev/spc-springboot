package br.com.joaojuniodev.spc.data.dtos.response;

import br.com.joaojuniodev.spc.models.Catequista;
import br.com.joaojuniodev.spc.models.Catequizando;
import br.com.joaojuniodev.spc.models.enums.EtapaEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EtapaResponseDTO {

    private Long id;
    private EtapaEnum etapa;
    private CatequistaResponseDTO catequista;
    private List<CatequizandoResponseDTO> catequizandos = new ArrayList<>();

    public EtapaResponseDTO() {}

    public EtapaResponseDTO(Long id, EtapaEnum etapa, CatequistaResponseDTO catequista, List<CatequizandoResponseDTO> catequizandos) {
        this.id = id;
        this.etapa = etapa;
        this.catequista = catequista;
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

    public CatequistaResponseDTO getCatequista() {
        return catequista;
    }

    public void setCatequista(CatequistaResponseDTO catequista) {
        this.catequista = catequista;
    }

    public List<CatequizandoResponseDTO> getCatequizandos() {
        return catequizandos;
    }

    public void setCatequizandos(List<CatequizandoResponseDTO> catequizandos) {
        this.catequizandos = catequizandos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        EtapaResponseDTO that = (EtapaResponseDTO) o;
        return Objects.equals(getId(), that.getId()) && getEtapa() == that.getEtapa() && Objects.equals(getCatequista(), that.getCatequista()) && Objects.equals(getCatequizandos(), that.getCatequizandos());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getEtapa());
        result = 31 * result + Objects.hashCode(getCatequista());
        result = 31 * result + Objects.hashCode(getCatequizandos());
        return result;
    }
}
