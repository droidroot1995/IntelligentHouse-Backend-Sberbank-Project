package tk.droidroot.intelligenthouse.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="light_sensor_data")
@Data
public class LightSensorDataEntity {
    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE_LSD")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    //@GeneratedValue//(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="light_sensor_id", nullable = false)
    private LightSensorEntity lightSensor;

    @Column(name = "data")
    private Double data;

    @Column(name="date")
    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LightSensorEntity getLightSensor() {
        return lightSensor;
    }

    public void setLightSensor(LightSensorEntity lightSensor) {
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
