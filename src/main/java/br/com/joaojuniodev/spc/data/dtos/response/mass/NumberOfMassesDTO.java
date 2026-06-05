package br.com.joaojuniodev.spc.data.dtos.response.mass;

import java.util.Objects;

public class NumberOfMassesDTO {

    private Integer totalMasses;
    private Integer totalMassesToThisToday;

    public NumberOfMassesDTO() {}

    public NumberOfMassesDTO(Integer totalMasses, Integer totalMassesToThisToday) {
        this.totalMasses = totalMasses;
        this.totalMassesToThisToday = totalMassesToThisToday;
    }

    public Integer getTotalMassesToThisToday() {
        return totalMassesToThisToday;
    }

    public void setTotalMassesToThisToday(Integer totalMassesToThisToday) {
        this.totalMassesToThisToday = totalMassesToThisToday;
    }

    public Integer getTotalMasses() {
        return totalMasses;
    }

    public void setTotalMasses(Integer totalMasses) {
        this.totalMasses = totalMasses;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        NumberOfMassesDTO that = (NumberOfMassesDTO) o;
        return Objects.equals(getTotalMasses(), that.getTotalMasses()) && Objects.equals(getTotalMassesToThisToday(), that.getTotalMassesToThisToday());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getTotalMasses());
        result = 31 * result + Objects.hashCode(getTotalMassesToThisToday());
        return result;
    }
}
