# External Weather API App

A Spring Boot application that provides weather data using the Weatherstack API and demonstrates Kafka integration for message publishing.

## Features

- REST API to fetch and encrypt weather data
- Kafka producer to send weather and encryption events
- Docker Compose setup for local development with Kafka and Zookeeper

## Project Structure

- `src/main/java/com/weather/Controller` — REST controllers
- `src/main/java/com/weather/Service` — Business logic and Kafka producer
- `src/main/java/com/weather/Config` — Kafka topic configuration
- `src/main/resources/application.properties` — Application configuration
- `docker-compose.yml` — Docker Compose for Kafka, Zookeeper, and the app

## Prerequisites

- Java 17+
- Maven
- Docker & Docker Compose

## Running Locally

1. **Build the application:**
   ```sh
   mvn clean package
   
   This version includes your original content and adds clear instructions and documentation for users and contributors.

## API Endpoints

- **Encrypt city name:**  
  `GET /api/encrypt?city=London`  
  Encrypts the city name and redirects to the weather endpoint with the encrypted value.

- **Get weather by encrypted city:**  
  `GET /api/weather?city=<encrypted>`  
  Returns weather data for the decrypted city.

**Usage Note:**  
Always use the `/api/encrypt` endpoint to obtain the encrypted city value before calling `/api/weather`.  
Passing a plain city name (e.g., `Satara`) directly to `/api/weather` will cause a decryption error.

**Example Flow:**
1. Call `/encrypt?city=Satara`  
   → Redirects to `/weather?city=<encrypted>`
2. Use the encrypted value in `/weather` to get the weather data.