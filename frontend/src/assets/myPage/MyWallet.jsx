import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import styles from "./MyWallet.module.css"
import { getEggMoney } from "../../apis/wallet";

const MyWallet = function () {
  const navigate = useNavigate();
  const [eggMoney, setEggMoney] = useState();
  const [isChargeModalOpen, setChargeModalOpen] = useState(false);
  const [isExchangeModalOpen, setExchangeModalOpen] = useState(false);
  

  useEffect(() => {
    (async () => {
      try {
        const data = await getEggMoney();
        console.log('eggmoney : ', data)
        setEggMoney(data)
      } catch (err) {
        console.error('eggmoney 정보를 가져오는 중 에러 발생:', err);
      }
    })();
  }, [])

  return(
    <div className={styles.mywallet_container}>
      {eggMoney && (
        <>
          <div className={styles.mywallet_top_bar}>
            <img className={styles.mywallet_back_direct} src="/imgs/direct.png" alt="back_direct" onClick={() => {navigate('/mypage')}}/>
            <span className={styles.mywallet_title}>내 금고</span>
          </div>
          <div className={styles.mywallet_sub_container}>
            <div className={styles.mywallet_sub_title}>내 자산</div>
            <div className={styles.mywallet_2col}>
              <div className={styles.mywallet_col}>
                <img className={styles.mywallet_col_img} src="/imgs/egg.png" alt="" />
                <div>{eggMoney.egg}</div>
              </div>
              <div className={styles.mywallet_col}>
                <img className={styles.mywallet_col_img} src="/imgs/money.png" alt="" />
                <div>{eggMoney.money}</div>
              </div>
            </div>
            <button className={styles.mywallet_btn} onClick={() => {setChargeModalOpen(true)}}>충전</button>
            <button className={styles.mywallet_btn} onClick={() => {setExchangeModalOpen(true)}}>환전</button>
          </div>
        </>
      )}
      {isChargeModalOpen && (
        <>
          <div className={styles.mywallet_modal_background}></div>
          <div className={styles.mywallet_modal_container}></div>
        </>
      )}
      {isExchangeModalOpen && (
        <>
        </>
      )}
    </div>
  )
}

export default MyWallet;