import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import styles from "./HalliGalli.module.css";
import { gray } from "d3-color";

const HalliGalli = function(){

    const navigate = useNavigate();

    const moveToMainPage = function () {
        navigate("/main")
    }
    

    return(
        <>
            <div className={styles.main_container}>
                <div className={styles.title_container}>
                    <img src="/imgs/direct.png" alt="뒤로가기" className={styles.back_btn} onClick={moveToMainPage}></img>
                    <p className={styles.title_txt}>나의 할리 김모자이크</p>
                </div>
                <div className={styles.profile_container}>
                    <p className={styles.profile_txt}>나의 할리</p>
                    <img src="/imgs/profile_img1.jpg" alt="프로필 이미지" className={styles.profile_img}></img>
                    <p className={styles.profile_name_txt}>김모자이크</p>
                </div>
                <div className={styles.mission_container}>
                    <p className={styles.mission_title}><span style={{color: "#186647", fontFamily: "bc_b"}}>김모자이크</span>님이 등록한 미션</p>
                    <div className={styles.mission_back}>
                        <p className={styles.content_txt}>내용</p>
                        <div className={styles.bottom_line}></div>
                        <div className={styles.content_container}>
                            <img src="/imgs/ch2_bol_money.png" alt="할리 돈 오리" className={styles.ch2_money}></img>
                            <div className={styles.content_txt_container}>
                                <p className={styles.content_detail_txt} style={{marginTop: 25}}>일일 목표 시간 : 60분</p>
                                <p className={styles.content_detail_txt} style={{marginTop: -5}}>총 휴일권 : 4개</p>
                            </div>
                        </div>
                        <p className={styles.money_content_txt}>금액</p>
                        <div className={styles.money_bottom_line}></div>
                        <div className={styles.money_content_container}>
                            <img src="/imgs/money.png" alt="할리 돈 오리" className={styles.money}></img>
                            <div className={styles.money_content_txt_container}>
                                <p className={styles.money_content_detail_txt} style={{marginTop: 25}}>총<span style={{fontSize : '0.7rem', color : "#7F7F7E"}}>(월 단위)</span> : 300,000원</p>
                                <p className={styles.money_content_detail_txt} style={{marginTop: -5}}>일일<span style={{fontSize : '0.7rem', color : "#7F7F7E"}}>(월/해당 월 날짜 수)</span> : 10,000원</p>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </>
    )
}

export default HalliGalli