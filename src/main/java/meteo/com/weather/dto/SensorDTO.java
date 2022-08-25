package meteo.com.weather.dto;

import javax.validation.constraints.Size;

public class SensorDTO {

    @Size(min = 3, max = 30, message = "Sensor's name must be between 3 and 30 characters long")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorDTO(String name) {
        this.name = name;
    }

    public SensorDTO(){}
}
