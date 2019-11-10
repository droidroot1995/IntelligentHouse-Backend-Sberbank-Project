package tk.droidroot.intelligenthouse.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="temperature_sensor")
@Data
public class TemperatureSensorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "temperatureSensor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<TemperatureSensorDataEntity> sensorData = new HashSet<>();

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

    public Set<TemperatureSensorDataEntity> getSensorData() {
        return sensorData;
    }

    public void setSensorData(Set<TemperatureSensorDataEntity> sensorData) {
        this.sensorData = sensorData;
    }
}
