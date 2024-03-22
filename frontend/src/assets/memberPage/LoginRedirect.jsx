import { useEffect } from "react";
import styles from "./LoginRedirect.module.css";
import { getGoogleToken } from "../../apis/memberApi"

const LoginRedirect = function () {
  useEffect(() => {
    const code = new URLSearchParams(window.location.search).get('code')
    console.log('code : ', code)

    if (code) {
      // TODO : 서버에 code 전송
      getGoogleToken(code)
    }
  })
  
  return (
    <div className={styles.login_redirect_container}>
      <img className={styles.login_loading_img} src="/imgs/ch1_bol_normal_run.gif" alt="loading" />
      <div className={styles.login_loading_txt} >로그인 중...</div>
    </div>
  )
}

export default LoginRedirect;