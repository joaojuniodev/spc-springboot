package br.com.joaojuniodev.spc.data.dtos.response.catechumen;

public class CatechumenDashboardResponseDTO {

    private Integer total;
    private Double mediumFrequency;
    private Integer attention;
    private Integer totalMasses;
    private Integer massesOccurred;

    public CatechumenDashboardResponseDTO() {}

    public CatechumenDashboardResponseDTO(Integer total, Double mediumFrequency, Integer attention, Integer totalMasses, Integer massesOccurred) {
        this.total = total;
        this.mediumFrequency = mediumFrequency;
        this.attention = attention;
        this.totalMasses = totalMasses;
        this.massesOccurred = massesOccurred;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getMediumFrequency() {
        return mediumFrequency;
    }

    public void setMediumFrequency(Double mediumFrequency) {
        this.mediumFrequency = mediumFrequency;
    }

    public Integer getAttention() {
        return attention;
    }

    public void setAttention(Integer attention) {
        this.attention = attention;
    }

    public Integer getTotalMasses() {
        return totalMasses;
    }

    public void setTotalMasses(Integer totalMasses) {
        this.totalMasses = totalMasses;
    }

    public Integer getMassesOccurred() {
        return massesOccurred;
    }

    public void setMassesOccurred(Integer massesOccurred) {
        this.massesOccurred = massesOccurred;
    }
}
