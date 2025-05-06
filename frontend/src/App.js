// App.js
import React, {useEffect, useState} from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import SignUp from "./pages/SignUp";
import axios from "axios";

function App() {
    /*
    * 통신 성공하고 예시로 남겨둘테니깐 참고해 성원
    * GET이나 POST 같은 걸로 API요청 날릴 때 말해줘 주소 설정해 줄게
     const [data, setData] = useState('');

     useEffect(() => {
        axios.get('http://localhost:8080/api/data')
            .then(res => {
                setData(res.data);
                console.log(data);
            })
            .catch(err => console.log(err))
     },[]);

     */
  return (
    <Router>
        {/*<div>*/}
        {/*    받아온 값 : {data}*/}
        {/*</div>*/}
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
      </Routes>
    </Router>
  );
}

export default App;
