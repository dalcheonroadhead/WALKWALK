import { useState } from "react";
import styles from "./Main.module.css";
import { ResponsiveBar } from "@nivo/bar";

const Main = function(){
    const handle = {
        barClick: (data) => {
            console.log(data);
        },

        legendClick: (data) => {
            console.log(data);
        },
    };

    const [tabIndex, settabIndex] = useState(0);
    const [halli, sethalli] = useState(true);
    const [isHalliOpen, setIsHalliOpen] = useState(false);
    const [isGalliOpen, setIsGalliOpen] = useState(false);

    const openHalliModal = function() {
        setIsHalliOpen(!isHalliOpen);
    }

    const openGalliModal = function(){
        setIsGalliOpen(!isGalliOpen);
    }

    const tabClickHandler = function(index){
        settabIndex(index)
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
            nickname: "김모리",
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
                                <div className={styles.my_time_progress_move} style={{width: 200}}></div>
                                <div className={styles.my_time_ori_container} style={{width: 200}}>
                                    <p className={styles.my_time_mine} style={{left: 135}}>41분</p>
                                    <img src="/imgs/ch1_bol_samewalk.gif" alt="제자리걸음 오리" className={styles.ch1} style={{left: 160}}></img>
                                </div>
                                <div className={styles.my_time_number_base}>
                                    <p className={styles.my_time_min}>0분</p>
                                    <p className={styles.my_time_min2}>60분</p>
                                </div>

                            </div>
                            <div>

                            </div>
                        </div>
                    </div>

                    <div className={styles.my_walk_foot}>
                        <p className={styles.foot_txt}>오늘 내가 걸은 걸음수</p>
                        <div className={styles.my_walk_progress_container}>
                            <div className={styles.my_walk_progress_base}>
                                <div className={styles.my_walk_progress_move} style={{width: 260}}></div>
                                <div className={styles.my_walk_ori_container} style={{width: 260}}>
                                    <p className={styles.my_walk_mine} style={{left: 170}}>5003보</p>
                                    <img src="/imgs/ch1_bol_samewalk.gif" alt="제자리걸음 오리" className={styles.ch1_2} style={{left: 220}}></img>
                                </div>
                                <div className={styles.my_walk_number_base}>
                                    <p className={styles.my_walk_min}>0보</p>
                                    <p className={styles.my_walk_min2}>6000보</p>
                                </div>

                            </div>
                            <div>

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
                            <div>
                                
                            </div>
                        </div>
                    </div>
                ) : (
                    <div className={styles.no_halli_content}>
                        <div className={styles.no_halli_container}>
                            <p className={styles.no_halli_detail}> 등록된 <br></br> 나의 할리가 <br></br> 없습니다.</p> 
                            <img src="/imgs/ch2_bol_q.png" alt="오리무중 오리" className={styles.no_halli_img}></img>
                        </div>
                        <div className={styles.no_halli_btn}>
                            <p className={styles.no_halli_btn_txt} onClick={openHalliModal}>요청 목록 보러가기</p>
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
            <div className={styles.no_galli_content}>
                <div className={styles.no_galli_container}>
                    <p className={styles.no_galli_detail}> 등록된 <br></br> 나의 갈리가 <br></br> 없습니다.</p> 
                    <img src="/imgs/ch1_bol_q.png" alt="오리무중 오리" className={styles.no_halli_img}></img>
                </div>
                <div className={styles.no_galli_btn}>
                    <p className={styles.no_galli_btn_txt} onClick={openGalliModal}>나의 갈리 등록하기</p>
                </div>
            </div>
        )
    }
        
    ]
    return(
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
                            <div className={styles.halli_names_container}>
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
                            <input className={styles.galli_search_box}></input>
                            <img className={styles.galli_search_icon} src="/imgs/search.png" alt="찾기 아이콘"></img>
                        </div>

                        <div className={styles.galli_list_container}>
                            <div className={styles.galli_names_container}>

                                {namelist.map((data, index) => {
                                    return(
                                        <>
                                            <div key={index} className={styles.galli_name_container}>
                                                <img src={data.pimg} alt="프로필 사진" className={styles.galli_img_container} ></img>
                                                <p className={styles.galli_name_txt}>{data.nickname}</p>
                                                <div className={styles.galli_btn_container}>
                                                    <div className={styles.galli_put_btn}>
                                                        <p className={styles.btn_txt}>신청</p>
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
    );
}

export default Main;