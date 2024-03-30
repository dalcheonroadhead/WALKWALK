import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import styles from "./Login.module.css";
import { getMypage } from "../../apis/member";

const Login = function () {
  const navigate = useNavigate();

  // 구글 로그인 페이지로 이동
  const CLIENT_ID = '661916935878-j4hpr01ps70konotjd0kdpaoogt6dfai.apps.googleusercontent.com'
  const REDIRECT_URI = import.meta.env.VITE_NODE_ENV === 'production' ? import.meta.env.VITE_REDIRECT_URI_PROD : import.meta.env.VITE_REDIRECT_URI_DEV;
  const AUTH_URL = `https://accounts.google.com/o/oauth2/auth?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/fitness.activity.read https://www.googleapis.com/auth/fitness.blood_glucose.read https://www.googleapis.com/auth/fitness.blood_pressure.read https://www.googleapis.com/auth/fitness.body.read https://www.googleapis.com/auth/fitness.heart_rate.read https://www.googleapis.com/auth/fitness.body_temperature.read https://www.googleapis.com/auth/fitness.location.read https://www.googleapis.com/auth/fitness.nutrition.read https://www.googleapis.com/auth/fitness.oxygen_saturation.read https://www.googleapis.com/auth/fitness.reproductive_health.read https://www.googleapis.com/auth/fitness.activity.write https://www.googleapis.com/auth/fitness.blood_glucose.write https://www.googleapis.com/auth/fitness.blood_pressure.write https://www.googleapis.com/auth/fitness.body.write https://www.googleapis.com/auth/fitness.heart_rate.write https://www.googleapis.com/auth/fitness.body_temperature.write https://www.googleapis.com/auth/fitness.location.write https://www.googleapis.com/auth/fitness.nutrition.write https://www.googleapis.com/auth/fitness.oxygen_saturation.write https://www.googleapis.com/auth/fitness.reproductive_health.write https://www.googleapis.com/auth/fitness.sleep.write&access_type=offline`
  const handleLogin = () => {
    window.location.href = AUTH_URL
  }

  // 자동로그인
  useEffect(() => {
    const userInfo = JSON.parse(localStorage.getItem('tokens'))
    // 로컬스토리지에 액세스토큰이 있고, isNew가 false일 때
    if (userInfo?.Authorization && userInfo?.isNew === "false") {
      const getNickname = async () => {
        try {
          const res = await getMypage();
          // 응답 닉네임과 로컬스토리지의 닉네임이 일치하면 자동로그인
          if (res.nickname === userInfo.member_nickname) {
            navigate('/main')
          }
        } catch (err) {
          // 로그인 화면 유지
          console.error(err)
          console.log('자동 로그인 실패')
        }
      };

      getNickname();
      
    } else if (userInfo?.Authorization && userInfo?.isNew === "true") {
      localStorage.clear();
      console.log('로컬스토리지 지움')
    } else {
      // 로그인 화면 유지
      console.log('자동 로그인 실패')
    }
  },[]);
  
  return (
    <div className={styles.login_container}>
      <div className={styles.login_app_name}>walk walk</div>
      <img className={styles.login_img} src="/imgs/login_logo.png" alt="login logo" />
      <img className={styles.google_login_btn} onClick={handleLogin} src="/imgs/google_login_btn.png" alt="google login button" />
    </div>
  )
}

export default Login;
