import styles from "./Toolbar.module.css";
import Sidebar from "../sidebar/Sidebar";
import { useLocation, useNavigate } from "react-router-dom";
import { getEggMoney } from "../../../apis/wallet";
import { useEffect, useState } from "react";


const Toolbar = function(){

    useEffect(()=>{
        getEggMoney()
            .then((res)=>{
                setEgg(res.egg);
                setMoney(res.money);
            })
    }, [])
    const navigate = useNavigate();
    const [egg, setEgg] = useState(0);
    const [money, setMoney] = useState(0);

    const moveToAlarmPage = function () {
        navigate("/Alarm")
    }


    return(
        <> 
            <div className={styles.tool_container}>               
                <div className={styles.all_money_container}>
                    <div className={styles.egg_container}>
                        <img src="/imgs/egg.png" alt="황금알" className={styles.egg}></img>
                        <p className={styles.egg_txt}>{egg}</p>
                    </div>
                    <div className={styles.money_container}>
                        <img src="/imgs/money.png" alt="황금알" className={styles.money}></img>
                        <p className={styles.money_txt}>{money}</p>
                    </div>
                </div>
                <img src="/imgs/bell.png" alt="알람" className={styles.bell} onClick={moveToAlarmPage}></img>
            </div>
        </>
    );
}

export default Toolbar; 