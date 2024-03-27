import { instance } from "./axiosModule";

export const searchGalleyMemberList = async (keyword) => {
    const url = '/friends/search-galley'
    console.log('api keyword : ', keyword)
    console.log(instance.defaults.headers.common['Authorization'])

    return await instance.post(url, {"keyword": keyword})
        .then((res) => {
            console.log(res)
            return res.data.data;
        })
        .catch((err) => {console.log(err)})
}