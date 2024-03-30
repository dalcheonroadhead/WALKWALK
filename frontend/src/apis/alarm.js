import { instance } from "./axiosModule";

// 내 운동기준 정보 조회
// export const getAlarmList = async () => {
//     const url = `/walk/criteria`;
    
//     return await instance.get(url)
//         .then((res) => {
//             console.log(res.data.data);
//             return res.data.data;
//         })
//         .catch((err) => {console.log(err)})
// }

export const getAlarmList = async () => {
    const url = `/notifications/`;

    return await instance.get(url)
        .then(res=>{
            console.log(res)
        })
}