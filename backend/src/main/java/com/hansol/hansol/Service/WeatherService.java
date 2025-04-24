package com.hansol.hansol.Service;

import com.hansol.hansol.Dto.WeatherDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class WeatherService {

    private RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
    private final String ServiceKey = "UGVc41C%2B%2FcUvUxumr3aNPb%2FdVTiFatzrAS99ZkHYRxUSVoedG2IKA7gTwCI7hr0kRXSQJd%2FBNmTCQOVE87Fyeg%3D%3D";

    //    기상청 api 요청(최저, 최고기온)
    public String getWeatherData() {
//        기상청에서 3시간 간격으로 예보하는 시간

        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        String pageNo = "1";
        String numOfRows = "1000";
        String dataType = "JSON";
        String base_date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String base_time = "0200";

        try{
//            오전 2시 이전은 전날 23:00 예보 사용
            if(LocalTime.now().getHour() <= 2){
                base_date = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyMMdd"));
                base_time = "2300";
            }

            urlBuilder.append("?ServiceKey=").append(ServiceKey)
                    .append("&numOfRows=").append(numOfRows)
                    .append("&pageNo=").append(pageNo)
                    .append("&dataType=").append(dataType)
                    .append("&base_date=").append(base_date)
                    .append("&base_time=").append(base_time)
                    .append("&nx=").append("60")
                    .append("&ny=").append("127");

            String url = urlBuilder.toString();

            URI uri = new URI(url);

//            응답데이터 확인용 URI
            System.out.println(String.format("URI : %s", uri));
            System.out.println(String.format("base_time : %s", base_time));

            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
            return response.getBody();
        } catch(Exception e){
            e.printStackTrace();
            return "Error : " + e.getMessage();
        }
    }

    //    response 파싱 후 DTO 컨트롤러로 전달
    public WeatherDto getWeather(){
        String jsonData = getWeatherData();
        String base_date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        try{
//            JSON응답 파싱
            JSONObject jsonResponse = new JSONObject(jsonData);
            JSONArray items = jsonResponse.getJSONObject("response")
                    .getJSONObject("body")
                    .getJSONObject("items")
                    .getJSONArray("item");

            String maxTemperature = "";
            String minTemperature = "";
            String rain = "";
            String weatherType = "";

            for(int i=0; i<items.length(); i++){
                JSONObject item = items.getJSONObject(i);
                String fcstDate = item.getString("fcstDate");

                //당일 날씨예보만 저장
                if(base_date.equals(fcstDate)){
                    String category = item.getString("category");
                    String fcstValue = item.getString("fcstValue");

                    //category별 값 저장
                    switch (category){
                        case "TMX":
                            maxTemperature =  fcstValue + "℃";
                            break;
                        case "POP":
                            rain = fcstValue + "%";
                            break;
                        case "TMN":
                            minTemperature = fcstValue + "℃";
                            break;
                        case "SKY":
                            weatherType = fcstValue;
                            if(weatherType.equals("1")){
                                weatherType = "☀\uFE0F 맑음";
                            } else if(weatherType.equals("3")){
                                weatherType = "☁\uFE0F 구름 많음";
                            } else{
                                weatherType = "\uD83C\uDF25\uFE0F 흐림";
                            }
                            break;
                    }
                }
            }

            return new WeatherDto(maxTemperature, minTemperature, rain, weatherType);
        } catch(Exception e){
            e.printStackTrace();
            return new WeatherDto("N/A", "N/A","N/A" ,"데이터 오류");
        }
    }
}