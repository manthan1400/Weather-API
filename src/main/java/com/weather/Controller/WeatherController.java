package com.weather.Controller;

import com.weather.API.Response.WeatherResponse;
import com.weather.Service.WeatherService;
import com.weather.Util.CryptoUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public ResponseEntity<?> getWeather(@RequestParam String city) {
       try{
           String decryptedCity = CryptoUtil.decrypt(city);
        WeatherResponse response = weatherService.getWeather(city);

        if (response == null) {
            return new ResponseEntity<>("City not found or error occurred", HttpStatus.NOT_FOUND);
        }
        String weatherInfo = String.format("Weather in %s: %d°C, feels like: %s",
                response.getLocation().getName(),
        response.getCurrent().getTemperature(),
        response.getCurrent().getWeather_descriptions());
        return new ResponseEntity<>(weatherInfo, HttpStatus.OK);
    } catch (Exception e) {
           throw new RuntimeException(e);
       }
}

}
