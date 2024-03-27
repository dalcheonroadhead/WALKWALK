import { instance } from "./axiosModule";

export const getGalleyList = async () => {
    const url = '/halleygalley/halley-to-galley';
    
    return await instance.get(url)
        .then((res) => {
            console.log('test1', res.data)
            console.log('type : ', typeof(res.data.data)) // object
            if(res.data.data.length != 0){
                return res.data.data;
            }
            return false;
        })
        .catch((err) => {console.log(err)})
}
