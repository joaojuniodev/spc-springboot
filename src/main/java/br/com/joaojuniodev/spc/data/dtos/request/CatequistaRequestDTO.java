package br.com.joaojuniodev.spc.data.dtos.request;

import br.com.joaojuniodev.spc.models.enums.CodeVerifyCommunityOrParish;

import java.util.Objects;

public class CatequistaRequestDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer code;

    public CatequistaRequestDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public CodeVerifyCommunityOrParish getCode() {
        return CodeVerifyCommunityOrParish.values()[this.code];
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CatequistaRequestDTO that = (CatequistaRequestDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getFirstName());
        result = 31 * result + Objects.hashCode(getLastName());
        return result;
    }
}
