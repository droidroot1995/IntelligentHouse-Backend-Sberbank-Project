package tk.droidroot.intelligenthouse.DTO;

import tk.droidroot.intelligenthouse.Models.TemperatureSensorEntity;
import java.util.Objects;

public class TemperatureSensorDataDTO {

    private Long id;
    private Long temperatureSensor;
    private Double data;
    private String date;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TemperatureSensorDataDTO temperatureSensorDataDTO = (TemperatureSensorDataDTO) o;

        return Objects.equals(id, temperatureSensorDataDTO.id) &&
                Objects.equals(temperatureSensor, temperatureSensorDataDTO.temperatureSensor) &&
                Objects.equals(data, temperatureSensorDataDTO.data) &&
                Objects.equals(date, temperatureSensorDataDTO.date);
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

    public Long getTemperatureSensor() {
        return temperatureSensor;
    }

    public void setTemperatureSensor(Long temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
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
