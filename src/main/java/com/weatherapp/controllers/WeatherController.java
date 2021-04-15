package com.weatherapp.controllers;

import com.weatherapp.domain.TemperatureResponse;
import com.weatherapp.exception.NoCityException;
import com.weatherapp.service.WeatherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for accepting the requests
 */

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weatherInfo")
    public ResponseEntity<TemperatureResponse> getWeatherDetails(@RequestParam(name = "city") final String city) throws Exception {

        if (StringUtils.isEmpty(city)) {
            throw new NoCityException("Missing city name");
        }
        return new ResponseEntity<TemperatureResponse>(weatherService.getTemperature(city), HttpStatus.OK);
    }
}
