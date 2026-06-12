package br.com.joaojuniodev.spc.data.dtos.response.presence;

public class PresenceRegisterRetroactiveDTO {

    private String username;
    private String catechistFullName;
    private String catechumenFullName;
    private String justification;

    public PresenceRegisterRetroactiveDTO() {}

    public PresenceRegisterRetroactiveDTO(String username, String catechistFullName, String catechumenFullName, String justification) {
        this.username = username;
        this.catechistFullName = catechistFullName;
        this.catechumenFullName = catechumenFullName;
        this.justification = justification;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCatechistFullName() {
        return catechistFullName;
    }

    public void setCatechistFullName(String catechistFullName) {
        this.catechistFullName = catechistFullName;
    }

    public String getCatechumenFullName() {
        return catechumenFullName;
    }

    public void setCatechumenFullName(String catechumenFullName) {
        this.catechumenFullName = catechumenFullName;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }
}
