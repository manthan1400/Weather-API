package com.weather.Service;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class KafkaMetricsService {

    private final MeterRegistry meterRegistry;

    public KafkaMetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void incrementMessagesSent() {
        meterRegistry.counter("kafka.messages.sent").increment();
    }

    public void incrementMessagesReceived() {
        meterRegistry.counter("kafka.messages.received").increment();
    }
}
