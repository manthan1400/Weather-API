package com.weather.Controller;

import com.weather.Service.WeatherService;
import com.weather.Service.WeatherKafkaProducer;
import com.weather.API.Response.WeatherResponse;
import com.weather.Util.CryptoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@Slf4j
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherKafkaProducer kafkaProducer;

    @GetMapping
    public ResponseEntity<?> getWeather(@RequestParam String city) throws Exception {
        log.info("Received request to get weather for city: {}", city);
        String decryptedCity = CryptoUtil.decrypt(city);
        log.info("Decrypted city: {}", decryptedCity);

        WeatherResponse response = weatherService.getWeather(decryptedCity);

        if (response == null) {
            log.warn("Weather data not found for city: {}", decryptedCity);
            return new ResponseEntity<>("City not found or error occurred", HttpStatus.NOT_FOUND);
        }

        String weatherInfo = String.format("Weather in %s: %d°C, feels like: %s",
                response.getLocation().getName(),
                response.getCurrent().getTemperature(),
                response.getCurrent().getWeather_descriptions());
        kafkaProducer.sendMessage("test-topic", weatherInfo);
        log.info("Weather data fetched and Kafka message sent: {}", weatherInfo);
        return new ResponseEntity<>(weatherInfo, HttpStatus.OK);
    }
}