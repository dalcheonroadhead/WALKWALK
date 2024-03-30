import styles from "./SendVoice.module.css"
import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";


const Store = function(){
    const navigate = useNavigate();    

    const moveToVoicePage = function () {
        navigate("/voice")
    }

    return(
        <>
            <div className={styles.main_container}>
                <div className={styles.title_container}>
                    <img src="/imgs/direct.png" alt="뒤로가기" className={styles.back_btn} onClick={moveToVoicePage}></img>
                    <p className={styles.title_txt}>응원 메시지 보내기</p>
                </div>
                <div className={styles.voice_btn_container}>
                    <div className={styles.egg_container}>
                        <p className={styles.btn_txt}>응원 메시지<br></br>보내기</p>
                        <img src="/imgs/mike.png" alt="마이크" className={styles.mike}></img>
                    </div>
                    <div className={styles.item_container}>
                        <p className={styles.btn_txt}>응원 메시지<br></br>보관함</p>
                        <img src="/imgs/file.png" alt="파일" className={styles.file}></img>
                    </div>
                    <div className={styles.back}>
                        <img src="/imgs/ch1_bol_longwalk.gif" alt="걷는 오리" className={styles.walk_ori}></img>
                        <img src="/imgs/ground.png" alt="땅 이미지" className={styles.ground}></img>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Store;