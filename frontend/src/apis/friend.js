import { instance } from "./axiosModule";

export const searchGalleyMemberList = async (keyword) => {
    const url = '/friends/search-galley'
    return await instance.post(url, {"keyword": keyword})
        .then((res) => {
            let data = [];
            return res.data.data;
        })
        .catch((err) => {console.log(err)})
}