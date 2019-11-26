package tk.droidroot.intelligenthouse.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="humidity_sensor")
@Data
public class HumiditySensorEntity {
    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE_HS")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    //@GeneratedValue//(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "humiditySensor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<HumiditySensorDataEntity> sensorData;

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

    public List<HumiditySensorDataEntity> getSensorData() {
        return sensorData;
    }

    public void setSensorData(List<HumiditySensorDataEntity> sensorData) {
        this.sensorData = sensorData;
    }
}
