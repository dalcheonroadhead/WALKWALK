import { useState } from 'react';
import styles from "./Login.module.css";

const Signup = function () {
  const [page, setPage] = useState(1);
  const [userInfo, setUserInfo] = useState({
    nickname: '',
    birthYear: '',
    gender: '',
    height: '',
    weight: '',
    location: '',
    longitude: '',
    latitude: '',
    eoa: '',
    publicKey: '',
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUserInfo((prevInfo) => ({
      ...prevInfo,
      [name]: value,
    }));
  };

  // 닉네임중복검사 API
  // 주소검색 API
  // 구글맵 API
  // 블록체인 지갑 생성 API
  // 회원가입완료 API

  return(
    <div className={styles.signup_container}>
      { page === 1 && (
        <div>
          <h2>닉네임을 만들어볼까요?</h2>
          <input type="text" name="nickname_input" 
            value={userInfo.nickname} onChange={handleInputChange} />
          <button onClick={() => setPage(2)}>다음</button>
        </div>
      )}
      { page === 2 && (
        <div>
          <h2>인적 사항을 알려주세요</h2>
          <h3>출생연도</h3>
          <input type="text" name="birthYear_input" placeholder='YYYY'
            value={userInfo.birthYear} onChange={handleInputChange} />
          <h3>성별</h3>
          <input type="radio" id="male" name="gender_input1"
            value="male" checked={userInfo.gender === 'male'} onChange={handleInputChange} />
          <label htmlFor="male">남성</label>
          <input type="radio" id="female" name="gender_input2"
            value="female" checked={userInfo.gender === 'female'} onChange={handleInputChange} />
          <label htmlFor="female">여성</label>
          <button onClick={() => setPage(3)}>다음</button>
        </div>
      )}
      { page === 3 && (
        <div>
          <h2>신체정보를 알려주세요</h2>
          <h3>키</h3>
          <input type="text" name="birthYear_input" placeholder='YYYY'
            value={userInfo.birthYear} onChange={handleInputChange} />
          <h3>체중</h3>
          <input type="radio" id="male" name="gender_input1"
            value="male" checked={userInfo.gender === 'male'} onChange={handleInputChange} />
          <button onClick={() => setPage(4)}>다음</button>
        </div>
      )}
      { page === 4 && (
        <div>
          <h2>지역을 선택해주세요</h2>
          
          <button onClick={() => setPage(3)}>다음</button>
        </div>
      )}
    </div>
  )
}

// const Signup = function () {
//   const [page, setPage] = useState(1);
//   const [userInfo, setUserInfo] = useState({
//     nickname: '',
//     birthYear: '',
//     gender: '',
//     height: '',
//     weight: '',
//     address: '',
//   });
//   const [isNicknameChecked, setIsNicknameChecked] = useState(false);

//   const handleInputChange = (e) => {
//     const { name, value } = e.target;
//     setUserInfo((prevInfo) => ({
//       ...prevInfo,
//       [name]: value,
//     }));
//   };

//   const checkNicknameAvailability = () => {
//     // API 호출을 통한 닉네임 중복 검사 후 결과에 따라 setIsNicknameChecked 업데이트
//     setIsNicknameChecked(true);
//   };

//   const years = Array.from(new Array(100), (val, index) => new Date().getFullYear() - index);

//   const canProceed = userInfo.nickname && isNicknameChecked && userInfo.birthYear && userInfo.gender && userInfo.height && userInfo.weight;

//   return (
//     <div className={styles.container}>
//       {page === 1 && (
//         <div>
//           <h1>회원 정보 입력</h1>
//           <input
//             type="text"
//             name="nickname"
//             placeholder="닉네임"
//             value={userInfo.nickname}
//             onChange={handleInputChange}
//           />
//           <button onClick={checkNicknameAvailability}>닉네임 중복 체크</button>
//           {isNicknameChecked && (
//             <>
//               <select name="birthYear" value={userInfo.birthYear} onChange={handleInputChange}>
//                 <option value="">출생연도</option>
//                 {years.map((year, index) => (
//                   <option key={index} value={year}>
//                     {year}
//                   </option>
//                 ))}
//               </select>
//               {userInfo.birthYear && (
//                 <>
//                   <div>
//                     <input
//                       type="radio"
//                       id="male"
//                       name="gender"
//                       value="male"
//                       checked={userInfo.gender === 'male'}
//                       onChange={handleInputChange}
//                     />
//                     <label htmlFor="male">남성</label>
//                     <input
//                       type="radio"
//                       id="female"
//                       name="gender"
//                       value="female"
//                       checked={userInfo.gender === 'female'}
//                       onChange={handleInputChange}
//                     />
//                     <label htmlFor="female">여성</label>
//                   </div>
//                   {userInfo.gender && (
//                     <>
//                       <input
//                         type="text"
//                         name="height"
//                         placeholder="키 (cm)"
//                         value={userInfo.height}
//                         onChange={handleInputChange}
//                       />
//                       <input
//                         type="text"
//                         name="weight"
//                         placeholder="몸무게 (kg)"
//                         value={userInfo.weight}
//                         onChange={handleInputChange}
//                       />
//                     </>
//                   )}
//                 </>
//               )}
//             </>
//           )}
//           <button disabled={!canProceed} onClick={() => setPage(2)}>다음 페이지</button>
//         </div>
//       )}
//       {page === 2 && (
//         <div>
//           <h1>지역 정보 입력</h1>
//           <input
//             type="text"
//             name="address"
//             placeholder="주소"
//             value={userInfo.address}
//             onChange={handleInputChange}
//           />
//           {/* 여기에서 지역 정보 API 연동 및 저장 로직을 추가 */}
//         </div>
//       )}
//     </div>
//   );
// }

// const Signup = function () {
//   const [userInfo, setUserInfo] = useState({
//     nickname: '',
//     birthYear: '',
//     gender: '',
//     height: '',
//     weight: '',
//     address: '',
//   });
//   const [step, setStep] = useState(1);

//   const handleInputChange = (e) => {
//     const { name, value } = e.target;
//     setUserInfo(prev => ({ ...prev, [name]: value }));
//   };

//   const goToNextStep = () => setStep(prevStep => prevStep + 1);

//   return (
//     <div className={styles.container}>
//       <div className={`input-field ${step >= 4 ? "visible" : ""}`}>
//         {step >= 4 && (
//           <>
//             <input
//               type="text"
//               name="height"
//               placeholder="키 (cm)"
//               value={userInfo.height}
//               onChange={handleInputChange}
//             />
//             <input
//               type="text"
//               name="weight"
//               placeholder="몸무게 (kg)"
//               value={userInfo.weight}
//               onChange={handleInputChange}
//             />
//           </>
//         )}
//         </div>
//         <div className={`input-field ${step >= 3 ? "visible" : ""}`}>
//         {step >= 3 && (
//           <div>
//             <input
//               type="radio"
//               id="male"
//               name="gender"
//               value="male"
//               checked={userInfo.gender === 'male'}
//               onChange={handleInputChange}
//             />
//             <label htmlFor="male">남성</label>
//             <input
//               type="radio"
//               id="female"
//               name="gender"
//               value="female"
//               checked={userInfo.gender === 'female'}
//               onChange={handleInputChange}
//             />
//             <label htmlFor="female">여성</label>
//           </div>
//         )}
//         </div>
//         <div className={`input-field ${step >= 2 ? "visible" : ""}`}>
//         {step >= 2 && (
//           <select name="birthYear" value={userInfo.birthYear} onChange={handleInputChange}>
//             <option value="">출생연도</option>
//             {/* 연도 옵션 생성 로직 */}
//           </select>
//         )}
//         </div>
//         <div className={`input-field ${step >= 1 ? "visible" : ""}`}>
//         <input
//           type="text"
//           name="nickname"
//           placeholder="닉네임"
//           value={userInfo.nickname}
//           onChange={handleInputChange}
//         />
//         </div>
//       {step < 4 && <button onClick={goToNextStep}>다음</button>}
//     </div>
//   );
// }

export default Signup;