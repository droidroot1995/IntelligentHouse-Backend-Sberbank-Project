package tk.droidroot.intelligenthouse.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="light_sensor")
@Data
public class LightSensorEntity {
    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE_LS")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    //@GeneratedValue//(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "lightSensor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<LightSensorDataEntity> sensorData;

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

    public List<LightSensorDataEntity> getSensorData() {
        return sensorData;
    }

    public void setSensorData(List<LightSensorDataEntity> sensorData) {
        this.sensorData = sensorData;
    }
}
