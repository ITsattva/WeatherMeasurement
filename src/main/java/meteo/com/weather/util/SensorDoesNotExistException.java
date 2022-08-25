package meteo.com.weather.util;

public class SensorDoesNotExistException extends RuntimeException{

    public SensorDoesNotExistException(String message) {
        super(message);
    }
}
