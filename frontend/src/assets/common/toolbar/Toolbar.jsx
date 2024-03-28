import styles from "./Toolbar.module.css";
import Sidebar from "../sidebar/Sidebar";
import { useLocation, useNavigate } from "react-router-dom";


const Toolbar = function(){

    
    const navigate = useNavigate();
    

    const moveToAlarmPage = function () {
        navigate("/halligalli")
    }


    return(
        <> 
            <div className={styles.tool_container}>               
                <div className={styles.all_money_container}>
                    <div className={styles.egg_container}>
                        <img src="/imgs/egg.png" alt="황금알" className={styles.egg}></img>
                        <p className={styles.egg_txt}>30000</p>
                    </div>
                    <div className={styles.money_container}>
                        <img src="/imgs/money.png" alt="황금알" className={styles.money}></img>
                        <p className={styles.money_txt}>300000</p>
                    </div>
                </div>
                <img src="/imgs/bell.png" alt="알람" className={styles.bell}></img>
            </div>
        </>
    );
}

export default Toolbar; 