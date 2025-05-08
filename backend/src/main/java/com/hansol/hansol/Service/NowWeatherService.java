package com.hansol.hansol.Service;

import com.hansol.hansol.Dto.NowWeatherDto;
import com.hansol.hansol.Dto.RecommendDto;
import com.hansol.hansol.Utils.TempRange;
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
import java.util.List;

@Service
public class NowWeatherService {

    private RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
    private final String ServiceKey = "UGVc41C%2B%2FcUvUxumr3aNPb%2FdVTiFatzrAS99ZkHYRxUSVoedG2IKA7gTwCI7hr0kRXSQJd%2FBNmTCQOVE87Fyeg%3D%3D";

    private String Temp = "";

// 기상청 api 요청(초단기 현재기온)
    public String getNowWeatherData(){
        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        String pageNo = "1";
        String numOfRows = "1000";
        String dataType = "JSON";
        String base_date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String base_time = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmm"));

        try{
//            정각 확인 조건문(api서버에 반영되는 시간은 정각+10)
            int minute = LocalTime.now().getMinute();
            if(minute <= 10){
                base_time = LocalTime.now()
                        .minusHours(1)
                        .withMinute(50)
                        .format(DateTimeFormatter.ofPattern("HHmm"));
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

            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
            return response.getBody();

        } catch (Exception e){
            e.printStackTrace();
            return "Error : " + e.getMessage();
        }
    }

//    response 파싱해서 컨트롤러로 넘김
    @Scheduled(fixedRate = 1800000)
    public NowWeatherDto getNowWeather(){
        String jsonData = getNowWeatherData();

        try{
//            JSON 파싱
            JSONObject jsonResponse = new JSONObject(jsonData);
            JSONArray items = jsonResponse.getJSONObject("response")
                    .getJSONObject("body")
                    .getJSONObject("items")
                    .getJSONArray("item");

            String temperature = "";

//            T1H -> 현재기온, RN1 -> 강수량, REH -> 습도, WSD -> 풍속

            for(int i=0; i< items.length(); i++){
                JSONObject item = items.getJSONObject(i);
                String category = item.getString("category");
                String obsrValue = item.getString("obsrValue");

                switch(category){
                    case "T1H":
                        temperature += obsrValue + "℃";;
                        Temp = obsrValue;
                        break;
                }
            }

            return new NowWeatherDto(temperature);
        } catch (Exception e){
            e.printStackTrace();
            return new NowWeatherDto("N/A");
        }
    }

//    기온별 옷차림 추천 메소드
    @Scheduled(fixedRate = 1800000)
    public RecommendDto whatClothes(){
        int temp = (int) Double.parseDouble(Temp);

        List<TempRange> recommendation = List.of(
                new TempRange(-20, -1, "가장 두껍고 따듯한 옷"),
                new TempRange(0,5,"패딩"),
                new TempRange(6, 11, "코트, 두꺼운 니트"),
                new TempRange(12, 17, "바람막이, 자켓, 가디건"),
                new TempRange(18, 22,"셔츠, 긴팔"),
                new TempRange(23, 27, "반팔"),
                new TempRange(28, 40, "가장 얇고 시원한 옷")
        );

        for(TempRange tempRange : recommendation){
            if(tempRange.matches(temp)) return new RecommendDto(tempRange.getClothing());
        }
        return new RecommendDto("N/A");
    }
}
