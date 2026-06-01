package br.com.joaojuniodev.spc.data.dtos.response.user;

import java.util.Objects;

public class UserSummaryDTO {

    private String username;
    private String fullName;

    public UserSummaryDTO() {}

    public UserSummaryDTO(String username, String fullName) {
        this.username = username;
        this.fullName = fullName;
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

        UserSummaryDTO that = (UserSummaryDTO) o;
        return Objects.equals(username, that.username) && Objects.equals(getFullName(), that.getFullName());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(username);
        result = 31 * result + Objects.hashCode(getFullName());
        return result;
    }
}
