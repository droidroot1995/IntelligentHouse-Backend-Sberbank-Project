package tk.droidroot.intelligenthouse.DTO;

import java.util.Objects;

public class HumiditySensorDataDTO {

    private Long id;
    private Double data;
    private String date;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        HumiditySensorDataDTO HumiditySensorDataDTO = (HumiditySensorDataDTO) o;

        return Objects.equals(id, HumiditySensorDataDTO.id)
                && Objects.equals(data, HumiditySensorDataDTO.data) && Objects.equals(date, HumiditySensorDataDTO.date);
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
