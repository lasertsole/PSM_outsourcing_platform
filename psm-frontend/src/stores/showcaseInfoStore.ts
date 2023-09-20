import axios from "axios"
import { defineStore } from "pinia";
import { ElMessage } from "element-plus";
import { ShowcaseBoxInfo } from "@/types/stores/ShowcaseInfo"

/**********showcaseInfoStore传入全局变量**********/
let global:any=undefined;
export function initShowcaseInfo(passGlobal:any):void{
    global=passGlobal;
}

/**********初始化存储用户自己的橱窗盒子信息**********/
let infoObj:ShowcaseBoxInfo={
    authorName: undefined,
    honor: undefined,
    commentNum: undefined,
    brief: undefined,
    works: undefined,
};

export const showcaseInfoStore = defineStore({
    id: 'showcaseInfo', 
    state: () => (infoObj),//存储用户自己的橱窗盒子信息
    //持久化选项
    
    getters: {
        
    },

    actions:{
        getShowcaseBoxes: async function(infoArr:any): Promise<void>{
            let result = await axios.get("api/showcase/getShowcaseBoxes", { params: { primarySort:infoArr[0], lastSort:infoArr[1], sortWay:infoArr[2], isIdle:infoArr[3], canQuicky:infoArr[4] } });
            let data = result.data;
        }
    }
})