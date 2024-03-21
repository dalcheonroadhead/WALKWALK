import styles from "./Login.module.css";

const Login = function () {

  return (
    <div className={styles.login_container}>
      <div className={styles.login_app_name}>walk walk</div>
      <img src="/imgs/login_logo.png" alt="login logo" />
      <a href="https://accounts.google.com/o/oauth2/auth?client_id=661916935878-j4hpr01ps70konotjd0kdpaoogt6dfai.apps.googleusercontent.com&redirect_uri=http://localhost:3000/oauth/callback/google/token&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile">
        <img className={styles.google_login_btn} src="/imgs/google_login.svg" alt="google login button" />
      </a>
    </div>
  )
}

export default Login;