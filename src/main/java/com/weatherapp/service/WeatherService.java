package com.weatherapp.service;

import com.weatherapp.domain.TemperatureResponse;
import com.weatherapp.exception.CityNotFoundException;
import com.weatherapp.models.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class WeatherService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${api.key}")
    private String appId;

    public TemperatureResponse getTemperature(final String city) {
        final Temperature response;
        try {
            response = webClientBuilder.build()
                    .get()
                    .uri("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + appId)
                    .retrieve()
                    .bodyToMono(Temperature.class)
                    .block();
        } catch (final WebClientResponseException exception) {
            throw new CityNotFoundException("Please pass the valid cityName");
        }

        return new TemperatureResponse(response.getMain().getTemp());

    }
}
