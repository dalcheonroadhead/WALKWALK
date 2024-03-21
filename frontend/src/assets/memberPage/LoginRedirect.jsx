import { useEffect } from "react";
import styles from "./LoginRedirect.module.css";

const LoginRedirect = function () {
  useEffect(() => {
    const queryParams = new URLSearchParams(window.location.search)
    const codeParam = queryParams.get('code')
    console.log(codeParam)

    // if (code) {
    //   // TODO : 서버에 code 전송
    // }
  })
  
  return (
    <div className={styles.login_redirect_container}>
      <img className={styles.login_redirect_container} src="/imgs/ch1_bol_walk.gif" alt="loading" />
      <p>로그인 중입니다...</p>
    </div>
  )
}

export default LoginRedirect;