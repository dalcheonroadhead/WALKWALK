import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from "./Login.module.css";
import { createWallet } from '../../apis/wallet';
import { submitUserInfo } from '../../apis/member';

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

  useEffect(() => {
    // selectedYear가 변경될 때마다 userInfo의 birthYear를 업데이트합니다.
    setUserInfo(prevInfo => ({
      ...prevInfo,
      birthYear: parseInt(selectedYear), // 출생연도를 정수로 변환하여 저장
    }));
  }, [selectedYear]);

  const handleInputChange = (e) => {
    if (e.target.name === 'selectedYear') {
      // selectedYear의 경우 상태를 직접 업데이트합니다.
      setSelectedYear(e.target.value);
    } else {
      setUserInfo((prevInfo) => ({
        ...prevInfo,
        [e.target.name]: e.target.value,
      }));
    }
  };

  // Button 클릭 이벤트 핸들러
  const handleClick = async () => {
    switch (step) {
      case 1:
        // 닉네임 중복 검사 등의 로직...
        setStep(step + 1);
        break;
      case 2:
        // 출생연도 선택 후 처리 로직...
        setStep(step + 1);
        break;
      case 3:
        // 성별 선택 후 처리 로직...
        setStep(step + 1);
        break;
      case 4:
        // 키&몸무게 선택 후 처리 로직...
        setStep(step + 1);
        break;
      case 5:
        // 전화번호 선택 후 처리 로직...
        setStep(0);
        setisLocationVisible(true); // 여기서 지역정보 입력을 활성화
        break;
      // 추가적인 단계별 처리 로직...
      default:
        console.log("회원가입 완료 또는 마지막 단계 이후의 처리");
        // 회원가입완료 API 호출 등의 로직...
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
        break;
    }
  };

  const daumPost = () => {
    new daum.Postcode({
      oncomplete: function(data) {
          // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

          // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
          // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
          var roadAddr = data.roadAddress; // 도로명 주소 변수
          var extraRoadAddr = ''; // 참고 항목 변수

          // 법정동명이 있을 경우 추가한다. (법정리는 제외)
          // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
          if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
              extraRoadAddr += data.bname;
          }
          // 건물명이 있고, 공동주택일 경우 추가한다.
          if(data.buildingName !== '' && data.apartment === 'Y'){
            extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
          }
          // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
          if(extraRoadAddr !== ''){
              extraRoadAddr = ' (' + extraRoadAddr + ')';
          }

          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          document.getElementById('sample4_postcode').value = data.zonecode;
          document.getElementById("sample4_roadAddress").value = roadAddr;
          document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
          
          // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
          if(roadAddr !== ''){
              document.getElementById("sample4_extraAddress").value = extraRoadAddr;
          } else {
              document.getElementById("sample4_extraAddress").value = '';
          }

          var guideTextBox = document.getElementById("guide");
          // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
          if(data.autoRoadAddress) {
              var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
              guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
              guideTextBox.style.display = 'block';

          } else if(data.autoJibunAddress) {
              var expJibunAddr = data.autoJibunAddress;
              guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
              guideTextBox.style.display = 'block';
          } else {
              guideTextBox.innerHTML = '';
              guideTextBox.style.display = 'none';
          }
      }
  }).open();
  }

  return(
    <div>
      {isLocationVisible === true && (
        <div className={styles.signup_form}>
          <div className={styles.signup_title}>지역정보</div>
          <input className={styles.signup_input} type="text" placeholder='주소를 입력해주세요' name="location" onClick={daumPost} onChange={handleInputChange}/>
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
            <input className={styles.signup_input} type="text" placeholder='닉네임을 입력해주세요' name="nickname" onChange={handleInputChange}/>
          </div>
        </div>
      )}
      <button className={styles.signup_btn} onClick={handleClick}>{step < 1 ? "회원가입 완료" : "다음"}</button>
    </div>
  )
}

export default Signup;