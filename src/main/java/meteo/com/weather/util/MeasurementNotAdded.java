package meteo.com.weather.util;

public class MeasurementNotAdded extends RuntimeException{
    public MeasurementNotAdded(String message) {
        super(message);
    }
}
