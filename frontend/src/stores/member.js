import { create } from "zustand";

export const useStore = create((set) => ({
    memberId: '',
    setMemberId: (memberId) => set({ memberId: memberId }),
  }));