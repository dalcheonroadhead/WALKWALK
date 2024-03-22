import { create } from 'zustand'

export const useUserStore = create((set) => ({
  isLogin: false,
  token: '',
  removeAllBears: () => set({ bears: 0 }),
  updateBears: (newBears) => set({ bears: newBears }),
}))