package com.hansol.hansol.Controller;

import com.hansol.hansol.Dto.ResponseDto;
import com.hansol.hansol.Service.NowWeatherService;
import com.hansol.hansol.Service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
/*
* RestController -> dto 반환하면 자동으로 JSON으로 응답 보내줌
* React에서 API 호출 시 응답하는 컨트롤러
* */
public class ReactApiController {

    private final WeatherService weatherService;
    private final NowWeatherService nowWeatherService;
    @GetMapping("/api/weather")
    public ResponseDto hello(){
        return new ResponseDto(
                weatherService.getWeather(),
                nowWeatherService.getNowWeather(),
                nowWeatherService.whatClothes()
        );
    }
}
