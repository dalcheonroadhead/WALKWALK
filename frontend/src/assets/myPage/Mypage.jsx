import { useState, useEffect } from "react";
import styles from "./Mypage.module.css"
import { getMypage } from "../../apis/member";

const Mypage = function () {
  const [mypageInfo, setMypageInfo] = useState({});
  useEffect(() => {
    getMypage()
  }, []);

  return(
    <div className={styles.mypage_container}>
      
    </div>
  )
}

export default Mypage;