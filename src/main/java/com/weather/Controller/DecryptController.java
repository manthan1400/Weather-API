package com.weather.Controller;

import com.weather.Util.CryptoUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/decrypt")
public class DecryptController {

    @GetMapping
    public ResponseEntity<String> decryptCity(@RequestParam String city) {
        try {
            String decrypted = CryptoUtil.decrypt(city);
            return ResponseEntity.ok("Decrypted city: " + decrypted);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Decryption failed: " + e.getMessage());
        }
    }
}
