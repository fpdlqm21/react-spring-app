// Main.js
import React from "react";
import maleImg from "../../images/male.png";
import femaleImg from "../../images/female.png";
import styled from "styled-components";

const Main = () => {
  return (
    <Container>
      <WeatherTop>
        <Title>날씨에 따라 , 스타일 있게.</Title>
        <WeatherInfo>
          <div>🌤 서울특별시 강남구</div>
          <div>13℃ | 11℃ 바람이 살짝 불어요.</div>
        </WeatherInfo>
      </WeatherTop>

      <OutfitSection>
        <CharacterWrap>
          <CharacterBox>
            <img src={maleImg} alt="남자 캐릭터" />
            <ClothingLabel>상의</ClothingLabel>
            <ClothingLabel>하의</ClothingLabel>
          </CharacterBox>
          <CharacterBox>
            <img src={femaleImg} alt="여자 캐릭터" />
            <ClothingLabel>자켓</ClothingLabel>
          </CharacterBox>
        </CharacterWrap>
      </OutfitSection>

      <RecommendationList>
        <SectionHeader>
          오늘 뭐 입지? 이번 주 총정리!
          <Arrow>➤</Arrow>
        </SectionHeader>
        <CardScroll>
          <WeatherCard>
            <span>☀️</span>
            <p>가볍게 입어요.<br />반팔 꺼낼 시간</p>
            <RecButton>추천 보기</RecButton>
          </WeatherCard>
          <WeatherCard>
            <span>⛅</span>
            <p>가볍게 입어요.<br />반팔 꺼낼 시간</p>
            <RecButton>추천 보기</RecButton>
          </WeatherCard>
          <WeatherCard>
            <span>🌧️</span>
            <p>가볍게 입어요.<br />반팔 꺼낼 시간</p>
            <RecButton>추천 보기</RecButton>
          </WeatherCard>
        </CardScroll>
      </RecommendationList>

      <ActionButtons>
        <Button type="outline" onClick={() => window.location.href = "/login"}>로그인</Button>
        <Button type="filled" onClick={() => window.location.href = "/signup"}>회원가입</Button>
      </ActionButtons>
    </Container>
  );
};

export default Main;

// 스타일드 컴포넌트들
const Container = styled.div`
  background: linear-gradient(#d0edff, #a2d2ff);
  padding: 20px 16px;
  font-family: 'Pretendard', sans-serif;
  min-height: 100vh;

  @media (max-width: 480px) {
    padding: 16px 12px;
  }
`;

const Title = styled.h2`
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 10px;

  @media (max-width: 480px) {
    font-size: 18px;
  }
`;

const WeatherInfo = styled.div`
  background: #fff;
  border-radius: 12px;
  padding: 12px;
  font-size: 14px;
  line-height: 1.5;

  @media (max-width: 480px) {
    font-size: 13px;
    padding: 10px;
  }
`;

const WeatherTop = styled.div`
  text-align: center;
  margin-bottom: 16px;

  @media (max-width: 480px) {
    margin-bottom: 12px;
  }
`;

const OutfitSection = styled.div`
  margin: 20px 0;

  @media (max-width: 480px) {
    margin: 16px 0;
  }
`;

const CharacterWrap = styled.div`
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  gap: 12px;
  position: relative;

  @media (max-width: 480px) {
    gap: 8px;
  }
`;

const CharacterBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;

  img {
    height: 160px;
    margin-bottom: 8px;

    @media (max-width: 480px) {
      height: 120px;
    }
  }
`;

const ClothingLabel = styled.div`
  position: absolute;
  background: #fff;
  border-radius: 8px;
  padding: 4px 8px;
  font-size: 12px;
  transform: translateY(-50%);
  top: ${(props) => props.top || "auto"};
  left: ${(props) => props.left || "auto"};
  right: ${(props) => props.right || "auto"};

  @media (max-width: 480px) {
    font-size: 11px;
    padding: 3px 6px;
  }
`;

const RecommendationList = styled.div`
  margin: 20px 0;
`;

const SectionHeader = styled.h3`
  font-size: 16px;
  margin-bottom: 8px;
  display: flex;
  align-items: center;
  gap: 5px;

  @media (max-width: 480px) {
    font-size: 15px;
  }
`;

const Arrow = styled.span`
  font-size: 16px;
  transform: translateY(1px);
`;

const CardScroll = styled.div`
  display: flex;
  gap: 10px;
  overflow-x: auto;
  padding-bottom: 8px;

  @media (max-width: 480px) {
    gap: 8px;
  }
`;

const WeatherCard = styled.div`
  background: #fff;
  border-radius: 12px;
  padding: 10px;
  min-width: 130px;
  text-align: center;
  font-size: 14px;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
  flex-shrink: 0;

  @media (max-width: 480px) {
    min-width: 110px;
    font-size: 13px;
    padding: 8px;
  }
`;

const RecButton = styled.button`
  margin-top: 10px;
  background: #e0f0ff;
  border: none;
  border-radius: 8px;
  padding: 6px;
  font-size: 13px;
  cursor: pointer;

  @media (max-width: 480px) {
    padding: 5px;
    font-size: 12px;
  }
`;

const ActionButtons = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
  gap: 12px;

  @media (max-width: 480px) {
    flex-direction: row;
    gap: 10px;
  }
`;

const Button = styled.button`
  flex: 1;
  padding: 12px;
  font-size: 14px;
  font-weight: bold;
  border-radius: 8px;
  border: ${(props) => props.type === "outline" ? "2px solid #000" : "none"};
  background-color: ${(props) => props.type === "filled" ? "#007bff" : "#fff"};
  color: ${(props) => props.type === "filled" ? "#fff" : "#000"};

  @media (max-width: 480px) {
    font-size: 13px;
    padding: 10px;
  }
`;
