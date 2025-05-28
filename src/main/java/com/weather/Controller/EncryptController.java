package com.weather.Controller;

import com.weather.Util.CryptoUtil;
import com.weather.Service.WeatherKafkaProducer;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/encrypt")
@Slf4j
public class EncryptController {

    @Autowired
    private WeatherKafkaProducer kafkaProducer;

    @GetMapping
    public void encryptCity(@RequestParam String city, HttpServletResponse response) throws Exception {
        log.info("Received request to encrypt city: {}", city);
        String encrypted = CryptoUtil.encrypt(city);
        String encoded = URLEncoder.encode(encrypted, StandardCharsets.UTF_8);
        String message = "EncryptController: City=" + city + " | Encoded=" + encoded;
        kafkaProducer.sendMessage("test-topic", message);
        log.info("City encrypted and Kafka message sent: {}", message);
        response.sendRedirect("/api/weather?city=" + encoded);
    }
}