package meteo.com.weather.util;

public class SensorNameAlreadyExistsException extends RuntimeException{
    public SensorNameAlreadyExistsException(String message) {
        super(message);
    }
}
