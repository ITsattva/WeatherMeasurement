package meteo.com.weather.dto;

import meteo.com.weather.models.Measurement;
import meteo.com.weather.models.Sensor;

import javax.validation.constraints.*;

public class MeasurementDTO {
    @Min(value = -100, message = "Temperature must be bigger then -100")
    @Max(value = 100, message = "Temperature must be less 100")
    private double value;//todo make double

    @NotEmpty(message = "Field \"value\" shouldn't be empty")
    private String raining;

    @NotNull(message = "Sensor could not be null")
    private SensorDTO sensor;

    public MeasurementDTO(double value, String raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public MeasurementDTO(){}

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getRaining() {
        return raining;
    }

    public void setRaining(String raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
