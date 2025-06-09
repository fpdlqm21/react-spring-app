// App.js
import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login/Login";
import SignUp from "./pages/SignUp/SignUp";
import axios from "axios";
import Home from "./pages/Home/Home";
import Main from "./pages/Main/Main";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="" element={<Main/>} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path={"/Home"} element={<Home />} />
      </Routes>
    </Router>
  );
}

export default App;
