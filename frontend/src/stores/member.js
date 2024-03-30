import { create } from "zustand";

export const useStore = create((set) => ({
    memberId: '',
    setMemberId: (memberId) => set({ memberId: memberId }),
  }));

export const useSignupStore = create((set) => ({
  isFirstVisit: false,
  setIsFirstVisit: (isFirst) => set({isFirstVisit: isFirst}),
}))