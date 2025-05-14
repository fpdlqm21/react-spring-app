import React, { useState, useRef } from "react";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import SocialLoginButton from "../components/SocialLoginButton";
import axios from "axios";


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

    // 실제 로그인 로직
    console.log("로그인 시도", { email, password });

    // 시험삼아서 한번 짜본 로그인 로직
    axios.post(API_URL, new URLSearchParams({
      username: email,
      password: password,
    }),
        {
          headers:{
            "Content-Type": "application/x-www-form-urlencoded",
          },
          withCredentials : true,
        }
    )
        .then(res => {
          console.log("로그인 성공", res);
          navigate("/Home");
        })
        .catch(err => {
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
          아직 회원이 아니신가요?{" "}
          <SignUpLink onClick={() => navigate("/signup")}>회원가입</SignUpLink>
        </BottomText>
      </LoginBox>
    </Wrapper>
  );
};

export default Login;


// 스타일 컴포넌트
const Wrapper = styled.div`
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f5f7fa;
`;

const LoginBox = styled.div`
  width: 100%;
  max-width: 360px;
  padding: 32px;
  background: white;
  border-radius: 12px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
`;

const Title = styled.h2`
  margin-bottom: 24px;
  text-align: center;
  font-size: 24px;
`;

const Input = styled.input`
  width: 100%;
  padding: 12px;
  box-sizing: border-box; 
  margin-bottom: 12px;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 14px;
`;

const PasswordInput = styled(Input)`
  padding-right: 40px; 
`;

const PasswordWrap = styled.div`
  position: relative;
  margin-bottom: 12px;
`;

const ToggleBtn = styled.button`
  position: absolute;
  right: 12px;
  top: 10px;
  background: none;
  border: none;
  font-size: 16px;
  cursor: pointer;
`;

const Checkbox = styled.input`
  margin-right: 6px;
  transform: scale(1.1);
`;

const OptionRow = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 13px;
  color: #666;
  margin-bottom: 16px;
`;

const FindLinks = styled.div`
  display: flex;
  align-items: center;
  gap: 6px;

  a {
    color: #4a90e2;
    text-decoration: none;
  }
`;

const LoginButton = styled.button`
  width: 100%;
  padding: 12px;
  background-color: #4a90e2;
  color: white;
  font-weight: bold;
  border: none;
  border-radius: 8px;
  margin-bottom: 16px;
  font-size: 15px;
`;

const Divider = styled.div`
  text-align: center;
  margin: 20px 0;
  color: #aaa;
  font-size: 13px;
`;

const BottomText = styled.p`
  text-align: center;
  font-size: 14px;
  color: #555;
`;

const SignUpLink = styled.span`
  color: #4a90e2;
  font-weight: bold;
  cursor: pointer;

  &:hover {
    text-decoration: underline;
  }
`;
