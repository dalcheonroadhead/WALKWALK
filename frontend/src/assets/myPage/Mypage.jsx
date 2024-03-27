import Calendar from "../common/calendar/Calendar";
import Sidebar from "../common/sidebar/Sidebar";
import styles from "./Mypage.module.css"

const Mypage = function(){
    return(
        <>
            <div className={styles.main_container}>
                <h1>마이 페이지</h1>
                <Calendar/>
            </div>
        </>
    )
}

export default Mypage;