import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import styles from "./MyGoalUpdate.module.css";
import { getExerciseCriteria, getExerciseCriteriaByAge, updateExerciseCriteria } from "../../apis/exercise";

const MyGoalUpdate = function () {
  const navigate = useNavigate();
  const [myCriteria, setMyCriteria] = useState({
    steps: 0,
    exerciseMinute: 0,
    heartRate: 0
  });
  const [myCriteriaByAge, setMyCriteriaByAge] = useState();

  useEffect(() => {
    (async () => {
      try {
        const res1 = await getExerciseCriteria();
        setMyCriteria(res1);
        const res2 = await getExerciseCriteriaByAge();
        setMyCriteriaByAge(res2);
      } catch (error) {
        console.error('criteria 정보를 가져오는 중 에러 발생:', error);
      }
    })();
  }, []);

  const handleChange = (e) => {
    const {name, value} = e.target;
    setMyCriteria((prevInfo) => ({
      ...prevInfo,
      [name]: value === '' ? 0 : Number(value),
    }))
  }

  const handleSubmit = () => {
    // myCriteria의 값이 0이거나 유효하지 않은 경우 myCriteriaByAge 값으로 대체
    const criteria = Object.keys(myCriteria).reduce((acc, key) => {
      // 0 또는 유효하지 않은 값 검사
      if (!myCriteria[key] || myCriteria[key] === 0) {
        acc[key] = myCriteriaByAge[key];
      } else {
        acc[key] = myCriteria[key];
      }
      return acc;
    }, {});
  }
  console.log(myCriteria)

  
  
  return (
    <div className={styles.mygoalupdate_container}>
      <>
        <div className={styles.mygoalupdate_top_bar}>
          <img className={styles.mygoalupdate_back_direct} src="/imgs/direct.png" alt="back_direct" onClick={() => {navigate('/mypage')}}/>
          <span className={styles.mygoalupdate_title}>미션 수정</span>
        </div>

        <div className={styles.mygoalupdate_sub_container}>
          {myCriteriaByAge && (
            <div>
              <img src="/imgs/info.png" alt="info_img" />
              <div>나의 권장량</div>
              <div>걸음수 : {myCriteriaByAge.steps} 보</div>
              <div>운동시간 : {myCriteriaByAge.exerciseMinute} 분</div>
              <div>심박수 : {myCriteriaByAge.heartRate} bpm</div>
            </div>
          )}
          {myCriteria && (
            <>
              <div>
                <div>목표 걸음수</div>
                <input type="number" placeholder='걸음수를 입력해주세요' name="steps" value={myCriteria.steps} onChange={handleChange}/>
              </div>
              <div>
                <div>목표 운동시간</div>
                <input type="number" placeholder='운동시간을 입력해주세요' name="exerciseMinute" value={myCriteria.exerciseMinute} onChange={handleChange}/>
              </div>
              <div>
                <div>목표 심박수</div>
                <input type="number" placeholder='심박수를 입력해주세요' name="heartRate" value={myCriteria.exerciseMinute} onChange={handleChange}/>
              </div>
              <button onClick={handleSubmit}>수 정</button>
            </>
          )}
        </div>
      </>
    </div>
  )
}

export default MyGoalUpdate;