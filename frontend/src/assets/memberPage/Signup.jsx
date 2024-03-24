import { useState } from 'react';
import styles from "./Login.module.css";

const Signup = function () {
  const [step, setStep] = useState(0);
  const [userInfo, setUserInfo] = useState({
    nickname: '',
    gender: '',
    height: 0,
    weight: 0,
    location: '',
    longitude: 0,
    latitude: 0,
    birthYear: 0,
    comment: '',
    eoa: '',
    phoneNumber: '',
    publicKey: '',
  });
  
  // 지역정보 입력 여부 변수
  const [isLocationVisible, setisLocationVisible] = useState(false);

  // 현재 연도를 기준으로 상태 설정
  const currentYear = new Date().getFullYear();
  const [selectedYear, setSelectedYear] = useState(currentYear);

  // 연도 목록 생성: 현재 연도부터 100년 전까지
  const years = Array.from(new Array(101), (val, index) => currentYear - index);

  const handleInputChange = (e) => {
    setUserInfo((prevInfo) => ({
      ...prevInfo,
      [e.target.name]: e.target.value,
    }));
  };

  // Button 클릭 이벤트 핸들러
  const handleClick = () => {
    switch (step) {
      case 0:
        // 닉네임 중복 검사 등의 로직...
        setStep(step + 1);
        break;
      case 1:
        // 출생연도 선택 후 처리 로직...
        setStep(step + 1);
        break;
      case 2:
        // 성별 선택 후 처리 로직...
        setStep(step + 1);
        break;
      // 추가적인 단계별 처리 로직...
      default:
        console.log("회원가입 완료 또는 마지막 단계 이후의 처리");
        // 회원가입완료 API 호출 등의 로직...
        break;
    }
  };

  // Button 텍스트 결정
  const buttonText = () => {
    switch (step) {
      case 0:
        return "닉네임 중복 체크";
      case 1:
        return "출생연도 확인";
      case 2:
        return "성별 확인";
      // 추가적인 단계별 버튼 텍스트...
      default:
        return "회원가입 완료";
    }
  };

  // 닉네임중복검사 API
  // 주소검색 API
  // 구글맵 API
  // 블록체인 지갑 생성 API
  // 회원가입완료 API

  return(
    <div>
      {isLocationVisible === true && (
        <div>
          지역정보
        </div>
      )}
      {step >= 4 && (
        <div>
          전화번호
        </div>
      )}
      {step >= 3 && (
        <div>
          키 & 몸무게
        </div>
      )}
      {step >= 2 && (
        <div>
          성별
        </div>
      )}
      {step >= 1 && (
        <div className={styles.signup_form}>
          <div>
            <div className={styles.signup_title}>출생연도</div>
            <select className={styles.signup_input} value={selectedYear} onChange={handleInputChange}>
              {years.map((year) => (
                <option key={year} value={year}>
                  {year}
                </option>
              ))}
            </select>
          </div>
        </div>
      )}
      {step >= 0 && (
        <div className={styles.signup_form}>
          <div>
            <div className={styles.signup_title}>닉네임</div>
            <input className={styles.signup_input} type="text" placeholder='닉네임을 입력해주세요'  onChange={handleInputChange}/>
          </div>
        </div>
      )}
      <button className={styles.signup_btn} onClick={handleClick}>{buttonText()}</button>
    </div>
  )
}

export default Signup;