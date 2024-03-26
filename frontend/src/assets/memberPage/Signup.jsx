import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from "./Login.module.css";
import { createWallet } from '../../apis/wallet';
import { submitUserInfo, checkDuplicated } from '../../apis/member';

const Signup = function () {
  const navigate = useNavigate();
  const [step, setStep] = useState(1);
  const [userInfo, setUserInfo] = useState({
    nickname: '',
    gender: '',
    height: 0,
    weight: 0,
    location: '',
    longitude: 0,
    latitude: 0,
    birthYear: currentYear,
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
  const years = Array.from({length:101}, (val, index) => currentYear - index);

  useEffect(() => {
    // selectedYear가 변경될 때마다 userInfo의 birthYear를 업데이트합니다.
    setUserInfo(prevInfo => ({...prevInfo, birthYear: parseInt(selectedYear)}));
  }, [selectedYear]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    // 입력 값에 대한 즉각적인 유효성 검사를 추가
    if (name === 'nickname') {
      validateNickname(value);
    }
    setUserInfo(prev => ({ ...prev, [name]: value }));
    // if (e.target.name === 'selectedYear') {
    //   // selectedYear의 경우 상태를 직접 업데이트합니다.
    //   setSelectedYear(e.target.value);
    // } else {
    //   setUserInfo((prevInfo) => ({
    //     ...prevInfo,
    //     [e.target.name]: e.target.value,
    //   }));
    // }
  };

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

  // Button 클릭 이벤트 핸들러
  const handleClick = async () => {
    if (step === 5) {
      setStep(0);
      setisLocationVisible(true);
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
        console.log(updatedUserInfo)
        navigate('/main')
      } catch (error) {
        console.log('지갑 생성 중 에러 발생', error)
      }
    }
  };

  
  const [isNextButtonDisabled, setIsNextButtonDisabled] = useState(false);
  const [nicknameError, setNicknameError] = useState('');
  const [debounce, setDebounce] = useState(null);

  useEffect(() => {
    // 디바운스된 중복 검사 로직
    if (step === 1 && userInfo.nickname) {
      if (debounce) clearTimeout(debounce);
      setDebounce(setTimeout(() => {
        checkNicknameDuplication(userInfo.nickname);
      }, 2000));
    }
  }, [userInfo.nickname]);
  
  useEffect(() => {
    validateCurrentStep();
  }, [step, userInfo]); // step 또는 userInfo가 변경될 때마다 유효성 검사 실행

  const validateNickname = (nickname) => {
    // 닉네임의 유효성 검사를 실행하고 에러 메시지 상태를 업데이트
    if (!nickname) {
      setNicknameError('닉네임을 입력해주세요.');
      setIsNextButtonDisabled(true);
    } else if (nickname.length > 12) {
      setNicknameError('닉네임은 12자 이하여야 합니다.');
      setIsNextButtonDisabled(true);
    } else {
      // 유효성 검사를 통과하면 에러 메시지를 초기화하고, 중복 검사 로직을 실행
      setNicknameError('');
      setIsNextButtonDisabled(false);
      checkNicknameDuplication(nickname);
    }
  };

  // 중복 검사에 디바운스 적용
const checkNicknameDuplication = (nickname) => {
  if (debounce) clearTimeout(debounce);
  setDebounce(setTimeout(() => {
    checkDuplicated(nickname);
  }, 2000));
};

  const validateCurrentStep = () => {
    // 닉네임 유효성 검사
    if (step === 1) {
      if (!userInfo.nickname) {
        setIsNextButtonDisabled(true);
        setNicknameError('닉네임을 입력해주세요.');
      } else if (userInfo.nickname.length > 12) {
        setIsNextButtonDisabled(true);
        setNicknameError('닉네임은 12자 이하여야 합니다.');
      }}
    // } else if (step === 2 && !userInfo.gender) {
    //   setIsNextButtonDisabled(true);
    // } else {
    //   // 추가적인 유효성 검사 로직
    //   setIsNextButtonDisabled(false); // 모든 검사를 통과하면 버튼 활성화
    // }
  };

  return(
    <div>
      {isLocationVisible === true && (
        <div className={styles.signup_form}>
          <div className={styles.signup_title}>지역정보</div>
          <input className={styles.signup_input} type="text" placeholder='주소를 입력해주세요' name="location" onClick={daumPost} value={userInfo.location} readOnly/>
        </div>
      )}
      {step >= 5 && (
        <div className={styles.signup_form}>
          <div className={styles.signup_title}>전화번호</div>
          <input className={styles.signup_input} type="text" placeholder='ex) 01011111111' name="phoneNumber" onChange={handleInputChange}/>
        </div>
      )}
      {step >= 4 && (
        <div className={styles.signup_form}>
          <div>
            <div className={styles.signup_title}>키</div>
            <input className={styles.signup_input} type="number" placeholder='키를 입력해주세요' name="height" onChange={handleInputChange}/>
          </div>
          <div>
            <div className={styles.signup_title}>몸무게</div>
            <input className={styles.signup_input} type="number" placeholder='몸무게를 입력해주세요' name="weight" onChange={handleInputChange}/>
          </div>
        </div>
      )}
      {step >= 3 && (
        <div className={styles.signup_form}>
          <div>
            <div className={styles.signup_title}>성별</div>
            <select className={styles.signup_input} name="gender" defaultValue="" onChange={handleInputChange}>
              <option value="" disabled>성별 선택</option>
              <option value="MALE">남성</option>
              <option value="FEMALE">여성</option>
            </select>
          </div>
        </div>
      )}
      {step >= 2 && (
        <div className={styles.signup_form}>
          <div>
            <div className={styles.signup_title}>출생연도</div>
            <select className={styles.signup_input} value={selectedYear} onChange={handleInputChange} name="selectedYear">
              {years.map((year) => (
                <option key={year} value={year}>
                  {year}
                </option>
              ))}
            </select>
          </div>
        </div>
      )}
      {step >= 1 && (
        <div className={styles.signup_form}>
          <div>
            <div className={styles.signup_title}>닉네임</div>
            <input className={styles.signup_input} type="text" placeholder='닉네임을 입력해주세요' name="nickname" value={userInfo.nickname} onChange={handleInputChange}/>
            {nicknameError && <p>{nicknameError}</p>}
          </div>
        </div>
      )}
      <button className={styles.signup_btn} onClick={handleClick} disabled={isNextButtonDisabled}>{step < 1 ? "가입 완료" : "다음"}</button>
    </div>
  )
}

export default Signup;