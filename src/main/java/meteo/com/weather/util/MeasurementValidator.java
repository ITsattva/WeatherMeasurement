package meteo.com.weather.util;

import meteo.com.weather.dto.MeasurementDTO;
import meteo.com.weather.dto.SensorDTO;
import meteo.com.weather.models.Sensor;
import meteo.com.weather.services.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Sensor.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;

        if(sensorService.getByName(measurementDTO.getSensor().getName()) == null) {
            errors.rejectValue("sensor", "", "Sensor with this name does not exist");
        }
    }
}
