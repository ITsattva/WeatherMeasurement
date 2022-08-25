package meteo.com.weather.services;

import meteo.com.weather.dto.SensorDTO;
import meteo.com.weather.models.Sensor;
import meteo.com.weather.repositories.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorService(SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    public Sensor getByName(String name){
        return sensorRepository.findByName(name);
    }

    @Transactional
    public void registration(SensorDTO sensorDTO) {
        sensorRepository.save(convertToSensor(sensorDTO));
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
