import React, {useEffect, useState} from 'react';
import axios from "axios";

const Home = () => {
    const [weather, setWeather] = useState(null);
    const [nowWeather, setNowWeather] = useState(null);
    const [recommend, setRecommend] = useState(null);

    useEffect(() => {
        axios.get("http://localhost:8080/api/weather", {withCredentials: true})
            .then(res => {
                const data = res.data;
                setWeather(data.weatherDto);
                setNowWeather(data.nowWeatherDto);
                setRecommend(data.recommendDto)
            })
            .catch(err => {
                console.error("정보 불러오기 실패", err);
            });
    }, []);

    if (!weather) return <div>로딩 중...</div>

  return (
    <div>
        <p>Hello, world!</p>
        <div>
            <h2>현재 기온 : {nowWeather.temperature}</h2>
            {/*<p>추천 옷차림 : {recommend.getClothes()}</p>*/}
            <p>최고 기온 : {weather.maxTemperature}</p>
            <p>최저 기온 : {weather.minTemperature}</p>
            <p>강수확률 : {weather.rain}</p>
            <p>날씨 : {weather.weatherType}</p>
        </div>
    </div>
  );
};

export default Home;