import styles from "./Login.module.css";

const Login = function () {
  return (
    <div>
      <div className={styles.login_app_name}>walk walk</div>
      <img src="/imgs/login_logo.png" alt="login logo" />
      <img src="/imgs/google_login.svg" alt="google login button" />
    </div>
  )
}

export default Login;