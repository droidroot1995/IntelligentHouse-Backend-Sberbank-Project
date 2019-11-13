package tk.droidroot.intelligenthouse.DTO;

import java.util.Objects;

public class DeviceDTO {

    private Long id;
    private String name;
    private Boolean state;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DeviceDTO DeviceDTO = (DeviceDTO) o;

        return Objects.equals(id, DeviceDTO.id) && Objects.equals(name, DeviceDTO.name) &&
                Objects.equals(state, DeviceDTO.state);
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

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
    
}
