import { create } from 'zustand'
import { persist, createJSONStorage } from 'zustand/middleware'

export const useMemberStore = create(
  persist(
    (set) => ({
      // 초기 상태
      tokens: {},
      // 상태 업데이트 메소드
      setToken: (newToken) => set({ tokens: newToken})
    }),
    {
      name: 'member', // name of the item in the storage (must be unique)
      storage: createJSONStorage(() => sessionStorage), // (optional) by default, 'localStorage' is used
    },
  ),
)