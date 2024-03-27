import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import styles from "./Mypage.module.css"
import { getMypage } from "../../apis/member";

const Mypage = function () {
  const navigate = useNavigate();
  const [mypageInfo, setMypageInfo] = useState(null);
  useEffect(() => {
    (async () => {
      try {
        const data = await getMypage();
        setMypageInfo(data);
      } catch (error) {
        console.error('mypage 정보를 가져오는 중 에러 발생:', error);
      }
    })();
  }, []);

  return(
    <div className={styles.mypage_container}>
      {mypageInfo && (
        <>
          <img src={mypageInfo.profileUrl} alt="my profile img" />
          <div>{mypageInfo.nickname}</div>
          <div>{mypageInfo.comment}</div>
          <button className={styles.mypage_btn} onClick={() => {navigate('/userinfoupdate')}}>
            <span>내 정보 수정</span>
            <img className={styles.mypage_direct} src="/imgs/direct.png" alt="" />
          </button>
          <button className={styles.mypage_btn} onClick={() => {navigate('/mywallet')}}>
            <span>내 금고</span>
            <img className={styles.mypage_direct} src="/imgs/direct.png" alt="" />
          </button>
          <button className={styles.mypage_btn} onClick={() => {navigate()}}>
            <span>내 배지</span>
            <img className={styles.mypage_direct} src="/imgs/direct.png" alt="" />
          </button>
        </>
      )}
    </div>
  )
}

export default Mypage;