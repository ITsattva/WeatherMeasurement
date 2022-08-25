package meteo.com.weather.controller;

import meteo.com.weather.dto.SensorDTO;
import meteo.com.weather.services.SensorService;
import meteo.com.weather.util.SensorErrorResponse;
import meteo.com.weather.util.SensorNameAlreadyExistsException;
import meteo.com.weather.util.SensorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder error = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError fieldError : errors) {
                error.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append("; ");
            }

            throw new SensorNameAlreadyExistsException(error.toString());
        }

        sensorService.registration(sensorDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handleException(SensorNameAlreadyExistsException e) {
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.NOT_ACCEPTABLE);
    }
}
