import axios from "axios";

export async function grapQL(paramsObj:any):Promise<any>{
    return axios.post("api/graphql",paramsObj);
}