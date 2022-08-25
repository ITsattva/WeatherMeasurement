package meteo.com.weather.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Sensor")
public class Sensor implements Serializable{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;

    @Id
    @Size(min = 3, max = 30, message = "Sensor's name must be between 3 and 30 characters long")
    @Column(name = "name")
    private String name;

    @Transient
    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;

    public Sensor(){}

    public Sensor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}
