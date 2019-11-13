package tk.droidroot.intelligenthouse.DTO;

import java.util.Objects;

public class GasSensorDTO {

    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GasSensorDTO GasSensorDTO = (GasSensorDTO) o;

        return Objects.equals(id, GasSensorDTO.id) && Objects.equals(name, GasSensorDTO.name);
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
