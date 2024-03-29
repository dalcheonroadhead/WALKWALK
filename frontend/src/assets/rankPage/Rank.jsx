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
            info: "나는 도리도리 김도리 닭도리탕이 맛있당",
            walk: 12000
        },
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김고리",
            info: "나는 고리고리 김고리 모든걸 걸 수 있당",
            walk: 11000
        },
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김노리",
            info: "나는 노리노리 김노리 노리개가 갖고 싶당",
            walk: 9000
        },
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김로리",
            info: "나는 로리로리 김로리 로리라는 이름은 좀 위험해",
            walk: 5000
        },
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김모리모리대머리",
            info: "나는 머리머리대머리 이름도 김모리모리대머리",
            walk: 4300
        },
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김보리",
            info: "나는 보리보리 김보리 보리씌!!! 보리씌!!",
            walk: 3200
        },
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김소리",
            info: "나는 소리소리 김소리 내 귀는 소머즈 귀 ㅋ",
            walk: 1020
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
                    <div className={styles.top3_ranks}>
                        <div className={styles.first_rank_container}>
                            <p className={styles.first_rank_title}>1등</p>
                            <img src="./imgs/crown1.png" alt="금 왕관" className={styles.first_rank_crown}></img>
                            <div className={styles.first_rank_profile_container}>
                                <img src="/imgs/profile_img1.jpg" alt="프로필 사진" className={styles.first_rank_img}></img>
                                <p className={styles.first_rank_name}>김도리</p>
                            </div>
                        </div>
                        <div className={styles.first_rank_container}>
                            <p className={styles.first_rank_title}>2등</p>
                            <img src="./imgs/crown2.png" alt="금 왕관" className={styles.first_rank_crown}></img>
                            <div className={styles.first_rank_profile_container}>
                                <img src="/imgs/profile_img1.jpg" alt="프로필 사진" className={styles.first_rank_img}></img>
                                <p className={styles.first_rank_name}>김고리</p>
                            </div>
                        </div>
                        <div className={styles.first_rank_container}>
                            <p className={styles.first_rank_title}>3등</p>
                            <img src="./imgs/crown3.png" alt="금 왕관" className={styles.first_rank_crown}></img>
                            <div className={styles.first_rank_profile_container}>
                                <img src="/imgs/profile_img1.jpg" alt="프로필 사진" className={styles.first_rank_img}></img>
                                <p className={styles.first_rank_name}>김노리</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div className={styles.day_ranks_container}>
                    {friendlist.map((data, index) => {
                        return(
                            <>
                                <div key={index} className={styles.day_rank_friend_container}>
                                    <p className={styles.day_rank_txt}>{index + 1}</p>
                                    <img src={data.pimg} alt="프로필 사진" className={styles.day_rank_friend_img_container} ></img>
                                    <p className={styles.day_rank_friend_name_txt}>{data.nickname}</p>
                                    <p className={styles.day_rank_walk_num }>{data.walk}보</p>
                                </div>
                            </>
                        )
                    })}
                
                </div>
                <div className={styles.day_rank_my_container}>
                        <p className={styles.day_rank_my_txt}>1</p>
                        <img src="/imgs/profile_img1.jpg" alt="프로필 사진" className={styles.day_rank_my_img_container} ></img>
                        <p className={styles.day_rank_my_name_txt}>김도리</p>
                        <p className={styles.day_rank_my_walk_num }>12000보</p>
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
            <div className={styles.week_rank_container}>
                <div className={styles.rank_week}>
                    <p>3월 25일 ~ 3월 31일</p>
                </div>
                <div className={styles.top_rank_container}>
                    <div className={styles.top3_ranks}>
                        <div className={styles.first_rank_container}>
                            <p className={styles.first_rank_title}>1등</p>
                            <img src="./imgs/crown1.png" alt="금 왕관" className={styles.first_rank_crown}></img>
                            <div className={styles.first_rank_profile_container}>
                                <img src="/imgs/profile_img1.jpg" alt="프로필 사진" className={styles.first_rank_img}></img>
                                <p className={styles.first_rank_name}>김도리</p>
                            </div>
                        </div>
                        <div className={styles.first_rank_container}>
                            <p className={styles.first_rank_title}>2등</p>
                            <img src="./imgs/crown2.png" alt="금 왕관" className={styles.first_rank_crown}></img>
                            <div className={styles.first_rank_profile_container}>
                                <img src="/imgs/profile_img1.jpg" alt="프로필 사진" className={styles.first_rank_img}></img>
                                <p className={styles.first_rank_name}>김고리</p>
                            </div>
                        </div>
                        <div className={styles.first_rank_container}>
                            <p className={styles.first_rank_title}>3등</p>
                            <img src="./imgs/crown3.png" alt="금 왕관" className={styles.first_rank_crown}></img>
                            <div className={styles.first_rank_profile_container}>
                                <img src="/imgs/profile_img1.jpg" alt="프로필 사진" className={styles.first_rank_img}></img>
                                <p className={styles.first_rank_name}>김노리</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div className={styles.day_ranks_container}>
                    {friendlist.map((data, index) => {
                        return(
                            <>
                                <div key={index} className={styles.day_rank_friend_container}>
                                    <p className={styles.day_rank_txt}>{index + 1}</p>
                                    <img src={data.pimg} alt="프로필 사진" className={styles.day_rank_friend_img_container} ></img>
                                    <p className={styles.day_rank_friend_name_txt}>{data.nickname}</p>
                                    <p className={styles.day_rank_walk_num }>{data.walk}보</p>
                                </div>
                            </>
                        )
                    })}
                
                </div>
                <div className={styles.day_rank_my_container}>
                        <p className={styles.day_rank_my_txt}>1</p>
                        <img src="/imgs/profile_img1.jpg" alt="프로필 사진" className={styles.day_rank_my_img_container} ></img>
                        <p className={styles.day_rank_my_name_txt}>김도리</p>
                        <p className={styles.day_rank_my_walk_num }>12000보</p>
                </div>
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
            <div className={styles.month_rank_container}>
                <div className={styles.rank_month}>
                    <p>3월</p>
                </div>
                <div className={styles.top_rank_container}>
                    <div className={styles.top3_ranks}>
                        <div className={styles.first_rank_container}>
                            <p className={styles.first_rank_title}>1등</p>
                            <img src="./imgs/crown1.png" alt="금 왕관" className={styles.first_rank_crown}></img>
                            <div className={styles.first_rank_profile_container}>
                                <img src="/imgs/profile_img1.jpg" alt="프로필 사진" className={styles.first_rank_img}></img>
                                <p className={styles.first_rank_name}>김도리</p>
                            </div>
                        </div>
                        <div className={styles.first_rank_container}>
                            <p className={styles.first_rank_title}>2등</p>
                            <img src="./imgs/crown2.png" alt="금 왕관" className={styles.first_rank_crown}></img>
                            <div className={styles.first_rank_profile_container}>
                                <img src="/imgs/profile_img1.jpg" alt="프로필 사진" className={styles.first_rank_img}></img>
                                <p className={styles.first_rank_name}>김고리</p>
                            </div>
                        </div>
                        <div className={styles.first_rank_container}>
                            <p className={styles.first_rank_title}>3등</p>
                            <img src="./imgs/crown3.png" alt="금 왕관" className={styles.first_rank_crown}></img>
                            <div className={styles.first_rank_profile_container}>
                                <img src="/imgs/profile_img1.jpg" alt="프로필 사진" className={styles.first_rank_img}></img>
                                <p className={styles.first_rank_name}>김노리</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div className={styles.day_ranks_container}>
                    {friendlist.map((data, index) => {
                        return(
                            <>
                                <div key={index} className={styles.day_rank_friend_container}>
                                    <p className={styles.day_rank_txt}>{index + 1}</p>
                                    <img src={data.pimg} alt="프로필 사진" className={styles.day_rank_friend_img_container} ></img>
                                    <p className={styles.day_rank_friend_name_txt}>{data.nickname}</p>
                                    <p className={styles.day_rank_walk_num }>{data.walk}보</p>
                                </div>
                            </>
                        )
                    })}
                
                </div>
                <div className={styles.day_rank_my_container}>
                        <p className={styles.day_rank_my_txt}>1</p>
                        <img src="/imgs/profile_img1.jpg" alt="프로필 사진" className={styles.day_rank_my_img_container} ></img>
                        <p className={styles.day_rank_my_name_txt}>김도리</p>
                        <p className={styles.day_rank_my_walk_num }>12000보</p>
                </div>
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
            <div className={styles.strick_rank_container}>
                <div className={styles.strick_top_rank_container}>
                    <div className={styles.strick_top3_ranks}>
                        <div className={styles.strick_first_rank_container}>
                            <p className={styles.strick_first_rank_title}>1등</p>
                            <img src="./imgs/crown1.png" alt="금 왕관" className={styles.strick_first_rank_crown}></img>
                            <div className={styles.strick_first_rank_profile_container}>
                                <img src="/imgs/profile_img1.jpg" alt="프로필 사진" className={styles.strick_first_rank_img}></img>
                                <p className={styles.strick_first_rank_name}>김도리</p>
                            </div>
                        </div>
                        <div className={styles.strick_first_rank_container}>
                            <p className={styles.first_rank_title}>2등</p>
                            <img src="./imgs/crown2.png" alt="금 왕관" className={styles.strick_first_rank_crown}></img>
                            <div className={styles.first_rank_profile_container}>
                                <img src="/imgs/profile_img1.jpg" alt="프로필 사진" className={styles.strick_first_rank_img}></img>
                                <p className={styles.strick_first_rank_name}>김고리</p>
                            </div>
                        </div>
                        <div className={styles.strick_first_rank_container}>
                            <p className={styles.strick_first_rank_title}>3등</p>
                            <img src="./imgs/crown3.png" alt="금 왕관" className={styles.strick_first_rank_crown}></img>
                            <div className={styles.strick_first_rank_profile_container}>
                                <img src="/imgs/profile_img1.jpg" alt="프로필 사진" className={styles.strick_first_rank_img}></img>
                                <p className={styles.strick_first_rank_name}>김노리</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div className={styles.strick_ranks_container}>
                    {friendlist.map((data, index) => {
                        return(
                            <>
                                <div key={index} className={styles.strick_rank_friend_container}>
                                    <p className={styles.strick_rank_txt}>{index + 1}</p>
                                    <img src={data.pimg} alt="프로필 사진" className={styles.strick_rank_friend_img_container} ></img>
                                    <p className={styles.strick_rank_friend_name_txt}>{data.nickname}</p>
                                    <p className={styles.strick_rank_walk_num }>{data.walk}보</p>
                                </div>
                            </>
                        )
                    })}
                
                </div>
                <div className={styles.strick_rank_my_container}>
                        <p className={styles.strick_rank_my_txt}>1</p>
                        <img src="/imgs/profile_img1.jpg" alt="프로필 사진" className={styles.strick_rank_my_img_container} ></img>
                        <p className={styles.strick_rank_my_name_txt}>김도리</p>
                        <p className={styles.strick_rank_my_walk_num }>12000보</p>
                </div>
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