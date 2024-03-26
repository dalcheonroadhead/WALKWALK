import { useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import styles from "./Login.module.css";
import { getGoogleToken } from "../../apis/member";
import { useMemberStore } from "../../stores/member";

const LoginRedirect = function () {
  const navigate = useNavigate();
  const {setToken} = useMemberStore();

  useEffect(() => {
    const code = new URLSearchParams(window.location.search).get('code')
    console.log('code : ', code)

    (async () => {
      if (code) {
        try {
          // 서버에 code 전송
          const response = await getGoogleToken(code);
          console.log('test2', response.data);
          setToken(response.data);
          navigate(response.data.isNew ? '/signup' : '/main');
        } catch (err) {
          console.error('Token fetching failed:', err)
        }
      }
    })();
  }, [])
  
  return (
    <div className={styles.login_redirect_container}>
      <img className={styles.login_loading_img} src="/imgs/ch1_bol_normal_run.gif" alt="loading" />
      <div className={styles.login_loading_txt} >로그인 중...</div>
    </div>
  )
}

export default LoginRedirect;