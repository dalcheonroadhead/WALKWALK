import create from 'zustand';

export const useStore = create((set) => ({
  activeIcon: 'home',
  setActiveIcon: (icon) => set({ activeIcon: icon }),
}));
