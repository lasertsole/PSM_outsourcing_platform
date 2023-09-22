import axios from "axios"
import { defineStore } from "pinia";
import { ElMessage } from "element-plus";
import { ShowcaseBoxesInfo } from "@/types/stores/ShowcaseInfo"

/**********showcaseInfoStore传入全局变量**********/
let global:any=undefined;
export function initShowcaseInfo(passGlobal:any):void{
    global=passGlobal;
}

export const showcaseInfoStore = defineStore({
    id: 'showcaseInfo', 
    state: () => ({}),//存储用户自己的橱窗盒子信息
    //持久化选项
    
    getters: {
        
    },

    actions:{
        getShowcaseBoxes: async function(infoArr:any): Promise<ShowcaseBoxesInfo[]|null>{
            let result = await axios.get("api/showcase/getShowcaseBoxes", { params: { primarySort:infoArr[0], lastSort:infoArr[1], sortWay:infoArr[2], isIdle:infoArr[3], canQuicky:infoArr[4] } });
            let data = result.data;
            if(data.code==200){
                data = data?.data;
                return data;
            }
            return null;
        },

        getShowcaseBoxDetail: async function(ID:string): Promise<ShowcaseBoxesInfo[]|null>{
            let result = await axios.get("api/showcase/getShowcaseBoxDetail", { params: { ID } });
            let data = result.data;
            if(data.code==200){
                data = data?.data;
                return data;
            }
            return null;
        }
    }
})