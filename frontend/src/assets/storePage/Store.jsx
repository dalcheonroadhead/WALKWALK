import styles from "./Store.module.css"
import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";


const Store = function(){
    const navigate = useNavigate();    

    const moveToMainPage = function () {
        navigate("/main")
    }

    return(
        <>
            <div className={styles.main_container}>
                <div className={styles.title_container}>
                    <img src="/imgs/direct.png" alt="뒤로가기" className={styles.back_btn} onClick={moveToMainPage}></img>
                    <p className={styles.title_txt}>상점</p>
                </div>
                <p className={styles.egg_txt}>내 보유 에그</p>
                <div className={styles.egg_container}>

                </div>
            </div>
        </>
    )
}

export default Store;