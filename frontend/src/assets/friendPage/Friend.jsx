import { useState, useEffect} from "react";
import { useLocation, useNavigate } from "react-router-dom";
import styles from "./Friend.module.css";

const Friend = function(){

    const [tabIndex, setTabIndex] = useState(0);
    const [findFriend, setFindFriend] = useState(false);

    const tabClickHandler = function(index){
        setTabIndex(index)
    }

    const openFindFriendModal = function(){
        setFindFriend(!findFriend)
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

    const tabArr=[{
        tabTitle:(
            <div className={tabIndex===0 ? styles.mode_choose : styles.mode_friend_list} onClick={()=>tabClickHandler(0)}>
                <p className={styles.mode_friend_list_txt}>친구 목록</p>
            </div>
        ),
        tabCont:(
            <div className={styles.friend_list_content}>
                {friendlist.map((data, index) => {
                    return(
                        <>
                            <div key={index} className={styles.friend_container}>
                                <img src={data.pimg} alt="프로필 사진" className={styles.friend_img_container} ></img>
                                <div className={styles.friend_info_container}>
                                    <p className={styles.friend_name_txt}>{data.nickname}</p>
                                    <p className={styles.friend_intro}>{data.info}</p>
                                </div>
                            </div>  
                        </>
                    )
                })}
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
                {friendlist.map((data, index) => {
                    return(
                        <>
                            <div key={index} className={styles.receive_friend_container}>
                                <img src={data.pimg} alt="프로필 사진" className={styles.send_friend_img_container} ></img>
                                <div className={styles.send_friend_info_container}>
                                    <p className={styles.send_friend_name_txt}>{data.nickname}</p>
                                    <div className={styles.send_cancel_btn}>
                                        <p className={styles.cancel_btn_txt}>취소</p>
                                    </div>
                                </div>
                            </div>  
                        </>
                    )
                })}
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
                {friendlist.map((data, index) => {
                    return(
                        <>
                            <div key={index} className={styles.receive_friend_container}>
                                <img src={data.pimg} alt="프로필 사진" className={styles.receive_friend_img_container} ></img>
                                <div className={styles.receive_friend_info_container}>
                                    <p className={styles.receive_friend_name_txt}>{data.nickname}</p>
                                    <div className={styles.receive_btn_container}>
                                        <div className={styles.receive_ok_btn}>
                                            <p className={styles.receive_ok_btn_txt}>수락</p>
                                        </div>
                                        <div className={styles.receive_cancel_btn}>
                                            <p className={styles.receive_cancel_btn_txt}>거절</p>
                                        </div>
                                    </div>
                                </div>
                            </div>  
                        </>
                    )
                })}
            </div>
        )
    }
]

    return(
        <>
            {findFriend && (
                <>
                    <div className={styles.modal_background}></div>
                    <div className={styles.find_friend_modal_container}>
                        <div className={styles.find_friend_title_container}>
                            <p className={styles.find_friend_modal_title}>친구 검색</p>
                            <img src="/imgs/x.png" alt="x" className={styles.find_friend_modal_x} onClick={openFindFriendModal}></img>
                        </div>                                
                        
                        <div className={styles.find_friend_search_container}>
                            <input className={styles.find_friend_search_box}></input>
                            <img className={styles.find_friend_search_icon} src="/imgs/search.png" alt="찾기 아이콘"></img>
                        </div>

                        <div className={styles.find_friend_list_container}>
                            <div className={styles.find_friend_names_container}>

                                {friendlist.map((data, index) => {
                                    return(
                                        <>
                                            <div key={index} className={styles.find_friend_name_container}>
                                                <img src={data.pimg} alt="프로필 사진" className={styles.find_friend_img_container} ></img>
                                                <p className={styles.find_friend_name_txt}>{data.nickname}</p>
                                                <div className={styles.find_friend_modal_btn_container}>
                                                    <div className={styles.find_friend_put_btn}>
                                                        <p className={styles.find_friend_btn_txt}>신청</p>
                                                    </div>
                                                </div>
                                            </div>  
                                        </>
                                    )
                                })}
                            </div>
                        </div>
                    </div>
                </>
            )}
            <div className={styles.main_container}>
                <div className={styles.find_friend_btn_container}>
                    <div className={styles.find_friend_btn} onClick={openFindFriendModal}>
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