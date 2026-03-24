package br.com.joaojuniodev.spc.data.dtos.request;

import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Objects;

public class CatequizandoRequestDTO {

    private Long id;
    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String birthDate;
    private NameOfTheCommunityOrParishEnum nameCommunityOrParish;
    private Long etapaId;

    public CatequizandoRequestDTO() {}

    public CatequizandoRequestDTO(Long id, String firstName, String lastName, String birthDate, NameOfTheCommunityOrParishEnum nameCommunityOrParish, Long etapaId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nameCommunityOrParish = nameCommunityOrParish;
        this.etapaId = etapaId;
    }

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public NameOfTheCommunityOrParishEnum getNameCommunityOrParish() {
        return nameCommunityOrParish;
    }

    public void setNameCommunityOrParish(NameOfTheCommunityOrParishEnum nameCommunityOrParish) {
        this.nameCommunityOrParish = nameCommunityOrParish;
    }

    public Long getEtapaId() {
        return etapaId;
    }

    public void setEtapaId(Long etapaId) {
        this.etapaId = etapaId;
    }
}