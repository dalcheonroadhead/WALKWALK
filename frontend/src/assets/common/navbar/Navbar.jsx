import styles from "./Navbar.module.css";
import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { useStore } from "../../../stores/nav";

const Navbar = function(){
    const {activeIcon, setActiveIcon} = useStore(); 

    const navigate = useNavigate();

    const handleClick = (icon) => {
        setActiveIcon(icon); // 클릭된 아이콘으로 상태를 업데이트

        switch (icon) {
            case "rank":
                navigate("/rank")
                break;
            case "run":
                navigate("/walking")
                break;
            case "home":
                navigate("/main")
                break;
            case "friend":
                navigate("/friend")
                break;
            case "my":
                navigate("/mypage")
                break;
            default:
                break;
        }
    };
    return(
        <>
            <div className={styles.nav_container}>
                <img src={activeIcon === "rank" ? "/imgs/select_rank_icon.png" : "/imgs/rank_icon.png" } alt="랭킹 아이콘" className={styles.rank_item} 
                onClick={() => handleClick("rank")}></img>
                <img src={activeIcon === "run" ? "/imgs/select_run_icon.png" : "/imgs/run_icon.png"} alt="운동 아이콘" className={styles.run_item}
                onClick={() => handleClick("run")}></img>
                <img src={activeIcon === "home" ? "/imgs/select_home_icon.png" : "/imgs/home_icon.png"} alt="홈 아이콘" className={styles.home_item}
                onClick={() => handleClick("home")}></img>
                <img src={activeIcon === "friend" ? "/imgs/select_friend_icon.png" : "/imgs/friend_icon.png"} alt="친구 아이콘" className={styles.friend_item}
                onClick={() => handleClick("friend")}></img>
                <img src={activeIcon === "my" ? "/imgs/select_my_icon.png" : "/imgs/my_icon.png"} alt="마이페이지 아이콘" className={styles.my_item}
                onClick={() => handleClick("my")}></img>
            </div>
        </>
    );
}

export default Navbar; 