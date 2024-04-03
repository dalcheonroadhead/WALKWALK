import { useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import { getGoogleToken } from "../../apis/member";
import Loading from "../common/loading/Loading";

const LoginRedirect = function () {
  const navigate = useNavigate();

  useEffect(() => {
    const code = new URLSearchParams(window.location.search).get('code')

    if (code) {
      getGoogleToken(code).then((res) => {
        navigate(res.isNew === 'false' ? '/main' : '/signup');
      }).catch((err) => {
        console.error('토큰 가져오기 실패 :', err);
        navigate('/')
      });
    } else {
      console.log('인가코드 없음')
      navigate('/')
    }
  }, [])
  
  return (
    <Loading text='로그인 중...'></Loading>
  )
}

export default LoginRedirect;