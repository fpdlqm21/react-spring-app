package com.hansol.hansol.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class NowWeatherDto {
    private String temperature;

    public NowWeatherDto(String temperature){
        this.temperature = temperature;
    }
}
