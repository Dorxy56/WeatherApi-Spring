package hu.pelda.idojarasapi.repository;

import hu.pelda.idojarasapi.entity.Weather;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {

    Optional<Weather> findTopByCityIgnoreCaseOrderByDateDesc(String city);

    List<Weather> findByCityIgnoreCaseAndDateBetweenOrderByDateDesc(
            String city,
            LocalDate start,
            LocalDate end
    );
}