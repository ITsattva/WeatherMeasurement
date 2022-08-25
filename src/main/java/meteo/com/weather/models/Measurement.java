package meteo.com.weather.models;

import jdk.jfr.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Min(value = -100, message = "Temperature must be higher then -100")
    @Max(value = 100, message = "Temperature must be less 100")
    @Column(name = "value")
    private double value;

    @NotEmpty(message = "Field \"value\" shouldn't be empty")
    @Column(name = "raining")
    private String raining;

    @NotNull(message = "Sensor could not be null")
    @ManyToOne()
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;

    @Timestamp
    private LocalDateTime time;

    public Measurement(){}

    public Measurement(int value, String raining, Sensor sensor, LocalDateTime time) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
        this.time = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRaining() {
        return raining;
    }

    public void setRaining(String raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
