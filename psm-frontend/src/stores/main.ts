import axios from"axios"
import { defineStore } from "pinia";
import { ElMessage } from "element-plus";

export const useMainStore = defineStore({//持久化存储用户信息
    id: 'mainInfo', 
    state: () => ({
        token:undefined,
        info: undefined,
    }),
    //持久化选项
    persist:{ 
        storage: window.localStorage,
    },
    getters: {
    },
    actions:{
        removeAccount:function():void{//移除账号信息
            this.token=undefined;
            this.info=undefined;
        },
        setAccount:function(token:any,info:any):void{//设置账号信息
            this.token=token;
            this.info=info;
        },
    }
})