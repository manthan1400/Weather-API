package com.weather.Cache;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class WeatherCache {

    private Map<String, String> appCache;

    @PostConstruct
    public void init(){
        appCache=null;
    }
}
