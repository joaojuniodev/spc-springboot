package br.com.joaojuniodev.spc.data.dtos.security;

import java.util.Objects;

public class RegisterUserDTO {

    private String username;
    private String password;
    private String fullName;

    public RegisterUserDTO() {}

    public RegisterUserDTO(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        RegisterUserDTO that = (RegisterUserDTO) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getFullName(), that.getFullName());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getUsername());
        result = 31 * result + Objects.hashCode(getPassword());
        result = 31 * result + Objects.hashCode(getFullName());
        return result;
    }
}
