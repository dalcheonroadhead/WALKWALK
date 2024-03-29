import { useNavigate } from "react-router-dom";
import styles from "./MyGoalUpdate.module.css";

const MyGoalUpdate = function () {
  const navigate = useNavigate();
  
  return (
    <div className={styles.mygoalupdate_container}>
      <>
        <div className={styles.mygoalupdate_top_bar}>
          <img className={styles.mygoalupdate_back_direct} src="/imgs/direct.png" alt="back_direct" onClick={() => {navigate('/mypage')}}/>
          <span className={styles.mygoalupdate_title}>목표 수정</span>
        </div>
      </>
    </div>
  )
}

export default MyGoalUpdate;