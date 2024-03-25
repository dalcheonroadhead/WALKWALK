import { create } from 'zustand'
import { persist, createJSONStorage } from 'zustand/middleware'

export const useMemberStore = create(
  persist(
    (set) => ({
      // 초기 상태
      isLogin: false,
      tokens: {
        accessToken: '',
        refreshToken: '',
        googleAccessToken: '',
        googleRefreshToken: '',
      },
      // 상태 업데이트 메소드
      setToken: (tokens) => set((state) => ({
        tokens: {
          ...state.tokens,
          accessToken: tokens.Authorization,
          refreshToken: tokens.Refresh_Token,
          googleAccessToken: tokens.Google_access_token,
          googleRefreshToken: tokens.Google_refresh_token,
        }
      }))
    }),
    {
      name: 'member', // name of the item in the storage (must be unique)
      storage: createJSONStorage(() => sessionStorage), // (optional) by default, 'localStorage' is used
    },
  ),
)