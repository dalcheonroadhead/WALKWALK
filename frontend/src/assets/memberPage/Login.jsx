import { useEffect } from "react";
import styles from "./Login.module.css";

const Login = function () {
  const CLIENT_ID = '661916935878-j4hpr01ps70konotjd0kdpaoogt6dfai.apps.googleusercontent.com'
  const REDIRECT_URI = import.meta.env.VITE_NODE_ENV === 'production' ? import.meta.env.VITE_REDIRECT_URI_PROD : import.meta.env.VITE_REDIRECT_URI_DEV;
  const AUTH_URL = `https://accounts.google.com/o/oauth2/auth?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/fitness.activity.read https://www.googleapis.com/auth/fitness.blood_glucose.read https://www.googleapis.com/auth/fitness.blood_pressure.read https://www.googleapis.com/auth/fitness.body.read https://www.googleapis.com/auth/fitness.heart_rate.read https://www.googleapis.com/auth/fitness.body_temperature.read https://www.googleapis.com/auth/fitness.location.read https://www.googleapis.com/auth/fitness.nutrition.read https://www.googleapis.com/auth/fitness.oxygen_saturation.read https://www.googleapis.com/auth/fitness.reproductive_health.read https://www.googleapis.com/auth/fitness.activity.write https://www.googleapis.com/auth/fitness.blood_glucose.write https://www.googleapis.com/auth/fitness.blood_pressure.write https://www.googleapis.com/auth/fitness.body.write https://www.googleapis.com/auth/fitness.heart_rate.write https://www.googleapis.com/auth/fitness.body_temperature.write https://www.googleapis.com/auth/fitness.location.write https://www.googleapis.com/auth/fitness.nutrition.write https://www.googleapis.com/auth/fitness.oxygen_saturation.write https://www.googleapis.com/auth/fitness.reproductive_health.write https://www.googleapis.com/auth/fitness.sleep.write&access_type=offline`
  const handleLogin = () => {
    window.location.href = AUTH_URL
  }

  // useEffect(() => {
  //   const userAgent = navigator.userAgent.toLowerCase();
  //   const appUrl = ''; // 앱 URL 스키마
  //   const androidStoreUrl = 'https://play.google.com/store/apps/details?id=com.google.android.apps.fitness'; // 앱 스토어 URL
  //   const appleStoreUrl = 'https://apps.apple.com/kr/app/google-%ED%94%BC%ED%8A%B8%EB%8B%88%EC%8A%A4-%ED%99%9C%EB%8F%99-%EC%B6%94%EC%A0%81%EA%B8%B0/id1433864494'; // 앱 스토어 URL

  //   if (/iphone|ipad|ipod/.test(userAgent)) {
  //     // iOS에서 실행 중
  //     alert('ios', navigator.userAgent)
  //   } else if (/android/.test(userAgent)) {
  //     // 안드로이드에서 실행 중
  //     alert('android', navigator.userAgent)
  //   } else {
  //     // 모바일 기기가 아닌 경우
  //     alert('not mobile', navigator.userAgent)
  //   }
  // }, []);
  
  return (
    <div className={styles.login_container}>
      <div className={styles.login_app_name}>walk walk</div>
      <img className={styles.login_img} src="/imgs/login_logo.png" alt="login logo" />
      <img className={styles.google_login_btn} onClick={handleLogin} src="/imgs/google_login_btn.png" alt="google login button" />
    </div>
  )
}

export default Login;
