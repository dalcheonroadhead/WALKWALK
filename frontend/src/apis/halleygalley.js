import { instance } from "./axiosModule";

// 할리 목록 조회
export const getHalleyList = async () => {
    const url = '/halleygalley/galley-to-halley';
    return await instance.get(url)
        .then((res) => {
            if(res.data.data.requestContent.length != 0){
                return res.data.data.requestContent;
            }
            return false;
        })
        .catch((err) => {console.log(err)})
        
}

// 갈리 목록 조회
export const getGalleyList = async () => {
    const url = '/halleygalley/halley-to-galley';
    
    return await instance.get(url)
        .then((res) => {
            if(res.data.data.length != 0){
                let data = [];
                res.data.data.forEach(element => {
                    if(element.isAccepted){
                        data.push(element);
                    }
                });
                if(data.length != 0){
                    return data;
                }
            }
            return false;
        })
        .catch((err) => {console.log(err)})
}

// 갈리 요청을 보낼 목록 조회
export const getHalleyReauestList = async () => {
    const url = '/halleygalley/halley-request-list';
    
    return await instance.get(url)
        .then((res) => {
            if(res.data.data.length != 0){
                return res.data.data;
            }
            return false;
        })
        .catch((err) => {console.log(err)})
}

// 갈리 요청 전송
export const postGalleyRequest = async (request) => {
    const url = '/halleygalley/galley-request';

    return await instance.post(url, request)
        .then((res) => {
            alert(res.data.msg);
        })
        .catch(err => console.log(err))
}

// 할리 세부정보 조회
export const getHalley = async (memberId) => {
    const url = `/halleygalley/halley?memberId=${memberId}`;
    
    return await instance.get(url)
        .then((res) => {
            console.log(res)
            return res.data.data;
        })
        .catch((err) => {console.log(err)})
}
