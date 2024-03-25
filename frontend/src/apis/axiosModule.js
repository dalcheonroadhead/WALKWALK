import axios from "axios";

const instance = axios.create({
  baseURL: 'https://j10d210.p.ssafy.io/api',
  // baseURL: 'http://localhost:8081/api',
});

// 요청 인터셉터: 요청을 보내기 전에 실행됩니다.
instance.interceptors.request.use(
  (config) => {
    // 요청을 보내기 전에 실행할 코드를 여기에 작성하세요.
    const token = localStorage.getItem('token');
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    // 요청 에러 처리를 여기서 합니다.
    return Promise.reject(error);
  }
);

// 응답 인터셉터: 응답을 받고 난 후 실행됩니다.
instance.interceptors.response.use(
  (response) => {
    // 응답 데이터를 처리하는 코드를 여기에 작성하세요.
    return response;
  },
  (error) => {
    // 응답 에러 처리를 여기서 합니다.
    return Promise.reject(error);
  }
);

export { instance }
