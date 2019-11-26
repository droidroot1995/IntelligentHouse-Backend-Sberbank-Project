package tk.droidroot.intelligenthouse.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="temperature_sensor_data")
@Data
public class TemperatureSensorDataEntity {
    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE_TSD")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    //@GeneratedValue//(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="temperature_sensor_id", nullable = false)
    private TemperatureSensorEntity temperatureSensor;

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

    public TemperatureSensorEntity getTemperatureSensor() {
        return temperatureSensor;
    }

    public void setTemperatureSensor(TemperatureSensorEntity temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
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
