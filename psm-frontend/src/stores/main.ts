import axios from "axios"
import { defineStore } from "pinia";
import { ElMessage } from "element-plus";
import { useMainStoreObjType, useMainStoreObjInfoType } from "@/types/stores/useMainStoreType"

/**********main传入全局变量**********/
let global:any=undefined;
export function initGlobal(passGlobal:any):void{
    global=passGlobal;
}

/**********持久化存储用户信息**********/
let obj:useMainStoreObjType={
    token:undefined,
    userinfo: {userName:undefined, userProfile:undefined},
};

export const useMainStore = defineStore({
    id: 'mainInfo', 
    state: () => (obj),
    //持久化选项
    persist:{ 
        storage: window.localStorage,
    },
    getters: {
    },
    actions:{
        removeAccount:function():void{//移除账号信息
            this.token=undefined;
            this.userinfo={userName:undefined, userProfile:undefined};
        },
        setAccount:function(token:any,userinfo:useMainStoreObjInfoType):void{//设置账号信息
            this.token=token;
            if(userinfo.userName==""){userinfo.userName=undefined;}
            if(userinfo.userProfile==""){userinfo.userProfile=undefined;}
            this.userinfo=userinfo;
        },
        fasterLogin: async function() {//自动登录
            let result = await axios.get("api/user/fasterLogin");
            let data = result.data;
            if(data.status==1){
                ElMessage.success(data.msg);
                this.setAccount(this.token, {userName:data.userName,userProfile:data.userProfile});
                global.Bus.emit("login","");//广播用户上线通知
            }
            else{
                ElMessage.error(data.msg);
            }
        },
        loginAccount: async function(phoneNumber:string,password:string):Promise<void>{//登录账号
            if(phoneNumber==""){
                ElMessage.error('手机号不能为空');
            }
            else if(!/^1[0-9]{10}$/.test(phoneNumber)){
                ElMessage.error('无效手机号');
            }
            else if(password==""){
                ElMessage.error('密码为空');
            }
            else if(password.length<6){
                ElMessage.error('密码过短');
            }
            else if(password.length>=15){
                ElMessage.error('密码过长');
            }
            else{
                let result = await axios.post("api/user/login",{phoneNumber, password});
                let data = result.data;
                if(data.status==1){
                    ElMessage.success(data.msg);
                    this.setAccount(data.token, {userName:data.userName,userProfile:data.userProfile});
                    global.Bus.emit("login","");//广播用户上线通知
                }
                else{
                    ElMessage.error(data.msg);
                }
            }
        },
        registerAccount: async function(phoneNumber:string,password:string):Promise<void>{//注册账号
            if(phoneNumber==""){
                ElMessage.error('手机号不能为空');
            }
            else if(!/^1[0-9]{10}$/.test(phoneNumber)){
                ElMessage.error('无效手机号');
            }
            else if(password==""){
                ElMessage.error('密码为空');
            }
            else if(password.length<6){
                ElMessage.error('密码过短');
            }
            else if(password.length>=15){
                ElMessage.error('密码过长');
            }
            else{
                let result = await axios.post("api/user/register",{phoneNumber,password});
                let data = result.data;
                console.log(data);
                if(data.status==1){
                    ElMessage.success(data.msg);
                    this.setAccount(data.token, {userName:data.userName,userProfile:data.userProfile});
                    this.fasterLogin();//注册完成后自动登录
                }
                else{
                    ElMessage.error(data.msg);
                }
            }
        },
        accountSetName: async function(userName:string):Promise<void>{//用户设置名字
            let result = await axios.post("api/user/setName",{userName});
            if(result.data.status==0){
                ElMessage.error("修改失败");
            }
            else{
                ElMessage.success("修改成功");
            }
        },
        accountSetProfile: async function(file:any):Promise<void>{//用户设置头像
            const formData = new FormData();//第一次请求
            formData.append("file", file);
            formData.append("format", "jpg");
            let result = await axios({
                method: 'post',
                url: "api/user/setProfile",
                headers: {
                    "Content-Type": "multipart/form-data; boundary=----WebKitFormBoundaryVCFSAonTuDbVCoAN",
                },
                data: formData,
            });
            if(result.data.status==0){
                ElMessage.error("上传失败");
            }
            else{
                ElMessage.success("上传成功");
            }
        }
    }
})

/**********用户登录注册账号**********/
export const useLoginAndRegisterStore = defineStore({
    id: 'LoginAndRegisterFloat', 
    state: () => ({
        isShowFloat:false,
    }),
    actions:{
        showFloat:function():void{//移除账号信息
            this.isShowFloat=true;
        },
        hideFloat:function():void{//移除账号信息
            this.isShowFloat=false;
        }
    }
})