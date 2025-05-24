package com.weather.Controller;

import com.weather.Util.CryptoUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/encrypt")
public class EncryptController {
    @GetMapping
    public void encryptCity(@RequestParam String city, HttpServletResponse response) {
        try {
            String encrypted = CryptoUtil.encrypt(city);
            String encoded = URLEncoder.encode(encrypted, StandardCharsets.UTF_8.toString());

            response.sendRedirect("/api/weather?city=" + encoded);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed: " + e.getMessage());
        }
    }
}
