package meteo.com.weather.util;

import meteo.com.weather.dto.SensorDTO;
import meteo.com.weather.models.Sensor;
import meteo.com.weather.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Access;

@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Sensor.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensor = (SensorDTO) target;
        if(sensorService.getByName(sensor.getName()) != null) {
            errors.rejectValue("name", "", "this name is already exists");
        }
    }
}
