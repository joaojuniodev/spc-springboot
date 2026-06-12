package br.com.joaojuniodev.spc.data.dtos.response.presence;

public class PresenceRegisterDTO {

    private String username;
    private String catechistFullname;
    private Integer numberOfCatechumens;

    public PresenceRegisterDTO() {}

    public PresenceRegisterDTO(String username, String catechistFullname, Integer numberOfCatechumens) {
        this.username = username;
        this.catechistFullname = catechistFullname;
        this.numberOfCatechumens = numberOfCatechumens;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCatechistFullname() {
        return catechistFullname;
    }

    public void setCatechistFullname(String catechistFullname) {
        this.catechistFullname = catechistFullname;
    }

    public Integer getNumberOfCatechumens() {
        return numberOfCatechumens;
    }

    public void setNumberOfCatechumens(Integer numberOfCatechumens) {
        this.numberOfCatechumens = numberOfCatechumens;
    }
}
