import React from "react";
import styled from "styled-components";

const SOCIAL_AUTH_URLS = {
  kakao: "http://localhost:8080/oauth2/authorization/kakao",
  google: "http://localhost:8080/oauth2/authorization/google",
  github: "http://localhost:8080/oauth2/authorization/github",
};

const SocialLoginButton = ({ type }) => {
  const handleLogin = () => {
    const url = SOCIAL_AUTH_URLS[type];
    if (url) {
      window.location.href = url;
    }
  };

  return (
    <Button type={type} onClick={handleLogin}>
      {getButtonText(type)}
    </Button>
  );
};

export default SocialLoginButton;

// 소셜별 텍스트
const getButtonText = (type) => {
  switch (type) {
    case "kakao":
      return "카카오톡으로 로그인";
    case "google":
      return "구글 계정으로 로그인";
    case "github":
      return "GitHub으로 로그인";
    default:
      return "소셜 로그인";
  }
};

// 스타일
const Button = styled.button`
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  font-size: 14px;
  font-weight: bold;
  color: #fff;
  border: none;
  border-radius: 8px;
  cursor: pointer;

  background-color: ${({ type }) =>
    type === "kakao"
      ? "#FEE500"
      : type === "google"
      ? "#DB4437"
      : type === "github"
      ? "#333"
      : "#aaa"};

  color: ${({ type }) => (type === "kakao" ? "#000" : "#fff")};
`;
