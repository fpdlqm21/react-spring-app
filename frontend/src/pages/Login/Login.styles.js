import styled from "styled-components";

export const Wrapper = styled.div`
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f5f7fa;
`;

export const LoginBox = styled.div`
  width: 100%;
  max-width: 360px;
  padding: 32px;
  background: white;
  border-radius: 12px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
`;

export const Title = styled.h2`
  margin-bottom: 24px;
  text-align: center;
  font-size: 24px;
`;

export const Input = styled.input`
  width: 100%;
  padding: 12px;
  box-sizing: border-box; 
  margin-bottom: 12px;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 14px;
`;

export const PasswordWrap = styled.div`
  position: relative;
  margin-bottom: 12px;
`;

export const PasswordInput = styled(Input)`
  padding-right: 40px; 
`;

export const ToggleBtn = styled.button`
  position: absolute;
  right: 12px;
  top: 10px;
  background: none;
  border: none;
  font-size: 16px;
  cursor: pointer;
`;

export const Checkbox = styled.input`
  margin-right: 6px;
  transform: scale(1.1);
`;

export const OptionRow = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 13px;
  color: #666;
  margin-bottom: 16px;
`;

export const FindLinks = styled.div`
  display: flex;
  align-items: center;
  gap: 6px;

  a {
    color: #4a90e2;
    text-decoration: none;
  }
`;

export const LoginButton = styled.button`
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

export const Divider = styled.div`
  text-align: center;
  margin: 20px 0;
  color: #aaa;
  font-size: 13px;
`;

export const BottomText = styled.p`
  text-align: center;
  font-size: 14px;
  color: #555;
`;

export const SignUpLink = styled.span`
  color: #4a90e2;
  font-weight: bold;
  cursor: pointer;

  &:hover {
    text-decoration: underline;
  }
`;
