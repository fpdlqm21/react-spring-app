package com.hansol.hansol.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDto {
    private String maxTemperature;
    private String minTemperature;
    private String rain;
    private String weatherType;

    public WeatherDto(String maxTemperature, String minTemperature, String rain, String weatherType){
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.rain = rain;
        this.weatherType = weatherType;
    }
}
