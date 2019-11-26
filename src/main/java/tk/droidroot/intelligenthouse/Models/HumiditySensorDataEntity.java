package tk.droidroot.intelligenthouse.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="humidity_sensor_data")
@Data
public class HumiditySensorDataEntity {
    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE_HSD")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    //@GeneratedValue//(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="humidity_sensor_id", nullable = false)
    private HumiditySensorEntity humiditySensor;

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

    public HumiditySensorEntity getHumiditySensor() {
        return humiditySensor;
    }

    public void setHumiditySensor(HumiditySensorEntity humiditySensor) {
        this.humiditySensor = humiditySensor;
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
