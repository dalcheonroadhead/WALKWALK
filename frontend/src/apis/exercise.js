import { instance } from "./axiosModule";
import axios from "axios";
import { updateGoogleToken } from "./member";

export const getRealtimeExerciseData = async () => {
    const url = 'https://www.googleapis.com/fitness/v1/users/me/dataset:aggregate';

    // 현재 날짜와 시간 객체 생성
    var currentDate = new Date();
    // 현재 날짜의 0시 0분 0초로 설정
    currentDate.setHours(0, 0, 0, 0);
    
    // 현재 날짜의 밀리초 구하기
    var millisecondsSinceMidnight = currentDate.getTime();
    // 현재 시간의 밀리초 구하기
    var currentMilliseconds = (new Date()).getTime();

    const requestBody = {
        "aggregateBy": [{
        "dataSourceId": "derived:com.google.step_count.delta:com.google.android.gms:merge_step_deltas"
        },
        {
        "dataSourceId":  "derived:com.google.active_minutes:com.google.android.gms:merge_active_minutes"
        }
        ],
        "bucketByTime": { "durationMillis": currentMilliseconds - millisecondsSinceMidnight },
        "startTimeMillis": millisecondsSinceMidnight,
        "endTimeMillis": currentMilliseconds
    };
    let data = {
        'steps': 0,
        'time': 0
    };
    await axios.post(url, requestBody, {headers: {Authorization: 'Bearer ' + JSON.parse(localStorage.getItem('tokens')).Google_access_token} })
        .then((res) => {
            data = {
                'steps': res.data.bucket[0].dataset[0].point[0].value[0].intVal,
                'time': res.data.bucket[0].dataset[1].point[0].value[0].intVal
            };
            
            return data;
        })
        .catch((err)=>{
            updateGoogleToken();
            axios.post(url, requestBody, {headers: {Authorization: 'Bearer ' + JSON.parse(localStorage.getItem('tokens')).Google_access_token} })
            .then((res) => {
                data = {
                    'steps': res.data.bucket[0].dataset[0].point[0].value[0].intVal,
                    'time': res.data.bucket[0].dataset[1].point[0].value[0].intVal
                };
                
                return data;
            })})
    return data;
}
