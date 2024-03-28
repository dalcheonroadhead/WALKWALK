import { create } from "zustand";
import { persist } from "zustand/middleware";

const useWalletStore = create(
  persist(
    (set) => ({
      inputMoney: 0,
      tid: '',
      updateInputMoney: (newInputMoney) => set({inputMoney: newInputMoney}),
      updateTid: (newTid) => set({tid: newTid}),
    }),
    {
      name: 'kakaopay',
    }
));

export default useWalletStore