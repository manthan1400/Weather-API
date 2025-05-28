package com.weather.Service;

import com.weather.API.Response.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
/**
 * Service for interacting with the Weatherstack API.
 * Handles business logic for fetching and parsing weather data for a given city.
 */
@Service
@Slf4j
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;
    //we have refresh key everytime we running application
    @Autowired
    private WeatherKafkaProducer kafkaProducer;

    @Value("${weather.api.url}")
    private String api;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate=restTemplateBuilder.build();

    }

    public WeatherResponse getWeather(String city) {
        String finalAPI = api.replace("CITY", city.trim()).replace("API_KEY", apiKey);
        log.info("Final API URL: {}", finalAPI);

        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            log.error("Error response: {} - {}", response.getStatusCode(), response.getBody());
            kafkaProducer.sendMessage("test-topic", "WeatherService: Failed to fetch weather for " + city);
            return null; // or throw an exception
        }
        kafkaProducer.sendMessage("test-topic", "WeatherService: Weather fetched for " + city);
        return response.getBody();
    }





}
