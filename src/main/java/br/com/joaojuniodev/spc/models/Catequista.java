package br.com.joaojuniodev.spc.models;

import br.com.joaojuniodev.spc.models.enums.CodeVerifyCommunityOrParish;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Catequista implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column
    private String lastName;

    @Column
    private CodeVerifyCommunityOrParish code;

    public Catequista() {}

    public Catequista(Long id, String firstName, String lastName, CodeVerifyCommunityOrParish code) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
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

    public CodeVerifyCommunityOrParish getCode() {
        return code;
    }

    public void setCode(CodeVerifyCommunityOrParish code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Catequista that = (Catequista) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
