import { instance } from "./axiosModule";

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

export const postGalleyRequest = async (request) => {
    const url = '/halleygalley/galley-request';

    return await instance.post(url, request)
        .then((res) => {
            alert(res.data.msg);
        })
        .catch(err => console.log(err))
}

export const getHalley = async (memberId) => {
    const url = `/halleygalley/halley?memberId=${memberId}`;
    
    return await instance.get(url)
        .then((res) => {
            console.log(res)
            return res.data.data;
        })
        .catch((err) => {console.log(err)})
}
