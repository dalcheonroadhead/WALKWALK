import { instance } from "./axiosModule";

export const getGoogleToken = async (token) => {
    const url = import.meta.env.VITE_NODE_ENV === 'production' ? `oauth/callback/google/token/d-t-d?code=${token}` : `oauth/callback/google/token/l-t-l?code=${token}`;
    const res = await instance.post(url)
    console.log(res.data)
}