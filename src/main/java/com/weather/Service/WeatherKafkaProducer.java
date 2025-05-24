package com.weather.Service;

import com.weather.dto.KafkaMessage;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Service for interacting with the Weatherstack API.
 * Handles business logic for fetching and parsing weather data for a given city.
 */
@Service
@Data
public class WeatherKafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final List<String> messages = new CopyOnWriteArrayList<>();

    //  Producer method
    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    // Consumer method
    @KafkaListener(topics = "test-topic", groupId = "weather-group")
    public void listen(String message) {
        messages.add(String.valueOf(new KafkaMessage(message, LocalDateTime.now())));
    }

    public List<String> getMessages() {
        return messages;
    }

    public void clearMessages() {
        messages.clear();
    }
}