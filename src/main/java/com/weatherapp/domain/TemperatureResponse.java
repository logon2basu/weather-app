package com.weatherapp.domain;

/**
 * Returns Json response.
 */
public class TemperatureResponse {
    private String temperature;

    public TemperatureResponse() {
    }

    public TemperatureResponse(String temp) {
        this.temperature = temp;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
