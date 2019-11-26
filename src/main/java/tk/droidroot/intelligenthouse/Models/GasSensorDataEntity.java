package tk.droidroot.intelligenthouse.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="gas_sensor_data")
@Data
public class GasSensorDataEntity {
    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE_GSD")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    //@GeneratedValue//(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="gas_sensor_id", nullable = false)
    private GasSensorEntity gasSensor;

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

    public GasSensorEntity getGasSensor() {
        return gasSensor;
    }

    public void setGasSensor(GasSensorEntity gasSensor) {
        this.gasSensor = gasSensor;
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
