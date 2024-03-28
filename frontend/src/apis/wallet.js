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

export const chargeMoney = async (money) => {
  const url = '/payment/kakaoPayReady'
  const info = {
    "cid": "TC0ONETIME",
    "partner_order_id": "walkwalk",
    "partner_user_id": "h_chaenn",
    "item_name": "money",
    "quantity": 1, 
    "total_amount": money,
    "tax_free_amount": 0,
    "approval_url":"http://localhost:5173/mywallet",
    "fail_url":"http://localhost:5173/mywallet",
    "cancel_url":"http://localhost:5173/mywallet"
  }

  await instance.post(url, info)
      .then((res) => {
        console.log('res : ', res)
      })
      .catch((err) => {console.log(err)})
}
