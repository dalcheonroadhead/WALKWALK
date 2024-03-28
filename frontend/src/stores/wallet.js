import { create } from "zustand";

export const useWalletStore = create((set) => ({
  inputMoney: 0,
  tid: '',
  setInputMoney: (inputMoney) => set({inputMoney: inputMoney}),
  setTid: (tid) => set({tid: tid}),
}));

export default useWalletStore