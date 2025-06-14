package com.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Main entry point for the Weather application.
 * Boots the Spring Boot application and prints startup messages to the console.
 */
@SpringBootApplication
public class WeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
		System.out.println("This is backend for Ext Weather API");
		System.out.println("${weather.api.url}");


		String str = "My name is manthan";
		String[] wordsArray = str.split(" "); // Splits the string by spaces

// Output the array
		for (String word : wordsArray) {
			System.out.println(word);
		}
	}

}
