import { axiosInstance } from "./axiosModule";

export const getGoogleToken = async (token) => {
    const url = import.meta.env.VITE_NODE_ENV === 'production' ? `api/oauth/callback/google/token/d-t-d?code=${token}` : `api/oauth/callback/google/token/l-t-l?code=${token}`;
    const res = await axiosInstance.post(url)
    console.log(res.data)
}