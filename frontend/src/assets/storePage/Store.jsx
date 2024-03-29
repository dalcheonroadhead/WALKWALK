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
                <div className={styles.egg_container}>
                    <p className={styles.egg_txt}>내 보유 에그</p>
                    <div className={styles.egg_num_container}>
                        <img src="/imgs/egg.png" alt="알" className={styles.egg}></img>
                        <p className={styles.egg_num_txt}>200알</p>
                    </div>
                </div>
                <div className={styles.item_container}>
                    <p className={styles.item_txt}>아이템</p>
                    <div className={styles.item_type_container}>
                        <img src="/imgs/foot.png" alt="오리발" className={styles.item}></img>
                    </div>
                    <div className={styles.item_detail}>
                        <p className={styles.item_name}>오리발 내밀권</p>
                        <div className={styles.item_egg_num_container}>
                            <img src="/imgs/egg.png" alt="알" className={styles.item_egg}></img>
                            <p className={styles.item_egg_num_txt}>200알</p>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Store;