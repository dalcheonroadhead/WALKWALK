import { useEffect, useState } from "react";
import styles from "./Alarm.module.css";
import { useLocation, useNavigate } from "react-router-dom";
import { getAlarmList } from "../../apis/alarm";


const Alarm = function(){

    useEffect(()=>{
        getAlarmList();
    }, [])
    
    const navigate = useNavigate();    

    const moveToMainPage = function () {
        navigate("/main")
    }

    const[alarm, setAlarm] = useState(false);
    const[nowAlarm, setNowAlarm] = useState("")

    const openAlarmModal = function(content){
        setAlarm(!alarm);
        setNowAlarm(content);
    }

    const alarmList = [
        {
            content: "새로운 할리 수락 요청이 있습니다. ",
            ok: "fasle"
        },
        {
            content: "새로운 할리 수락 요청이 있습니다. ",
            ok: "fasle"
        },
        {
            content: "새로운 할리 수락 요청이 있습니다. ",
            ok: "fasle"
        },
        {
            content: "나의 할리 김고리노리도리님이 월간 미션을 변경하였습니다. 확인해보세요!",
            ok: "fasle"
        },
        {
            content: "나의 할리 김수안무거북이와두루미님이 월간 미션을 변경하였습니다. 확인해보세요!",
            ok: "fasle"
        },
        {
            content: "나의 할리 하하하하하하하하하하님이 월간 미션을 변경하였습니다. 확인해보세요!",
            ok: "fasle"
        },
        {
            content: "친구 신청이 있습니다.",
            ok: "true"
        },
        {
            content: "친구 신청이 있습니다.",
            ok: "true"
        },
        {
            content: "친구 신청이 있습니다.",
            ok: "true"
        },
    ]

    return(
        <> 
            {alarm && (
                <>
                    <div className={styles.modal_background}></div>
                    <div className={styles.alarm_modal_container}>
                        <div className={styles.alarm_title_container}>
                            <p className={styles.alarm_modal_title}>알림</p>
                            <img src="/imgs/x.png" alt="x" className={styles.alarm_modal_x} onClick={openAlarmModal}></img>
                        </div>
                        <div className={styles.alarm_content}>
                            <img src="/imgs/alarm.png" alt="알림" className={styles.alarm_img}></img>
                            <p className={styles.alarm_detail}>{nowAlarm}</p>
                        </div>
                       
                    </div>
                </>
            )}
            <div className={styles.main_container}>
                <div className={styles.title_container}>
                    <img src="/imgs/direct.png" alt="뒤로가기" className={styles.back_btn} onClick={moveToMainPage}></img>
                    <p className={styles.title_txt}>알림</p>
                </div>
                <div className={styles.alarm_list_container}>
                    {alarmList.map((data, index) => {
                        return(
                            <>
                                <div key={index} className={`${styles.alarm_container} ${data.ok == "true" ? styles.select_alarm_container : ''}`} onClick={() => openAlarmModal(data.content)}>
                                    <img src="/imgs/bell.png" alt="알람사진" className={styles.alarm_img_container} ></img>
                                    <p className={styles.alarm_txt}> {data.content.length > 32 ? `${data.content.slice(0,31)}...`: data.content}</p>
                                </div>  
                            </>
                        )
                    })}
                </div>
            </div>
        </>
    );
}

export default Alarm; 