import { create } from 'zustand'

export const useUserStore = create((set) => ({
  isLogin: false,
  tokens: {
    accessToken: '',
    refreshToken: '',
    googleAccessToken: '',
    googleRefreshToken: '',
  },
  setToken: (tokens) => set({
    accessToken: tokens.Authorization,
    refreshToken: tokens.Refresh_Token,
    googleAccessToken: tokens.Google_access_token,
    googleRefreshToken: tokens.Google_refresh_token,
  })
}))