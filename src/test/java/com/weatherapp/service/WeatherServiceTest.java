package com.weatherapp.service;

import com.weatherapp.domain.TemperatureResponse;
import com.weatherapp.exception.CityNotFoundException;
import com.weatherapp.models.Main;
import com.weatherapp.models.Temperature;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private ClientResponse clientResponse;

    @Mock
    private ExchangeFunction exchangeFunction;

    @Mock
    private WebClientResponseException webClientResponseException;

    @Mock
    private IllegalArgumentException illegalArgumentException;

    @BeforeEach
    public  void setup() {
        WebClient.Builder builder = WebClient.builder().exchangeFunction(exchangeFunction);
        ReflectionTestUtils.setField(weatherService, "webClientBuilder", builder);
    }

    @Test
    public void should_return_temperature_when_valid_city_is_given() {
        when(exchangeFunction.exchange(any(ClientRequest.class))).thenReturn(Mono.just(clientResponse));

        final Main main = new Main();
        main.setTemp("290.99");
        final Temperature temperature = new Temperature();
        temperature.setMain(main);

        when(clientResponse.bodyToMono(Temperature.class)).thenReturn(Mono.just(temperature));

        final TemperatureResponse temperatureResponse = weatherService.getTemperature("Bangalore");
        MatcherAssert.assertThat(temperatureResponse.getTemperature(), Matchers.is("290.99"));
    }

    @Test
    public void should_throw_invalid_city_exception_when_city_is_invalid() {
        when(exchangeFunction.exchange(any(ClientRequest.class))).thenThrow(webClientResponseException);
        Assertions.assertThrows(CityNotFoundException.class, () ->  weatherService.getTemperature("bb"));
    }

    @Test
    public void should_throw_illegal_exception_when_city_is_invalid() {
        when(exchangeFunction.exchange(any(ClientRequest.class))).thenThrow(illegalArgumentException);
        Assertions.assertThrows(IllegalArgumentException.class, () ->  weatherService.getTemperature("bb"));
    }
}
