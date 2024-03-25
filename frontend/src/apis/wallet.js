import axios from "axios";

// 프론트에서 사용할 api 함수 이름, 인자가 있다면 인자 설정 / 없으면 비워두기
export const createWallet = async () => {
    // API명세서 주소 '/도메인/URI'
    const url = 'https://wallet-api.klaytnapi.com/v2/account'

    // return은 필요할 때만 붙이면 됩니다.
    // instance 뒤에 method 적어주고, url와 넘겨줄 정보가 있다면 같이 인자로 넘겨줍니다.
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