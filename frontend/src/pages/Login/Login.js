// 📁 src/pages/Login.js
import React, { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import SocialLoginButton from "../../components/SocialLoginButton";
import axios from "axios";
import {
  Wrapper,
  LoginBox,
  Title,
  Input,
  PasswordInput,
  PasswordWrap,
  ToggleBtn,
  Checkbox,
  OptionRow,
  FindLinks,
  LoginButton,
  Divider,
  BottomText,
  SignUpLink,
} from "./Login.styles";

const Login = () => {
  const API_URL = "http://localhost:8080/login";
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [autoLogin, setAutoLogin] = useState(false);
  const [showPassword, setShowPassword] = useState(false);

  const emailInputRef = useRef(null);
  const passwordInputRef = useRef(null);
  const navigate = useNavigate();

  const handleLogin = () => {
    if (!email) {
      alert("이메일을 입력해주세요.");
      emailInputRef.current.focus();
      return;
    }
    if (!email.includes("@")) {
      alert("이메일 양식이 아닙니다.");
      emailInputRef.current.focus();
      return;
    }
    if (!password) {
      alert("비밀번호를 입력해주세요.");
      passwordInputRef.current.focus();
      return;
    }

    axios
      .post(
        API_URL,
        new URLSearchParams({
          username: email,
          password: password,
        }),
        {
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
          withCredentials: true,
        }
      )
      .then((res) => {
        console.log("로그인 성공", res);
        navigate("/Home");
      })
      .catch((err) => {
        console.error("로그인 실패", err);
        alert("로그인에 실패했습니다.");
      });
  };

  return (
    <Wrapper>
      <LoginBox>
        <Title>로그인</Title>

        <Input
          type="email"
          placeholder="이메일"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          ref={emailInputRef}
        />

        <PasswordWrap>
          <PasswordInput
            type={showPassword ? "text" : "password"}
            placeholder="비밀번호"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            ref={passwordInputRef}
          />
          <ToggleBtn onClick={() => setShowPassword(!showPassword)}>
            {showPassword ? "🙈" : "👁️"}
          </ToggleBtn>
        </PasswordWrap>

        <OptionRow>
          <label>
            <Checkbox
              type="checkbox"
              checked={autoLogin}
              onChange={() => setAutoLogin(!autoLogin)}
            />
            자동 로그인
          </label>
          <FindLinks>
            <a href="#">아이디 찾기</a>
            <span>|</span>
            <a href="#">비밀번호 찾기</a>
          </FindLinks>
        </OptionRow>

        <LoginButton onClick={handleLogin}>로그인</LoginButton>

        <Divider>또는</Divider>

        <SocialLoginButton type="kakao" />
        <SocialLoginButton type="google" />
        <SocialLoginButton type="github" />

        <BottomText>
          아직 회원이 아니신가요? <SignUpLink onClick={() => navigate("/signup")}>회원가입</SignUpLink>
        </BottomText>
      </LoginBox>
    </Wrapper>
  );
};

export default Login;
