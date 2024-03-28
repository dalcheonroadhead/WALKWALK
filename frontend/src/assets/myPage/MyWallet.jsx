import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import styles from "./MyWallet.module.css";
import { getEggMoney, requestMoneyCharge } from "../../apis/wallet";
import useWalletStore from "../../stores/wallet";

const MyWallet = function () {
  const navigate = useNavigate();
  const [eggMoney, setEggMoney] = useState();
  const [isChargeModalOpen, setChargeModalOpen] = useState(false);
  const [isExchangeModalOpen, setExchangeModalOpen] = useState(false);
  const { inputMoney, updateInputMoney, updateTid } = useWalletStore();

  useEffect(() => {
    (async () => {
      try {
        const data = await getEggMoney();
        setEggMoney(data);
      } catch (err) {
        console.error('eggmoney 정보를 가져오는 중 에러 발생:', err);
      }
    })();
  }, [])

  const handleInputChange = (e) => {
    if (e.target.value === '') {
      updateInputMoney(0);
    } else {
      updateInputMoney(Number(e.target.value));
    }
  }

  const isMobile = () => {
    // 터치 이벤트 지원 여부 및 화면 크기를 통한 모바일 환경 판별
    return 'ontouchstart' in window || navigator.maxTouchPoints > 1 ;
    // return ('ontouchstart' in window || navigator.maxTouchPoints > 1 ) && window.innerWidth <= 800;
  }

  const submitMoneyCharge = async () => {
    try {
      if (inputMoney === 0) {
        alert('충전할 금액을 입력해주세요!')
      } else {
        const res = await requestMoneyCharge(inputMoney);
        updateTid(res.tid);
  
        setTimeout(() => {
          if (isMobile()) {
            window.location.href = res.next_redirect_mobile_url
          } else {
            window.location.href = res.next_redirect_pc_url
          }
        }, 1000);
      }
    } catch (err) {
      console.error('머니 충전 실패 : ', err)
    }
  }

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
          </div>
          <div>
            <button className={styles.mywallet_btn} onClick={() => {setChargeModalOpen(true)}}>충전</button>
            <button className={styles.mywallet_btn} onClick={() => {setExchangeModalOpen(true)}}>환전</button>
          </div>
          <div className={styles.mywallet_sub_container}>
            <div className={styles.mywallet_sub_title}>거래 내역</div>
          </div>
        </>
      )}
      {isChargeModalOpen && (
        <>
          <div className={styles.mywallet_modal_background}></div>
          <div className={styles.mywallet_modal_container}>
            <img className={styles.mywallet_close} src="/imgs/x.png" alt="" onClick={() => {setChargeModalOpen(false)}}/>
            <h2>머니 충전하기</h2>
            <input className={styles.mywallet_input} type="number" name="inputMoney" placeholder="금액을 입력해주세요" onChange={handleInputChange}/>
            <button onClick={submitMoneyCharge}>충전</button>
          </div>
        </>
      )}
      {isExchangeModalOpen && (
        <>
          <div className={styles.mywallet_modal_background}></div>
          <div className={styles.mywallet_modal_container}>
            <img className={styles.mywallet_close} src="/imgs/x.png" alt="" onClick={() => {setExchangeModalOpen(false)}}/>
            <h2>머니 환전하기</h2>
            
          </div>
        </>
      )}
    </div>
  )
}

export default MyWallet;