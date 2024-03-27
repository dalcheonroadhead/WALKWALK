import { useState, useEffect, useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from "./Login.module.css";
import { createWallet } from '../../apis/wallet';
import { submitUserInfo, checkDuplicated } from '../../apis/member';

const Signup = function () {
  const navigate = useNavigate();
  const [step, setStep] = useState(1);
  const [isButtonDisabled, setIsButtonDisabled] = useState(true);
  const [nicknameError, setNicknameError] = useState('');
  const [nicknameErrorType, setNicknameErrorType] = useState(false);
  const [debounce, setDebounce] = useState(null);
  const [selectedYear, setSelectedYear] = useState('');
  const currentYear = new Date().getFullYear();
  const years = Array.from({length:101}, (val, index) => currentYear - index); // 연도 목록 생성: 현재 연도부터 100년 전까지
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
  
  useEffect(() => {
    // selectedYear가 변경될 때마다 userInfo의 birthYear를 업데이트합니다.
    setUserInfo(prevInfo => ({...prevInfo, birthYear: parseInt(selectedYear)}));
  }, [selectedYear]);

  useEffect(() => {
    if (debounce) clearTimeout(debounce);
    setDebounce(setTimeout(() => {
      checkNickname(userInfo.nickname);
    }, 700))
  }, [userInfo.nickname]);

  useEffect(() => {
    // 입력값마다 유효성 검사하기
    if (step === 2) {
      if (selectedYear !== '') {
        setIsButtonDisabled(false);
      } else {
        setIsButtonDisabled(true);
      }
    }
    else if (step === 3) {
      if (userInfo.gender !== '') {
        setIsButtonDisabled(false);
      } else {
        setIsButtonDisabled(true);
      }
    }
    else if (step === 4) {
      if (userInfo.height !== '' && userInfo.weight !== '') {
        setIsButtonDisabled(false);
      } else {
        setIsButtonDisabled(true);
      }
    }
    else if (step === 5) {
      if (userInfo.phoneNumber !== '') {
        setIsButtonDisabled(false);
      } else {
        setIsButtonDisabled(true);
      }
    }
    else if (step === 0) {
      if (userInfo.location !== '') {
        setIsButtonDisabled(false);
      } else {
        setIsButtonDisabled(true);
      }
    }
  }, [step, selectedYear, userInfo.gender, userInfo.height, userInfo.weight, userInfo.phoneNumber, userInfo.location]);

  const checkNickname = useCallback(async (nickname) => {
      // 닉네임의 유효성 검사를 실행하고 에러 메시지 상태를 업데이트
      if (!nickname || nickname.length > 10) {
        setNicknameError('닉네임은 1자 이상 10자 이하여야 합니다.');
        setNicknameErrorType(false);
        setIsButtonDisabled(true);
        return;
      }

      try {
        const { isDuplicated } = await checkDuplicated(nickname);
        if (isDuplicated) {
          setNicknameError('이미 사용 중인 닉네임입니다.');
          setNicknameErrorType(false);
          setIsButtonDisabled(true);
        } else {
          setNicknameError('사용 가능한 닉네임입니다.');
          setNicknameErrorType(true);
          if (step === 1) {
            setIsButtonDisabled(false);
          }
        }
      } catch (error) {
        console.error('닉네임 중복 검사 중 에러 발생:', error);
        setNicknameError('중복 검사 중 오류가 발생했습니다.');
        setNicknameErrorType(false);
        setIsButtonDisabled(true);
      }
    }, [step]);

  const handleAddressChange = (address) => {
    setUserInfo(prevInfo => ({...prevInfo, location: address}));
  };

  const daumPost = () => {
    new daum.Postcode({
      oncomplete: function(data) {
          // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
          const roadAddr = data.roadAddress; // 도로명 주소 변수
          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          handleAddressChange(roadAddr);
      }
  }).open();
  }

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    if (name === 'selectedYear') {
      // selectedYear의 경우 상태를 직접 업데이트합니다.
      setSelectedYear(value);
    } else {
      setUserInfo((prevInfo) => ({...prevInfo, [name]: value}));
    }
  };

  const handleClick = async () => {
    setNicknameError('');
    if (step === 5) {
      setStep(0);
    } else if (step === 0) {
      // 블록체인 지갑 생성 API
      try {
        const walletInfo = await createWallet();
        const updatedUserInfo = {
          ...userInfo,
          eoa: walletInfo.eoa,
          publicKey: walletInfo.publicKey,
        }
        setUserInfo(updatedUserInfo);

        await submitUserInfo(updatedUserInfo);
        navigate('/main')
      } catch (error) {
        console.log('지갑 생성 중 에러 발생', error)
      }
    } else {
      setStep(step + 1);
      setIsButtonDisabled(true);
    }
  };

  return(
    <div className={styles.signup_container}>
      {step === 0 && (
        <div>
          <div className={styles.signup_title}>지역정보</div>
          <input className={styles.signup_input} type="text" placeholder='주소를 입력해주세요' name="location" onClick={daumPost} value={userInfo.location} readOnly/>
        </div>
      )}
      {step >= 5 && (
        <div>
          <div className={styles.signup_title}>전화번호</div>
          <input className={styles.signup_input} type="text" placeholder='ex) 01011111111' name="phoneNumber" onChange={handleInputChange}/>
        </div>
      )}
      {step >= 4 && (
        <div className={styles.signup_2col}>
          <div>
            <div className={styles.signup_title}>키</div>
            <input className={styles.signup_input} type="number" placeholder='ex) 111.1 (단위 : cm)' name="height" onChange={handleInputChange}/>
          </div>
          <div>
            <div className={styles.signup_title}>몸무게</div>
            <input className={styles.signup_input} type="number" placeholder='ex) 11.1 (단위 : kg)' name="weight" onChange={handleInputChange}/>
          </div>
        </div>
      )}
      {step >= 3 && (
        <div>
          <div className={styles.signup_title}>성별</div>
          <select className={styles.signup_input} name="gender" defaultValue="" onChange={handleInputChange}>
            <option value="" disabled>성별을 선택해주세요</option>
            <option value="MALE">남성</option>
            <option value="FEMALE">여성</option>
          </select>
        </div>
      )}
      {step >= 2 && (
        <div>
          <div className={styles.signup_title}>출생연도</div>
          <select className={styles.signup_input} name="selectedYear" value={selectedYear} onChange={handleInputChange}>
            <option value="" disabled>출생연도를 선택해주세요</option>
            {years.map((year) => (
              <option key={year} value={year}>{year}</option>
            ))}
          </select>
        </div>
      )}
      {step >= 1 && (
        <div>
          <div className={styles.signup_title}>닉네임</div>
          <input className={styles.signup_input} type="text" placeholder='닉네임을 입력해주세요' name="nickname" value={userInfo.nickname} onChange={handleInputChange}/>
          {nicknameError && <p className={styles.signup_error} style={{color: nicknameErrorType ? 'green' : 'red'}}>{nicknameError}</p>}
        </div>
      )}
      <button className={styles.signup_btn} disabled={isButtonDisabled} onClick={handleClick}>{step === 0 ? "가입 완료" : "다 음"}</button>
    </div>
  )
}

export default Signup;