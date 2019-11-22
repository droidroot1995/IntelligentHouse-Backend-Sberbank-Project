package tk.droidroot.intelligenthouse.DTO;

import tk.droidroot.intelligenthouse.Models.LightSensorEntity;
import java.util.Objects;

public class LightSensorDataDTO {

    private Long id;
    private Long lightSensor;
    private Double data;
    private String date;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LightSensorDataDTO lightSensorDataDTO = (LightSensorDataDTO) o;

        return Objects.equals(id, lightSensorDataDTO.id) &&
                Objects.equals(lightSensor, lightSensorDataDTO.lightSensor) &&
                Objects.equals(data, lightSensorDataDTO.data) &&
                Objects.equals(date, lightSensorDataDTO.date);
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
    public Long getLightSensor() {
        return lightSensor;
    }

    public void setLightSensor(Long lightSensor) {
        this.lightSensor = lightSensor;
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
