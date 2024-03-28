import styles from "./Alarm.module.css";
import { useLocation, useNavigate } from "react-router-dom";


const Alarm = function(){

    
    const navigate = useNavigate();
    

    const moveToMainPage = function () {
        navigate("/main")
    }

    const alarmList = [
        {
            content: "새로운 할리 수락 요청 목록이 있습니다. "
        },
        {
            content: "새로운 할리 수락 요청 목록이 있습니다. "
        },
        {
            content: "새로운 할리 수락 요청 목록이 있습니다. "
        },
    ]


    return(
        <> 
            <div className={styles.main_container}>
                <div className={styles.title_container}>
                    <img src="/imgs/direct.png" alt="뒤로가기" className={styles.back_btn} onClick={moveToMainPage}></img>
                    <p className={styles.title_txt}>알림</p>
                </div>
            </div>
        </>
    );
}

export default Alarm; 