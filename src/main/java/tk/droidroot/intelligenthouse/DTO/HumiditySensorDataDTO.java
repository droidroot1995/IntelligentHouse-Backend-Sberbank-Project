package tk.droidroot.intelligenthouse.DTO;

import tk.droidroot.intelligenthouse.Models.HumiditySensorEntity;

import java.util.Objects;

public class HumiditySensorDataDTO {

    private Long id;
    private Long humiditySensor;
    private Double data;
    private String date;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        HumiditySensorDataDTO humiditySensorDataDTO = (HumiditySensorDataDTO) o;

        return Objects.equals(id, humiditySensorDataDTO.id) &&
                Objects.equals(humiditySensor, humiditySensorDataDTO.humiditySensor) &&
                Objects.equals(data, humiditySensorDataDTO.data) &&
                Objects.equals(date, humiditySensorDataDTO.date);
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

    public Long getHumiditySensor() {
        return humiditySensor;
    }

    public void setHumiditySensor(Long humiditySensor) {
        this.humiditySensor = humiditySensor;
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
