import { useState, useEffect} from "react";
import { useLocation, useNavigate } from "react-router-dom";
import styles from "./Rank.module.css"

const Rank = function(){
    
    const [tabIndex, setTabIndex] = useState(0);

    const tabClickHandler = function(index){
        setTabIndex(index)
    }


    const friendlist = [
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김도리",
            info: "나는 도리도리 김도리 닭도리탕이 맛있당"
        },
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김고리",
            info: "나는 고리고리 김고리 모든걸 걸 수 있당"
        },
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김노리",
            info: "나는 노리노리 김노리 노리개가 갖고 싶당"
        },
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김로리",
            info: "나는 로리로리 김로리 로리라는 이름은 좀 위험해"
        },
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김모리모리대머리",
            info: "나는 머리머리대머리 이름도 김모리모리대머리"
        },
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김보리",
            info: "나는 보리보리 김보리 보리씌!!! 보리씌!!"
        },
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김소리",
            info: "나는 소리소리 김소리 내 귀는 소머즈 귀 ㅋ"
        },
    ]

    const dayrank = [

    ]

    const tabArr=[{
        tabTitle:(
            <div className={tabIndex===0 ? styles.mode_choose : styles.day_rank_tab} onClick={()=>tabClickHandler(0)}>
                <p className={styles.day_rank_tab_txt}>일</p>
            </div>
        ),
        tabCont:(
            <div className={styles.day_rank_container}>
                <div className={styles.rank_day}>
                    <p>3월 27일</p>
                </div>
                <div className={styles.top_rank_container}>
                    <div className={styles.first_rank_container}>
                        <p className={styles.first_rank_title}>1등</p>
                    </div>
                </div>
            </div>
        )
    },
    {
        tabTitle:(
            <div className={tabIndex===1 ? styles.mode_choose : styles.week_rank_tab} onClick={()=>tabClickHandler(1)}>
                <p className={styles.week_rank_tab_txt}>주</p>
            </div>
        ),
        tabCont:(
            <div >
                
            </div>
        )
    },
    {
        tabTitle:(
            <div className={tabIndex===2 ? styles.mode_choose : styles.month_rank_tab} onClick={()=>tabClickHandler(2)}>
                <p className={styles.month_rank_tab_txt}>월</p>
            </div>
        ),
        tabCont:(
            <div>
                
            </div>
        )
    },
    {
        tabTitle:(
            <div className={tabIndex===3 ? styles.mode_choose : styles.strick_rank_tab} onClick={()=>tabClickHandler(3)}>
                <p className={styles.strick_rank_tab_txt}>스트릭</p>
            </div>
        ),
        tabCont:(
            <div >
                
            </div>
        )
    }]

    return(
        <>
            <div className={styles.main_container}>
                <div className={styles.rank_content_container}>
                    <div className={styles.rank_title}>
                        <p>친구 랭킹</p>
                    </div>  
                    <div className={styles.rank_list_content}>
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
            </div>
        </>
    )
}

export default Rank;