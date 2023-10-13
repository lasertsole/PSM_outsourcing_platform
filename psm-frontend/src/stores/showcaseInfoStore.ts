import axios from "axios"
import { grapQL } from "@/graphQL"
import { defineStore } from "pinia";
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
            let result = await grapQL({
                query: `query {
                    getShowcaseBoxes(
                        showcaseBoxParams:{
                          primarySort:"${infoArr[0]}"
                          lastSort:"${infoArr[1]}"
                          sortWay: "${infoArr[2]}"
                          isIdle:${infoArr[3]}
                          canQuicky:${infoArr[4]}
                        }
                      )
                      {
                        ID
                        userName
                        profile
                        honor
                        authorBrief
                        authorID
                        commentNum
                        primarySort
                        lastSort
                        isIdle
                        canQuicky
                        works
                      }
                    }`
            });

            let data = result.data;
            if(!data.errors){
                data = data.data.getShowcaseBoxes;
                return data;
            }
            return null;
        },

        getShowcaseBoxDetail: async function(ID:string): Promise<ShowcaseBoxesInfo[]|null>{
            let result = await grapQL({
                query: `query {
                            getShowcaseBoxDetail(
                                ID:"${ID}"
                            ){
                                ID
                                authorID
                                price
                                imgPath
                                videoPath
                                    abstractInfo
                                modifyTime
                                mainInfo
                                profile
                                userName
                                commentNum
                                primarySort
                                lastSort
                                isIdle
                                canQuicky
                            }
                        }`
            });

            let data = result.data;
            if(!data.errors){
                data = data.data.getShowcaseBoxDetail;
                return data;
            }
            return null;
        }
    }
})