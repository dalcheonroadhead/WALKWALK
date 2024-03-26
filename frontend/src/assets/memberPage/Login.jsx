import styles from "./Login.module.css";

const Login = function () {
  const CLIENT_ID = '661916935878-j4hpr01ps70konotjd0kdpaoogt6dfai.apps.googleusercontent.com'
  const REDIRECT_URI = import.meta.env.VITE_NODE_ENV === 'production' ? import.meta.env.VITE_REDIRECT_URI_PROD : import.meta.env.VITE_REDIRECT_URI_DEV;
  const AUTH_URL = `https://accounts.google.com/o/oauth2/auth?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile&access_type=offline`
  console.log('redirect url : ',REDIRECT_URI)

  const handleLogin = () => {
    window.location.href = AUTH_URL
  }
  
  return (
    <div className={styles.login_container}>
      <div className={styles.login_app_name}>walk walk</div>
      <img className={styles.login_img} src="/imgs/login_logo.png" alt="login logo" />
      <img className={styles.google_login_btn} onClick={handleLogin} src="/imgs/google_login_btn.png" alt="google login button" />
    </div>
  )
}

export default Login;