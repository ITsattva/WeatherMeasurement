package meteo.com.weather.controller;

import meteo.com.weather.dto.MeasurementDTO;
import meteo.com.weather.dto.SensorDTO;
import meteo.com.weather.models.Sensor;
import meteo.com.weather.services.MeasurementService;
import meteo.com.weather.services.SensorService;
import meteo.com.weather.util.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementService measurementService;
    private final SensorService sensorService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementsController(MeasurementService measurementService, SensorService sensorService, MeasurementValidator measurementValidator, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurements(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
//        Sensor sensor = measurementDTO.getSensor();
//        SensorDTO sensorDTO = modelMapper.map(sensor, SensorDTO.class);
        measurementValidator.validate(measurementDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            StringBuilder error = new StringBuilder();

            for (FieldError fieldError : errors) {
                error.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append("; ");
            }

            throw new MeasurementNotAdded(error.toString());
        }

        measurementService.save(measurementDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public List<MeasurementDTO> getMeasurements(){
        return measurementService.getAll();
    }

    @GetMapping("/rainyDaysCount")
    public String getRainyDays(){
        return null;//todo get rainy days
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotAdded e) {
        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse(
                e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

}
