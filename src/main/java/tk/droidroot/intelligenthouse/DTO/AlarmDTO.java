package tk.droidroot.intelligenthouse.DTO;

import java.util.Objects;

public class AlarmDTO {

    private Long id;
    private String name;
    private String time;
    private Boolean state;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AlarmDTO AlarmDTO = (AlarmDTO) o;

        return Objects.equals(id, AlarmDTO.id) && Objects.equals(name, AlarmDTO.name) &&
                Objects.equals(time, AlarmDTO.time) && Objects.equals(state, AlarmDTO.state);
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
