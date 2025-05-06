package com.hansol.hansol.Controller;

import com.hansol.hansol.Dto.NowWeatherDto;
import com.hansol.hansol.Dto.WeatherDto;
import com.hansol.hansol.Service.NowWeatherService;
import com.hansol.hansol.Service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class WeatherController {

    private final WeatherService weatherService;
    private final NowWeatherService nowWeatherService;

    @GetMapping("/weather")
    public String getWeather(Model model, Authentication authentication) {
        // Authentication객체 -> 현재 로그인한 사용자의 인증 정보 담고있음
        Object principal = authentication.getPrincipal(); // 사용자 정보 꺼냄

        String userName = "비회원";

        /*
        * 로그인 -> 인증 객체(Authentication)생성 <- principal이 담긴다.
        * 로그인 방식에 따라 principal 타입 다름(UserDetails OR OAuth2User)
        * 일반 로그인 -> UserDetails타입
        * OAuth2로그인 -> OAuth2User타입
        * */

        if(principal instanceof UserDetails){
//            일반 로그인한 경우
            UserDetails userDetails = (UserDetails) principal;
            userName = userDetails.getUsername();

        } else if(authentication instanceof OAuth2AuthenticationToken oauthToken){
//            OAuth2 로그인한 경우(Authentication객체가 OAuth2AuthenticationToken 토큰 타입일 때)
//            발급받은 토큰으로 어떤 방식 간편 로그인인지 확인
            OAuth2User oauth2User = (OAuth2User) principal;
            String registrationId = oauthToken.getAuthorizedClientRegistrationId();

            System.out.println(registrationId);

            if("google".equals(registrationId)){
                userName = oauth2User.getAttribute("name"); // google은 name이라는 키로 제공
            } else if ("kakao".equals(registrationId)) {
                /* OAuth2User객체의 attributes맵에서 꺼내온다.
                로그인 시 아래의 방식으로 전달 받는다.
                * {
                *   "id": 123456789,
                *   "kakao_account": {
                *       "profile":{
                *           "nickname":"홍길동"
                *       },
                *       ...
                *   }
                * }
                * */
                Map<String, Object> kakaoAccount = oauth2User.getAttribute("kakao_account");
                Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
                userName = (String) profile.get("nickname");
            }
        }

//        날씨 데이터 요청
        WeatherDto weatherData = weatherService.getWeather();
        NowWeatherDto nowWeatherDto = nowWeatherService.getNowWeather();

//        OAuth2 Login 방식 말고 form 방식의 경우 -> 컨트롤러 파라미터에 @AuthenticationPrincipal User userDetails
//        model.addAttribute("userName", userDetails.getName()); form 로그인 방식
        model.addAttribute("userName", userName);
        model.addAttribute("nowWeatherData", nowWeatherDto);
        model.addAttribute("weatherData", weatherData);

        return "weather";
    }
}
