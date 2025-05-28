package com.weather.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
/**
 * Service for interacting with the Weatherstack API.
 * Handles business logic for fetching and parsing weather data for a given city.
 */
@Service
public class WeatherKafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}