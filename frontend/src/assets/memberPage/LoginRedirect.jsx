import { useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import styles from "./Login.module.css";
import { getGoogleToken } from "../../apis/member";

const LoginRedirect = function () {
  const navigate = useNavigate();

  useEffect(() => {
    const code = new URLSearchParams(window.location.search).get('code')
    console.log('code : ', code)

    if (code) {
      getGoogleToken(code).then((res) => {
        console.log('test2 :', res);
        navigate(res.isNew ? '/signup' : '/main');
      }).catch((err) => {
        console.error('토큰 가져오기 실패 :', err);
      });
    }
  }, [])
  
  return (
    <div className={styles.login_redirect_container}>
      <img className={styles.login_loading_img} src="/imgs/ch1_bol_normal_run.gif" alt="loading" />
      <div className={styles.login_loading_txt} >로그인 중...</div>
    </div>
  )
}

export default LoginRedirect;