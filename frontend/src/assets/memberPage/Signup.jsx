import { useState } from 'react';

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
    <div>
      { page === 1 && (
        <div>
          <h1>닉네임을 만들어볼까요?</h1>
          <input type="text" name="nickname_input" 
            value={userInfo.nickname} onChange={handleInputChange} />
          <button onClick={() => setPage(2)}>다음</button>
        </div>
      )}
      { page === 2 && (
        <div>
          <h1>인적 사항을 알려주세요</h1>
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
          <h1>신체정보를 알려주세요</h1>
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
          <h1>지역을 선택해주세요</h1>
          
          <button onClick={() => setPage(3)}>다음</button>
        </div>
      )}
    </div>
  )
}

export default Signup;