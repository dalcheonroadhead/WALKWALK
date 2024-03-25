import { create } from 'zustand'
import { persist, createJSONStorage } from 'zustand/middleware'

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

export const useBearStore = create(
  persist(
    (set, get) => ({
      bears: 0,
      addABear: () => set({ bears: get().bears + 1 }),
    }),
    {
      name: 'food-storage', // name of the item in the storage (must be unique)
      storage: createJSONStorage(() => sessionStorage), // (optional) by default, 'localStorage' is used
    },
  ),
)