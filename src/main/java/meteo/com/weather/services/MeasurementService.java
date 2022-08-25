package meteo.com.weather.services;

import meteo.com.weather.dto.MeasurementDTO;
import meteo.com.weather.dto.SensorDTO;
import meteo.com.weather.models.Measurement;
import meteo.com.weather.models.Sensor;
import meteo.com.weather.repositories.MeasurementRepository;
import meteo.com.weather.repositories.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void save(MeasurementDTO measurementDTO) {
        Measurement measurement = convertToMeasurement(measurementDTO);
        measurement.setTime(LocalDateTime.now());

//        Sensor sensor = measurementDTO.getSensor();
//        measurement.setSensor(sensor);

        measurementRepository.save(measurement);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public List<MeasurementDTO> getAll() {
        List<MeasurementDTO> measurementDTOS = measurementRepository.findAll().stream().map(this::convertMeasurementToDTO).collect(Collectors.toList());

//        for(MeasurementDTO measurementDTO : measurementDTOS) {
//            convertSensorToDTO(measurementDTO.getSensor());
//        }

        return measurementDTOS;
    }

    public int getRainyDaysCount() {
        return 0;//todo rainy days count
    }

    private MeasurementDTO convertMeasurementToDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    private SensorDTO convertSensorToDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }


}
