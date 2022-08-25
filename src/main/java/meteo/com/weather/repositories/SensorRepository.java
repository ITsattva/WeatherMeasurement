package meteo.com.weather.repositories;

import meteo.com.weather.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    Sensor findByName(String name);
}
