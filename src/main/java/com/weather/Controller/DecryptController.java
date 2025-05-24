package com.weather.Controller;

import com.weather.Util.CryptoUtil;
import com.weather.Service.WeatherKafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * REST controller for decrypting city names.
 * Accepts an encrypted city name, decrypts it, sends a Kafka message, and returns the decrypted value.
 * Endpoint: /api/decrypt?city={encrypted}
 */
@RestController
@RequestMapping("/decrypt")
@Slf4j
public class DecryptController {

    @Autowired
    private WeatherKafkaProducer kafkaProducer;

    @GetMapping
    public String decryptCity(@RequestParam String city) throws Exception {
        log.info("Received request to decrypt city: {}", city);
        String decrypted = CryptoUtil.decrypt(city);
        String message = "DecryptController: Encoded=" + city + " | Decrypted=" + decrypted;
//        kafkaProducer.sendMessage("test-topic", message);
        log.info("City decrypted and Kafka message sent: {}", message);
        return "Decrypted city: " + decrypted;
    }
}