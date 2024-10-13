package com.weather.Service;

import com.weather.API.Response.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;
//  private static final String apiKey="8219bb2eae81767595bc37e90a2f73de";
      //we have refresh key everytime we running application

    @Value("${weather.api.url}")
    private String api;
//  private static final String api = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";


    private final RestTemplate restTemplate;

    public WeatherService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate=restTemplateBuilder.build();

    }

    public WeatherResponse getWeather(String city) {
        String finalAPI = api.replace("CITY", city).replace("API_KEY", apiKey);
        System.out.println("Final API URL: " + finalAPI);

        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            System.out.println("Error response: " + response.getStatusCode() + " - " + response.getBody());
            return null; // or throw an exception
        }

        return response.getBody();
    }





}
