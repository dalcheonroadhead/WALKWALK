import { useState, useEffect} from "react";
import { useLocation, useNavigate } from "react-router-dom";
import styles from "./Main.module.css";
import { ResponsiveBar } from "@nivo/bar";
import { color } from "d3-color";
import { getGalleyList, getHalleyList, postGalleyRequest } from "../../apis/halleygalley";
import { searchGalleyMemberList } from "../../apis/friend";
import {updateGoogleToken} from "../../apis/member";
import {getRealtimeExerciseData} from "../../apis/exercise";

const Main = function(){

    useEffect(()=>{
        getHalleyList()
            .then((res)=>{
                if(res){
                    // setHalliList(getHalleyListResponse);
                    setHalli(true);
                }
                else{
                    setHalliList([])
                    setHalli(false);
                }
            })

        getRealtimeExerciseData()
            .then((res)=>{
              setRealtimeExerciseData(res);
              console.log(res)
            })
    }, [])

    const navigate = useNavigate();
    

    const moveToHalliGalliPage = function () {
        navigate("/halligalli")
    }

    const handleInputChange = (e) => {
        const value = e.target.value;
        // 입력 값에 대한 즉각적인 유효성 검사를 추가
        
        setKeyword(value);
      };
    const handleSearchClick = ()=>{
        searchGalleyMemberList(keyword)
        .then((res)=>{
            setMemberList(res);
        })  
        .catch((err)=>{
            console.log(err);
        })
    }

    const [tabIndex, settabIndex] = useState(0);
    const [halli, setHalli] = useState(true);
    const [galli, setGalli] = useState(true);
    const [isHalliOpen, setIsHalliOpen] = useState(false);
    const [isGalliOpen, setIsGalliOpen] = useState(false);
    const [isHalliListOpen, setIsHalliListOpen] = useState(false);
    const [expanded, setExpanded] = useState(true);
    const [keyword, setKeyword] = useState('');
    const [memberList, setMemberList] = useState([]);
    const [galliList, setGalliList] = useState([]);
    const [halliList, setHalliList] = useState([]);
    const [realtimeExerciseData, setRealtimeExerciseData] = useState({});

    const openHalliModal = function() {
        setIsHalliOpen(!isHalliOpen);
    }

    const openGalliModal = function(){
        setIsGalliOpen(!isGalliOpen);
        handleSearchClick();
    }

    const tabClickHandler = function(index){
        settabIndex(index)
        if(index == 0){

        }
        else if(index == 1){

        }
        else{
            // 갈리 리스트 조회
            getGalleyList().then((res)=>{
                if(res){
                    setGalliList(res);
                    console.log(res)
                    setGalli(true);
                }
                else{
                    setGalliList([])
                    setGalli(false);
                }
            })
        }
    }

    const openHalliListModal = function(){
        setIsHalliListOpen(!isHalliListOpen);   
    }

    const longGalliList = function(){
        setExpanded(!expanded)
    }

    //===========================================================================
    //===========================================================================
    // 나 모달
    const 운동데이터 = {
        criteriaTime: 120,       // 기준 운동시간
        criteriaSteps: 6000,    // 기준 걸음수
    };
    const 프로그래스바 = {
        calculatedTime: realtimeExerciseData.time/운동데이터.criteriaTime*320 < 70 ? 70 : realtimeExerciseData.time/운동데이터.criteriaTime*320,
        calculatedSteps: realtimeExerciseData.steps/운동데이터.criteriaSteps*320 < 90 ? 90 : realtimeExerciseData.steps/운동데이터.criteriaSteps*320,
    }

    const 프로그래스바2 = {
        calculatedTime: realtimeExerciseData.time/운동데이터.criteriaTime*300 < 70 ? 70 : realtimeExerciseData.time/운동데이터.criteriaTime*300,
        calculatedSteps: realtimeExerciseData.steps/운동데이터.criteriaSteps*300 < 90 ? 90 : realtimeExerciseData.steps/운동데이터.criteriaSteps*300,
    }
    

    // 할리 모달
    const 할리API요청결과 = {
        data: {
            timeStamp: "2024-12-11 21:10:10",
            exerciseTime: 10,
            maxExerciseTime: 120,
            
            creteriaContent: { // 기준
                timeStamp: "2024-12-11 21:10:10", // LocalDateTime
                exerciseTime: 19, // Long(minute) (기준 할당량)
            },
        
            requestContent: [ 
                {
                    profileUrl: "https://lh3.googleusercontent.com/a-/ALV-UjWWsoJkyZLe8JNBVN2HAJ8tPX2JcXCcr2bVd44YEapN=s96-c", 
                    timeStamp: "2024-12-11 21:10:10",
                    memberId: 3,
                    nickname: "우주최강수민",
                    requestedTime: 120,
                },
                {
                    profileUrl: "https://lh3.googleusercontent.com/a/ACg8ocIgs9aTv2Ift58zj51IXknNTaXKG2UtAjRVNVjTwyBb=s96-c", 
                    timeStamp: "2024-12-11 21:20:10",
                    memberId: 4,
                    nickname: "우주최강지수",
                    requestedTime: 50,
                },
                {
                    profileUrl: "https://lh3.googleusercontent.com/a/ACg8ocIgs9aTv2Ift58zj51IXknNTaXKG2UtAjRVNVjTwyBb=s96-c", 
                    timeStamp: "2024-12-11 21:20:10",
                    memberId: 4,
                    nickname: "우주최강지수",
                    requestedTime: 20,
                }
            ]
        },
        msg: "할리 목록 조회 성공",
    }

    const namelist = [
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김도리",
        },
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김고리",
        },
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김노리",
        },
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김로리",
        },
        {
            pimg: "/imgs/profile_img1.jpg",
            nickname: "김모리모리대머리",
        },
    ]

    const tabArr=[{
        tabTitle:(
            <div className={tabIndex===0 ? styles.mode_choose : styles.mode_my} onClick={()=>tabClickHandler(0)}>
                <p className={styles.mode_my_txt}>나</p>
            </div>
        ),
        tabCont:(
            <div className={styles.my_content}>
                <div className={styles.my_progress_container}>
                    <div className={styles.my_walk_time}>
                        <p className={styles.time_txt}>오늘 내가 걸은 시간</p>
                        <div className={styles.my_time_progress_container}>
                            <div className={styles.my_time_progress_base}>
                                <div className={styles.my_time_progress_move} style={{width: 프로그래스바.calculatedTime > 320 ? 320 : 프로그래스바.calculatedTime}}></div>
                                <div className={styles.my_time_ori_container} style={{width: 프로그래스바.calculatedTime > 320 ? 320 : 프로그래스바.calculatedTime}}>
                                    <p className={styles.my_time_mine} style={{color: 프로그래스바.calculatedTime > 320 ? 'red' : 'white'}}>{realtimeExerciseData.time}분</p>
                                    <img src={프로그래스바.calculatedTime > 320 ? "/imgs/ch1_bol_samerun.gif" : "/imgs/ch1_bol_samewalk.gif"} alt="제자리걸음 오리" className={styles.ch1_2}></img>
                                </div>
                                <div className={styles.my_time_number_base}>
                                    <p className={styles.my_time_min}>0분</p>
                                    <p className={styles.my_time_min2}>{운동데이터.criteriaTime}분</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className={styles.my_walk_foot}>
                        <p className={styles.foot_txt}>오늘 내가 걸은 걸음수</p>
                        <div className={styles.my_walk_progress_container}>
                            <div className={styles.my_walk_progress_base}>
                                <div className={styles.my_walk_progress_move} style={{width: 프로그래스바.calculatedSteps > 320 ? 320 : 프로그래스바.calculatedSteps}}></div>
                                <div className={styles.my_walk_ori_container} style={{width: 프로그래스바.calculatedSteps > 320 ? 320 : 프로그래스바.calculatedSteps}}>
                                    <p className={styles.my_walk_mine} style={{color: 프로그래스바.calculatedSteps > 320 ? 'red' : 'white'}}>{realtimeExerciseData.steps}보</p>
                                    <img src={프로그래스바.calculatedSteps > 320 ? "/imgs/ch1_bol_samerun.gif" : "/imgs/ch1_bol_samewalk.gif"} alt="제자리걸음 오리" className={styles.ch1_2}></img>
                                </div>
                                <div className={styles.my_walk_number_base}>
                                    <p className={styles.my_walk_min}>0보</p>
                                    <p className={styles.my_walk_min2}>{운동데이터.criteriaSteps}보</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    },
    {
        tabTitle:(
            <div className={tabIndex===1 ? styles.mode_choose : styles.mode_halli} onClick={()=>tabClickHandler(1)}>
                <p className={styles.mode_halli_txt}>할리</p>
            </div>
        ),
        tabCont:(
            <>
                {halli ? (
                    <div className={styles.halli_content}>
                        <div className={styles.halli_container}>
                            <p className={styles.halli_detail}>나의 할리 목록</p> 
                            <div className={styles.my_halli_list_container} onClick={openHalliListModal}>
                                <p className={styles.halli_goal_title}>목표 걷기 시간</p>
                                <div className={styles.halli_time_progress_container}>
                                    <div className={styles.halli_time_progress_base}>
                                        {할리API요청결과.data.requestContent.map((data) =>{
                                            return (
                                                <div className={styles.halli_mission_container} style={{left: `${data.requestedTime/할리API요청결과.data.maxExerciseTime*16}rem`, backgroundImage: 운동데이터.currentTime >= data.requestedTime ? 'url(/public/imgs/yes_marker.png)' : 'url(/public/imgs/no_marker.png)'}}>
                                                    <div className={styles.halli_mission_profile}>
                                                        <img src={data.profileUrl}/>
                                                    </div>
                                                    <h4>
                                                        {data.requestedTime}분
                                                    </h4>
                                                </div>
                                            )
                                        })}
                                        
                                        <div className={styles.halli_time_progress_move} style={{width: 프로그래스바2.calculatedTime > 300 ? 300 : 프로그래스바2.calculatedTime}}></div>
                                        <div className={styles.halli_time_ori_container} style={{width: 프로그래스바2.calculatedTime > 300 ? 300 : 프로그래스바2.calculatedTime}}>
                                            <p className={styles.halli_time_mine} style={{color: 프로그래스바2.calculatedTime > 300 ? 'red' : 'white'}}>{운동데이터.currentTime}분</p>
                                            <img src={프로그래스바2.calculatedTime > 300 ? "/imgs/ch1_bol_samerun.gif" : "/imgs/ch1_bol_samewalk.gif"} alt="제자리걸음 오리" className={styles.ch1_2}></img>
                                        </div>
                                        <div className={styles.halli_time_number_base}>
                                            <p className={styles.halli_time_min}>0분</p>
                                            <p className={styles.halli_time_min2}></p>
                                        </div>
                                    </div>
                                </div>  
                            </div>
                        </div>
                    </div>
                ) : (
                    <div className={styles.no_halli_content}>
                        <div className={styles.no_halli_container}>
                            <p className={styles.no_halli_detail}> 등록된 <br></br> 나의 할리가 <br></br> 없습니다.</p> 
                            <img src="/imgs/ch2_bol_q.png" alt="오리무중 오리" className={styles.no_halli_img}></img>
                        </div>
                        <div className={styles.no_halli_btn} onClick={openHalliModal}>
                            <p className={styles.no_halli_btn_txt} >요청 목록 보러가기</p>
                        </div>

                    </div>
                )}
            </>
            
            
        )
    },
    {
        tabTitle:(
            <div className={tabIndex===2 ? styles.mode_choose : styles.mode_galli} onClick={()=>tabClickHandler(2)}>
                <p className={styles.mode_galli_txt}>갈리</p>
            </div>
        ),
        tabCont:(
            <>
            {galli ? (
                <div className={styles.galli_content} style={{overflow: expanded && 'hidden'}}>
                    <div className={styles.galli_container}>
                        <div className={styles.galli_list_title_container}>
                            <p className={styles.galli_detail}>나의 갈리 목록</p>
                            <img src="/imgs/direct.png" alt="확장 버튼" className={`${styles.galli_direct_btn} ${expanded ? '' : styles.galli_direct_btn2}`} onClick={longGalliList}></img>
                        </div>
                        <div className={styles.galli_expanded} style={{height: 240 * galliList.length, overflow: !expanded && 'scroll'}}>
                        {galliList.map((data, index) => {
                            return(
                                <>  
                                    <div className={styles.my_galli_list_container}>
                                        <p className={styles.galli_goal_title}>나의 갈리 <span style={{color: "#186647", fontFamily: "bc_b"}}>{data.nickname}</span>님의 <br></br>운동기록</p>
                                        <div className={styles.galli_time_progress_container}>
                                            {data.requestedTime == null ? <p>설정안됨</p> :<div className={styles.galli_time_progress_base}>
                                                <div className={styles.galli_time_progress_move} style={{width: 프로그래스바2.calculatedTime > 300 ? 300 : 프로그래스바2.calculatedTime}}></div>
                                                <div className={styles.galli_time_ori_container} style={{width: 프로그래스바2.calculatedTime > 300 ? 300 : 프로그래스바2.calculatedTime}}>
                                                    <p className={styles.galli_time_mine} style={{color: 프로그래스바2.calculatedTime > 300 ? 'red' : 'white'}}>{운동데이터.currentTime}분</p>
                                                    <img src={프로그래스바2.calculatedTime > 300 ? "/imgs/ch1_bol_samerun.gif" : "/imgs/ch1_bol_samewalk.gif"} alt="제자리걸음 오리" className={styles.ch1_2}></img>
                                                </div>  
                                                <div className={styles.galli_time_number_base}>
                                                    <p className={styles.galli_time_min}>0분</p>
                                                    <p className={styles.galli_time_min2}>{운동데이터.criteriaTime}분</p>
                                                </div>
                                            </div>}
                                        </div>
                                    </div>
                                </>
                            )
                        })}
                        <div className={styles.galli_btn} onClick={openGalliModal}>
                            <div className={styles.galli_btn_txt}>나의 갈리 추가하기</div>
                        </div>

                        </div>
                    </div>
                </div>
            ) : (
                <div className={styles.no_galli_content}>
                    <div className={styles.no_galli_container}>
                        <p className={styles.no_galli_detail}> 등록된 <br></br> 나의 갈리가 <br></br> 없습니다.</p> 
                        <img src="/imgs/ch1_bol_q.png" alt="오리무중 오리" className={styles.no_halli_img}></img>
                    </div>    
                    <div className={styles.no_galli_btn} onClick={openGalliModal}>
                        <p className={styles.no_galli_btn_txt}>나의 갈리 등록하기</p>
                    </div>
                </div>
                )}
            </>
        )
    }
        
    ]
    return(
        <>
        <div>
            {isHalliOpen && (
                <>
                    <div className={styles.modal_background}></div>
                    <div className={styles.halli_modal_container}>
                        <div className={styles.halli_title_container}>
                            <p className={styles.halli_modal_title}>할리 요청 목록</p>
                            <img src="/imgs/x.png" alt="x" className={styles.halli_modal_x} onClick={openHalliModal}></img>
                        </div>
                        <div className={styles.halli_list_container}>
                                {namelist.map((data, index) => {
                                    return(
                                        <>
                                            <div key={index} className={styles.halli_name_container}>
                                                <img src={data.pimg} alt="프로필 사진" className={styles.halli_img_container} ></img>
                                                <p className={styles.halli_name_txt}>{data.nickname}</p>
                                                <div className={styles.halli_btn_container}>
                                                    <div className={styles.halli_ok_btn}>
                                                        <p className={styles.btn_txt}>수락</p>
                                                    </div>
                                                    <div className={styles.halli_no_btn}>
                                                        <p className={styles.btn_txt}>거절</p>
                                                    </div>
                                                </div>
                                            </div>  
                                        </>
                                    )
                                })}
                        </div>
                       
                    </div>
                </>
            )}

            {isGalliOpen && (
                <>
                    <div className={styles.modal_background}></div>
                    <div className={styles.galli_modal_container}>
                        <div className={styles.galli_title_container}>
                            <p className={styles.galli_modal_title}>갈리 요청하기</p>
                            <img src="/imgs/x.png" alt="x" className={styles.galli_modal_x} onClick={openGalliModal}></img>
                        </div>                                
                        
                        <div className={styles.galli_search_container}>
                            <input className={styles.galli_search_box}  value={keyword} onChange={handleInputChange}></input>
                            <img className={styles.galli_search_icon} src="/imgs/search.png" alt="찾기 아이콘" onClick={handleSearchClick}></img>
                        </div>

                        <div className={styles.galli_list_container}>
                            <div className={styles.galli_names_container}>

                                {memberList.map((data, index) => {
                                    return(
                                        <>
                                            <div key={index} className={styles.galli_name_container}>
                                                <img src={data.profileUrl} alt="프로필 사진" className={styles.galli_img_container} ></img>
                                                <p className={styles.galli_name_txt}>{data.nickname}</p>
                                                <div className={styles.galli_btn_container}>
                                                    <div className={styles.galli_put_btn}>
                                                        <p className={styles.btn_txt} onClick={()=>{
                                                            postGalleyRequest({'memberId': data.memberId});
                                                            setIsGalliOpen(false);
                                                            setMemberList([]);
                                                            }}>신청</p>
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

            {isHalliListOpen && (
                <>
                    <div className={styles.modal_background}></div>
                    <div className={styles.my_halli_list_modal_container}>
                        <div className={styles.my_halli_list_title_container}>
                            <p className={styles.my_halli_list_modal_title}>나의 할리 목록</p>
                            <img src="/imgs/x.png" alt="x" className={styles.my_halli_list_modal_x} onClick={openHalliListModal}></img>
                        </div>
                        <div className={styles.my_halli_list_list_container}>
                            <div className={styles.my_halli_list_names_container}>
                                {namelist.map((data, index) => {
                                    return(
                                        <>
                                            <div key={index} className={styles.my_halli_list_name_container} onClick={moveToHalliGalliPage}>
                                                <img src={data.pimg} alt="프로필 사진" className={styles.my_halli_list_img_container} ></img>
                                                <p className={styles.my_halli_list_name_txt}>{data.nickname}</p>
                                                <div className={styles.my_halli_list_btn_container}>
                                                    <img src="/imgs/direct.png" alt="화살표" className={styles.direct_btn}></img>
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

                
                <div className={styles.week_graph_container}>
                    <div className={styles.week_content_container}>
                        <p className={styles.week_title}>이번주 나의 기록</p>
                        <p className={styles.week_detail}>걸음 수 평균</p>
                        <div className={styles.avg_container}>
                            <p className={styles.week_avg_number}>6704</p>
                            <p className={styles.week_avg_walk}>걸음</p>
                        </div>
                        <div className={styles.graph_container}>
                            <div className={styles.graph_back}></div>
                            <div className={styles.graph_style}>
                            <ResponsiveBar /**
                         * chart에 사용될 데이터
                         */
                         data={[
                            {
                                "요일": "월",
                                "걸음 수": 6204,
                              },
                              {
                                "요일": "화",
                                "걸음 수": 12311,
                              },
                              {
                                "요일": "수",
                                "걸음 수": 3234,
                              },
                              {
                                "요일": "목",
                                "걸음 수": 2311,
                              },
                              {
                                "요일": "금",
                                "걸음 수": 0,
                              },
                              {
                                "요일": "토",
                                "걸음 수": 0,
                              },
                              {
                                "요일": "일",
                                "걸음 수": 0,
                              }
                        ]}
                        keys={[
                            '걸음 수',
                        ]}
                        indexBy="요일"
                        margin={{ top: 40, right: 5, bottom: 30, left: 60 }}
                        padding={0.4}
                        valueScale={{ type: 'linear' }}
                        indexScale={{ type: 'band', round: true }}
                        colors={['#5F5745']} // 커스텀해서 사용할 때
                        defs={[
                            {
                                id: 'dots',
                                type: 'patternDots',
                                background: 'inherit',
                                color: '#38bcb2',
                                fontFamily: 'bc_b',
                                size: 4,
                                padding: 1,
                                stagger: true
                            },
                            {
                                id: 'lines',
                                type: 'patternLines',
                                background: 'inherit',
                                color: '#eed312',
                                rotation: -45,
                                lineWidth: 6,
                                spacing: 10
                            }
                        ]}
                        borderRadius={6}
                        borderColor={{
                            from: 'color',
                            modifiers: [
                                [
                                    'darker',
                                    1.6
                                ]
                            ]
                        }}
                        axisLeft={{
                            tickValues: [0, 6000, 10000], // 0만 포함하도록 설정
                            format: value => value.toLocaleString(),
                            text: {
                                fontSize: 10,
                                fontFamily: "bc_b",
                                fill: '#000000',
                            },
                            tickLineSize: (value) => (value === 0 ? 5 : 3), // 0에 해당하는 기준선만 크기를 더 크게 설정
                        }}
                        theme={{
                            /**
                             * label style (pad에 표현되는 글씨)
                             */
                            labels: {
                                text: {
                                    fontSize: 10,
                                    fontFamily: "bc_b",
                                    fill: '#000000',
                                },
                            },
                            /**
                             * legend style (default로 하단에 있는 색상별 key 표시)
                             */
                            legends: {
                                text: {
                                    fontSize: 12,
                                    fontFamily: "bc_b",
                                    fill: '#000000',
                                },
                            },

                            tooltip: {
                                container: {
                                    background: '#FFF3CE',
                                    color: '#00000',
                                    fontFamily: 'bc_b', // 원하는 글꼴로 변경
                                    fontSize: '1rem', // 원하는 크기로 변경
                                    borderRadius: '1.2rem',
                                    boxShadow: '0 2px 4px rgba(0, 0, 0, 0.15)',
                                },
                                basic: {
                                    fontFamily: 'bc_b', // 원하는 글꼴로 변경
                                    fontSize: '1rem', // 원하는 크기로 변경
                                    lineHeight: '1.4',
                                    textAlign: 'center',
                                },
                            },
                            axis: {
                                legend: {
                                    text: {
                                        fontSize: 8,
                                        fontFamily: "bc_b",
                                        fill: '#000000',
                                    },
                                },
                                ticks: {
                                    text: {
                                        fontSize: 14,
                                        fontFamily: "bc_b",
                                        fill: '#000000',
                                    },
                                },
                                line: {
                                    stroke: '#000000',
                                    strokeWidth: 2,
                                },
                            },

                            grid: {
                                line: {
                                    stroke: '#000000',
                                    strokeWidth: 0.5,
                                    strokeDasharray: '4 4' // 점선 스타일
                                }
                            }
                        }}
                        axisTop={null}
                        axisRight={null}
                        enableLabel={false}
                        enableTotals={true}

                        totalsOffset={14}
                        labelSkipWidth={12}
                        labelSkipHeight={12}

                        labelTextColor={{
                            from: 'color',
                            modifiers: [
                                [
                                    'darker',
                                    1.6
                                ]
                            ]
                        }}
                        role="application"
                        ariaLabel="Nivo bar chart demo"
                        barAriaLabel={e=>e.id+": "+e.formattedValue+" in country: "+e.indexValue}
                    />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </>
    );
}

export default Main;