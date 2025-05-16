package com.hansol.hansol.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private WeatherDto weatherDto;
    private NowWeatherDto nowWeatherDto;
    private RecommendDto recommendDto;
}
