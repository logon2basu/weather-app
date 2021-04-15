package com.weatherapp.controllers;

import com.weatherapp.domain.TemperatureResponse;
import com.weatherapp.exception.CityNotFoundException;
import com.weatherapp.service.WeatherService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by abhi on 4/12/2021.
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    public void should_return_temperature_when_valid_city_given() throws Exception {
        when(weatherService.getTemperature(any())).thenReturn(new TemperatureResponse("299.90"));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/weatherInfo?city=Bangalore"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature", Matchers.is("299.90")));
    }

    @Test
    public void should_throw_an_exception_when_no_city() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/weatherInfo?city="))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Enter Valid City Name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details[0]", Matchers.is("Missing city name")));
    }

    @Test
    public void should_throw_an_exception_when_invalid_city() throws Exception {
        when(weatherService.getTemperature(any())).thenThrow(new CityNotFoundException("Please pass the valid cityName"));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/weatherInfo?city=bb"))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Enter Valid City Name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details[0]", Matchers.is("Please pass the valid cityName")));
    }
}