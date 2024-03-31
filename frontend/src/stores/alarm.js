import { create } from 'zustand';

const useAlarmStore = create((set) => ({
  notification: null,
  subscriptionId: null,
  hasArrived: false,
  setNotification: (notification) => set({ notification }),
  setSubscriptionId: (id) => set({ subscriptionId: id }),
  setHasArrived: (b) => set(b)
}));

export default useAlarmStore;
