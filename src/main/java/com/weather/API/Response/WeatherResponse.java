package com.weather.API.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class WeatherResponse{

    private Current current;

    private Location location;

    @Getter
    @Setter
    public class Current{

        @JsonProperty("observation_time")
        private String observationTime;

        private int temperature;
        private int weather_code;
        public List<String> weather_descriptions;

    }

    @Getter
    @Setter
    public class Location{
        public String name;
        public String country;
        public String region;
        public String lat;
        public String lon;
        public String timezone_id;
        public String localtime;
        public int localtime_epoch;
        public String utc_offset;
    }




}











