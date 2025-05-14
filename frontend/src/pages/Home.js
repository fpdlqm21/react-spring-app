import React, {useEffect, useState} from 'react';
import axios from "axios";

const Home = () => {
    const [userInfo, setUserInfo] = useState(null);

    useEffect(() => {
        axios.get("http://localhost:8080/weather", {withCredentials: true})
            .then(res => {
                setUserInfo(res.data);
            })
            .catch(err => {
                console.error("사용자 정보 불러오기 실패", err);
            });
    }, []);

    if (!userInfo) return <div>로딩 중...</div>

  return (
    <div>
        {userInfo.email} Hello, world!
    </div>
  );
};

export default Home;