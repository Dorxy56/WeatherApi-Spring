package hu.pelda.idojarasapi.controller;

import hu.pelda.idojarasapi.entity.Weather;
import hu.pelda.idojarasapi.service.WeatherService;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/idojaras")
public class WeatherController {
    private final WeatherService service;

    public WeatherController(WeatherService service){
        this.service=service;
    }

    @GetMapping({"", "/"})
    public Object getWeather(
            @RequestParam(required = false) String varos,
            @RequestParam(required = false) String datum
    ){
        if (varos == null || varos.isBlank()){
            return Map.of(
                    "minta1", "/?varos=Szeged",
                    "minta2", "/?varos=Debrecen&datum=2024-02-15"
            );
        }

        if(datum == null || datum.isBlank()) {
            return service.getByCity(varos)
                    .map(this::toSingleResponse)
                    .orElse(Map.of("hiba", "nincs találat"));
        }

        try {
            LocalDate d = LocalDate.parse(datum);

            List<Weather> lista = service.getByCityAndDateRange(varos, d);

            if(lista.isEmpty()){
                return Map.of("hiba", "nincs találat");
            }

            return lista.stream().map(this::toListResponse).toList();
        } catch (Exception e) {
            return Map.of("hiba", "nincs találat");
        }
    }

    private Map<String, Object> toSingleResponse(Weather w){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("varos", w.getCity());
        map.put("homerseklet",w.getTemperature() +"°C");
        map.put("paratartalom",w.getHumidity()+"%");
        map.put("szelsebesseg",w.getWindSpeed()+"°km/h");
        return map;
    }

    private Map<String, Object> toListResponse(Weather w){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("varos", w.getCity());
        map.put("datum", w.getDate());
        map.put("homerseklet",w.getTemperature() +"°C");
        map.put("paratartalom",w.getHumidity()+"%");
        map.put("szelsebesseg",w.getWindSpeed()+"°km/h");
        return map;
    }
}