import create from 'zustand';

const useAlarmStore = create((set) => ({
  selectAlarmIndex: [],
  setSelectAlarmIndex: (index) => set({ selectAlarmIndex: index }),
}));

export default useAlarmStore;
