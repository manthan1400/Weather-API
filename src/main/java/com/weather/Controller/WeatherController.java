package com.weather.Controller;

import com.weather.API.Response.WeatherResponse;
import com.weather.Service.WeatherService;
import com.weather.Util.CryptoUtil;
import com.weather.WeatherKafkaProducer;
import com.weather.exception.WeatherServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherKafkaProducer kafkaProducer;

    private final WeatherService weatherService;
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public ResponseEntity<?> getWeather(@RequestParam String city) {
        try {
            String decryptedCity = CryptoUtil.decrypt(city);
            WeatherResponse response = weatherService.getWeather(decryptedCity);

            if (response == null) {
                throw new WeatherServiceException("City not found or error occurred");
            }

            String weatherInfo = String.format("Weather in %s: %d°C, feels like: %s",
                    response.getLocation().getName(),
                    response.getCurrent().getTemperature(),
                    response.getCurrent().getWeather_descriptions());
            kafkaProducer.sendMessage("test-topic", weatherInfo);
            return new ResponseEntity<>(weatherInfo, HttpStatus.OK);
        } catch (Exception e) {
            throw new WeatherServiceException("Failed to get weather: " + e.getMessage());
        }
    }


}


