import { useState, useEffect} from "react";
import { useLocation, useNavigate } from "react-router-dom";
import styles from "./Friend.module.css";

const Friend = function(){

    const [tabIndex, setTabIndex] = useState(0);

    const tabClickHandler = function(index){
        setTabIndex(index)
    }

    const tabArr=[{
        tabTitle:(
            <div className={tabIndex===0 ? styles.mode_choose : styles.mode_friend_list} onClick={()=>tabClickHandler(0)}>
                <p className={styles.mode_friend_list_txt}>친구 목록</p>
            </div>
        ),
        tabCont:(
            <div className={styles.friend_list_content}>
                
            </div>
        )
    },
    {
        tabTitle:(
            <div className={tabIndex===1 ? styles.mode_choose : styles.mode_send_list} onClick={()=>tabClickHandler(1)}>
                <p className={styles.mode_send_list_txt}>신청 목록</p>
            </div>
        ),
        tabCont:(
            <div className={styles.send_list_content}>
                
            </div>
        )
    },
    {
        tabTitle:(
            <div className={tabIndex===2 ? styles.mode_choose : styles.mode_receive_list} onClick={()=>tabClickHandler(2)}>
                <p className={styles.mode_receive_list_txt}>받은 목록</p>
            </div>
        ),
        tabCont:(
            <div className={styles.receive_list_content}>
                
            </div>
        )
    }
]

    return(
        <>
            <div className={styles.main_container}>
                <div className={styles.find_friend_btn_container}>
                    <div className={styles.find_friend_btn}>
                        <p className={styles.btn_txt}>닉네임으로 친구찾기</p>
                    </div>
                </div>
                <div className={styles.tab_container}>
                    <div className={styles.mode_tabs}>
                        {tabArr.map((mode, index)=>{
                            return mode.tabTitle
                        })}
                    </div>

                    <div>
                        {tabArr[tabIndex].tabCont}
                    </div>
                </div>
            </div>
        </>
    )
}

export default Friend;