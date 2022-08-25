package meteo.com.weather.client;

import meteo.com.weather.dto.MeasurementDTO;
import meteo.com.weather.dto.SensorDTO;
import meteo.com.weather.models.Sensor;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientSensor {
    private static RestTemplate restTemplate = new RestTemplate();
    private static String address = "http://localhost:8080/measurements/add";


    public static void main(String[] args) {
//        SensorDTO firstSensor = new SensorDTO("First");
//        SensorDTO secondSensor = new SensorDTO("Second");
//        SensorDTO thirdSensor = new SensorDTO("Third");
//        String registrationAddress = "http://localhost:8080/sensors/registration";
//        restTemplate.postForObject(registrationAddress, firstSensor, SensorDTO.class);
//        restTemplate.postForObject(registrationAddress, secondSensor, SensorDTO.class);
//        restTemplate.postForObject(registrationAddress, thirdSensor, SensorDTO.class);

        for (int i = 0; i < 1000; i++) {
        createAndAddMeasurement();
        }

//        MeasurementDTO[] measurements = restTemplate.getForObject(address, MeasurementDTO[].class);
//
//        for (MeasurementDTO m : measurements) {
//            System.out.println(m.getSensor().getName()+" "+m.getRaining()+" "+m.getValue());
//        }
    }

    private static String trueOrFalse() {
        Random random = new Random();
        int randomInt = random.nextInt(2);
        System.out.println(randomInt);
        return (randomInt == 0) ? "true" : "false";
    }

    private static double temperature() {
        Random random = new Random();
        double temperature = random.nextDouble()*45.0;
        temperature = random.nextInt(2) == 0 ? temperature : temperature-(temperature*2);
        return temperature;
    }

    private static SensorDTO randomSensor(){
        Random random = new Random();
        int choice = random.nextInt(3);
        System.out.println(choice);
        switch(choice) {
            case 0:
                return new SensorDTO("First");
            case 1:
                return new SensorDTO("Second");
            case 2:
                return new SensorDTO("Third");
        }

        return new SensorDTO("not existed");
    }

    private static void createAndAddMeasurement(){
        SensorDTO sensorDTO = randomSensor();
        String choice = trueOrFalse();
        double temperature = temperature();

        MeasurementDTO measurementDTO = new MeasurementDTO(temperature, choice, sensorDTO);
        System.out.println(measurementDTO.getSensor().getName()+" "+measurementDTO.getRaining()+" "+measurementDTO.getValue());

        HttpEntity<MeasurementDTO> entity = new HttpEntity<>(measurementDTO);

        String response = restTemplate.postForObject(address, entity, String.class);

        System.out.println(response);
    }
}
