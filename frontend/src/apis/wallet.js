import axios from "axios";
import { instance } from "./axiosModule";

export const createWallet = async () => {
    const url = 'https://wallet-api.klaytnapi.com/v2/account'

    return await axios.post(url, {}, {
      headers: {
        'x-chain-id': '1001',
        Authorization: import.meta.env.VITE_KAS_AUTHORIZATION
        }
      })
        .then((res) => {
          console.log(res)
          return {eoa: res.data.address, publicKey: res.data.publicKey}
        })
        .catch((err) => {console.log(err)})
}

export const getEggMoney = async () => {
  const url = '/wallets/egg-money'

  return await instance.get(url)
      .then((res) => {
        console.log('res : ', res.data)
        return res.data.data
      })
      .catch((err) => {console.log(err)})
}