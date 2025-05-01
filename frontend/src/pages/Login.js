import React, { useState } from "react";
import styled from "styled-components";
import { useNavigate } from "react-router-dom"; // useNavigate 훅 import
import SocialLoginButton from "../components/SocialLoginButton";

const Login = () => {
  const [autoLogin, setAutoLogin] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const [password, setPassword] = useState("");
  const navigate = useNavigate(); // navigate 훅 사용

  return (
    <Wrapper>
      <LoginBox>
        <Title>로그인</Title>

        <Input type="email" placeholder="이메일" />

        <PasswordWrap>
          <PasswordInput
            type={showPassword ? "text" : "password"}
            placeholder="비밀번호"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
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

        <LoginButton>로그인</LoginButton>

        <Divider>또는</Divider>

        <SocialLoginButton type="kakao" />
        <SocialLoginButton type="google" />
        <SocialLoginButton type="github" />

        {/* 회원가입 링크 추가 */}
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
  width: 95%;
  padding: 12px;
  padding-right: 0;
  margin-bottom: 12px;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 14px;
`;

const PasswordInput = styled(Input)`
  padding-right: 0;
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
