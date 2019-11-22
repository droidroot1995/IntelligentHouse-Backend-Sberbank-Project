package tk.droidroot.intelligenthouse.DTO;

import tk.droidroot.intelligenthouse.Models.GasSensorEntity;

import java.util.Objects;

public class GasSensorDataDTO {

    private Long id;
    private Long gasSensor;
    private Double data;
    private String date;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GasSensorDataDTO gasSensorDataDTO = (GasSensorDataDTO) o;

        return Objects.equals(id, gasSensorDataDTO.id) &&
                Objects.equals(gasSensor, gasSensorDataDTO.gasSensor) &&
                Objects.equals(data, gasSensorDataDTO.data) &&
                Objects.equals(date, gasSensorDataDTO.date);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGasSensor() {
        return gasSensor;
    }

    public void setGasSensor(Long gasSensor) {
        this.gasSensor = gasSensor;
    }

    public Double getData() {
        return data;
    }

    public void setData(Double data) {
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
