package br.com.joaojuniodev.spc.data.dtos.response;

public class CatequistaResponseByCatequizandoDTO {

    private String fullName;

    public CatequistaResponseByCatequizandoDTO() {}

    public CatequistaResponseByCatequizandoDTO(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
