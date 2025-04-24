package com.hansol.hansol.Controller;

import com.hansol.hansol.Dto.NowWeatherDto;
import com.hansol.hansol.Dto.WeatherDto;
import com.hansol.hansol.Service.NowWeatherService;
import com.hansol.hansol.Service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class WeatherController {

    private final WeatherService weatherService;
    private final NowWeatherService nowWeatherService;

    @GetMapping("/weather")
    public String getWeather(Model model) {
        WeatherDto weatherData = weatherService.getWeather();
        NowWeatherDto nowWeatherDto = nowWeatherService.getNowWeather();

        model.addAttribute("nowWeatherData", nowWeatherDto);
        model.addAttribute("weatherData", weatherData);

        return "weather";
    }
}
