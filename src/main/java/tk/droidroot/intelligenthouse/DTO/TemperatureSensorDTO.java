package tk.droidroot.intelligenthouse.DTO;

import java.util.Objects;

public class TemperatureSensorDTO {
    
    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TemperatureSensorDTO temperatureSensorDTO = (TemperatureSensorDTO) o;

        return Objects.equals(id, temperatureSensorDTO.id) && Objects.equals(name, temperatureSensorDTO.name);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
