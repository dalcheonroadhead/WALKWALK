import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import styles from "./Mypage.module.css";
import { getMypage } from "../../apis/member";

const Mypage = function () {
  const navigate = useNavigate();
  const [mypageInfo, setMypageInfo] = useState(null);
  useEffect(() => {
    (async () => {
      try {
        const data = await getMypage();
        setMypageInfo(data);
      } catch (err) {
        console.error('mypage 정보를 가져오는 중 에러 발생:', err);
      }
    })();
  }, []);

  const handleLogout = () => {
    // 로컬 스토리지 값 비우기
    localStorage.clear()
    // 로그인화면으로 이동
    navigate('/')
  }

  return(
    <div className={styles.mypage_container}>
      {mypageInfo && (
        <>
          <div className={styles.mypage_profile_container}>
            <img className={styles.mypage_profile_img} src={mypageInfo.profileUrl} alt="my profile img" />
            <div className={styles.mypage_profile_nickname}>{mypageInfo.nickname}</div>
            <div>{mypageInfo.comment}</div>
          </div>
          <button className={styles.mypage_btn} onClick={() => {navigate('/userinfo/update')}}>
            <span>개인정보 변경</span>
            <img className={styles.mypage_direct} src="/imgs/direct.png" alt="" />
          </button>
          <button className={styles.mypage_btn} onClick={() => {navigate('/mygoal/update')}}>
            <span>목표 수정</span>
            <img className={styles.mypage_direct} src="/imgs/direct.png" alt="" />
          </button>
          <button className={styles.mypage_btn} onClick={() => {navigate('/mywallet')}}>
            <span>내 금고</span>
            <img className={styles.mypage_direct} src="/imgs/direct.png" alt="" />
          </button>
          <button className={styles.mypage_btn} onClick={() => {navigate('/mybadge')}}>
            <span>내 배지</span>
            <img className={styles.mypage_direct} src="/imgs/direct.png" alt="" />
          </button>

          <button className={styles.mypage_logout_btn} onClick={handleLogout}>로그아웃</button>
        </>
      )}
    </div>
  )
}

export default Mypage;