package hu.pelda.idojarasapi.service;

import hu.pelda.idojarasapi.entity.Weather;
import hu.pelda.idojarasapi.repository.WeatherRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final WeatherRepository repo;

    public WeatherService(WeatherRepository repo) {
        this.repo=repo;
    }

    public Optional<Weather> getByCity(String city) {
        return repo.findTopByCityIgnoreCaseOrderByDateDesc(city);
    }

    public List<Weather> getByCityAndDateRange(String city, LocalDate date){
        LocalDate start = date.minusDays(13);
        return repo.findByCityIgnoreCaseAndDateBetweenOrderByDateDesc(city, start, date);
    }
}