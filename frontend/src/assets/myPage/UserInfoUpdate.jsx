import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import styles from "./UserInfoUpdate.module.css";
import { getMyInfo } from "../../apis/member";

const UserInfoUpdate = function () {
  const navigate = useNavigate();
  const [myInfo, setMyInfo] = useState();
  const [newInfo, setNewInfo] = useState({});


  useEffect(() => {
    (async () => {
      try {
        const data = await getMyInfo();
        setNewInfo(data);
        console.log('myInfo : ', myInfo)
      } catch (err) {
        console.error('userInfo 정보를 가져오는 중 에러 발생:', err);
      }
    })();
  }, [])

  // const handleProfileChange = (e) => {
  // }

  // const handleSubmit = async () => {
  //   const formData = new FormData();
  //   formData.append('');
  // }

  return(
    <div className={styles.userinfoupdate_container}>
      {newInfo && (
        <>
          <div className={styles.userinfoupdate_top_bar}>
            <img className={styles.userinfoupdate_back_direct} src="/imgs/direct.png" alt="back_direct" onClick={() => {navigate('/mypage')}}/>
            <span className={styles.userinfoupdate_title}>내 정보 수정</span>
          </div>
          {/* <div className={styles.userinfoupdate_sub_container}>
            <div>
              <label className={styles.userinfoupdate_profile_container} htmlFor="profileInput">
                <img className={styles.userinfoupdate_profile_img} src={myInfo.profileUrl} alt="profile img" />
                <img className={styles.userinfoupdate_camera_img} src="/imgs/camera.png" alt="camera img" />
              </label>
              <input id="profileInput" type="file" style={{'display': 'none'}} accept=".png, .jpg, .jpeg" onChange={handleProfileChange}/>
            </div>
            <div>
              <div className={styles.signup_title}>닉네임</div>
              <input className={styles.signup_input} type="text" placeholder='닉네임을 입력해주세요' name="nickname" value={userInfo.nickname} onChange={handleInputChange}/>
              {nicknameError && <p className={styles.signup_error} style={{color: nicknameErrorType ? 'green' : 'red'}}>{nicknameError}</p>}
            </div>
            <div>
              <div className={styles.signup_title}>한줄소개</div>
              <input className={styles.signup_input} type="text" placeholder='ex) 01011111111' name="phoneNumber" onChange={handleInputChange}/>
            </div>
            <div className={styles.signup_2col}>
              <div>
                <div className={styles.signup_title}>출생연도</div>
                <select className={styles.signup_input} name="selectedYear" value={selectedYear} onChange={handleInputChange}>
                  <option value="" disabled>출생연도를 선택해주세요</option>
                  {years.map((year) => (
                    <option key={year} value={year}>{year}</option>
                  ))}
                </select>
              </div>
              <div>
                <div className={styles.signup_title}>성별</div>
                <select className={styles.signup_input} name="gender" defaultValue="" onChange={handleInputChange}>
                  <option value="" disabled>성별을 선택해주세요</option>
                  <option value="MALE">남성</option>
                  <option value="FEMALE">여성</option>
                </select>
              </div>
            </div>
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
            <div>
              <div className={styles.signup_title}>전화번호</div>
              <input className={styles.signup_input} type="text" placeholder='ex) 01011111111' name="phoneNumber" onChange={handleInputChange}/>
            </div>
            <div>
              <div className={styles.signup_title}>지역정보</div>
              <input className={styles.signup_input} type="text" placeholder='주소를 입력해주세요' name="location" onClick={daumPost} value={userInfo.location} readOnly/>
            </div>
            <button className={styles.signup_btn} disabled={isButtonDisabled} onClick={handleSubmit}>수정</button>
          </div> */}
        </>
      )}
    </div>
  )
}

export default UserInfoUpdate;