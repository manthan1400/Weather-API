package com.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class KafkaMessage {

    private String text;
    private LocalDateTime time;
}
