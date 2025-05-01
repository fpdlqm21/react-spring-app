import React, { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import {
  Wrapper,
  LoginBox,
  Title,
  Input,
  LoginButton,
  Divider,
  BottomText,
  SignUpLink,
  TermsCheckbox,
  ToggleBtn,
  PasswordWrap,
} from "./SignUp.styles"; 

const SignUp = () => {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [name, setName] = useState("");
  const [termsAccepted, setTermsAccepted] = useState(false);
  const [showPassword, setShowPassword] = useState(false);

  const emailRef = useRef(null);
  const passwordRef = useRef(null);
  const confirmPasswordRef = useRef(null);
  const nameRef = useRef(null);

  const isValidEmail = (email) => {
    return email.includes("@");
  };

  const handleSignUp = () => {
    if (!name) {
      alert("이름을 입력해주세요.");
      nameRef.current.focus(); 
      return;
    }
    if (!email) {
      alert("이메일을 입력해주세요.");
      emailRef.current.focus(); 
      return;
    }
    if (!isValidEmail(email)) {
      alert("이메일 양식이 올바르지 않습니다.");
      emailRef.current.focus(); 
      return;
    }
    if (!password) {
      alert("비밀번호를 입력해주세요.");
      passwordRef.current.focus(); 
      return;
    }
    if (!confirmPassword) {
      alert("비밀번호 확인을 입력해주세요.");
      confirmPasswordRef.current.focus(); 
      return;
    }
    if (password !== confirmPassword) {
      alert("비밀번호가 일치하지 않습니다.");
      confirmPasswordRef.current.focus(); 
      return;
    }
    if (!termsAccepted) {
      alert("이용약관에 동의해야 합니다.");
      return;
    }
    
    console.log("회원가입 성공");
    
    navigate("/login");
  };

  return (
    <Wrapper>
      <LoginBox>
        <Title>회원가입</Title>
        <Input
          ref={nameRef}
          type="text"
          placeholder="이름"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <Input
          ref={emailRef}
          type="email"
          placeholder="이메일"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <PasswordWrap>
          <Input
            ref={passwordRef}
            type={showPassword ? "text" : "password"}
            placeholder="비밀번호"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <ToggleBtn onClick={() => setShowPassword(!showPassword)}>
            {showPassword ? "🙈" : "👁️"}
          </ToggleBtn>
        </PasswordWrap>
        <PasswordWrap>
          <Input
            ref={confirmPasswordRef}
            type={showPassword ? "text" : "password"}
            placeholder="비밀번호 확인"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
          <ToggleBtn onClick={() => setShowPassword(!showPassword)}>
            {showPassword ? "🙈" : "👁️"}
          </ToggleBtn>
        </PasswordWrap>

        <TermsCheckbox>
          <label>
            <input
              type="checkbox"
              checked={termsAccepted}
              onChange={() => setTermsAccepted(!termsAccepted)}
            />
            이용약관에 동의합니다.
          </label>
        </TermsCheckbox>

        <LoginButton onClick={handleSignUp}>
          회원가입
        </LoginButton>

        <Divider>또는</Divider>

        <BottomText>
          이미 회원이신가요?{" "}
          <SignUpLink onClick={() => navigate("/login")}>로그인</SignUpLink>
        </BottomText>
      </LoginBox>
    </Wrapper>
  );
};

export default SignUp;
