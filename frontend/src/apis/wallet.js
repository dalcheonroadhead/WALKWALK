import axios from "axios";
import { instance } from "./axiosModule";

// 블록체인 지갑 생성
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

// 에그머니 보유량 확인
export const getEggMoney = async () => {
  const url = '/wallets/egg-money'

  return await instance.get(url)
      .then((res) => {
        console.log('res : ', res.data)
        return res.data.data
      })
      .catch((err) => {console.log(err)})
}

// 에그 충전 요청
export const requestMoneyCharge = async (money) => {
  const url = '/payment/kakaoPayReady'
  const info = {
    "cid": "TC0ONETIME",
    "partner_order_id": "walkwalk",
    "partner_user_id": "walkwalk",
    "item_name": "머니",
    "quantity": 1,
    "total_amount": money,
    "tax_free_amount": 0,
    "approval_url":"http://localhost:5173/kakaopay/callback?status=approval",
    "fail_url":"http://localhost:5173/kakaopay/callback?status=fail",
    "cancel_url":"http://localhost:5173/kakaopay/callback?status=cancel"
  }

  return await instance.post(url, info)
      .then((res) => {
        console.log('res : ', res.data)
        return res.data
      })
      .catch((err) => {console.log(err)})
}

// 에그 충전 승인
export const approveMoneyCharge = async (info) => {
  const url = '/payment/kakaoPayApprove'
  const chargeInfo = {
    "cid" : "TC0ONETIME",
    "tid" : info.tid,
    "partner_order_id": "walkwalk",
    "partner_user_id": "walkwalk",
    "pg_token" : info.pgToken,
    "total_amount": info.inputMoney
  }

  return await instance.post(url, chargeInfo)
      .then((res) => {
        console.log('res : ', res)
        return true
      })
      .catch((err) => {
        console.log(err)
        return false
      })
}