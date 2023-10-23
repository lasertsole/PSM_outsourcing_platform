import gql from 'graphql-tag'
import { defineStore } from "pinia";
import { ElMessage } from "element-plus";
import { AccountInfo } from "@/types/stores/AccountInfo";

/**********accountInfoStore传入全局变量**********/
let global:any=undefined;
let apolloClient:any;
export function initAccountInfo(passGlobal:any):void{
    global=passGlobal;
    apolloClient=global?.apolloClient;
}

/**********持久化存储用户信息**********/
let infoObj:AccountInfo={
    ID: undefined,
    token: undefined,
    userName: undefined,
    userPhoneNumber: undefined,
    userProfile: undefined,
    isOnline: false,
};

export const accountInfoStore = defineStore({
    id: 'accountInfo', 
    state: () => (infoObj),
    //持久化选项
    persist:{ 
        enabled: true,
        strategies:[//数据保存策略，若未设置则保存到内存
            {
                key: 'token',//key可省略，省略则为变量同名key值
                storage: localStorage,
                paths: ['token'] 
            }
        ]
    },
    getters: {
    },
    actions:{
        removeAccount:function():void{//移除账号信息
            this.token = undefined;
            this.userPhoneNumber = undefined;
            this.userName = undefined;
            this.userProfile = undefined;
            this.isOnline = false;
        },

        setAccount:function( userInfo:AccountInfo ):void{//设置账号信息
            this.ID = userInfo.ID;
            this.token = userInfo.token;
            this.userName = userInfo.userName;
            if(this.userName==""){this.userName=undefined;}
            if(this.userProfile==""){this.userProfile=undefined;}
            this.userPhoneNumber = userInfo.userPhoneNumber;
            this.userProfile = userInfo.userProfile;
            this.isOnline = true;
        },

        InfoChange:function( userInfo:AccountInfo ):void{//信息修改
            if(userInfo.token){
                this.token = userInfo.token;
            }
            if(userInfo.userPhoneNumber){
                this.userPhoneNumber = userInfo.userPhoneNumber;
            }
            if(userInfo.userName){
                this.userName = userInfo.userName;
            }
            if(userInfo.userProfile){
                this.userProfile = userInfo.userProfile;
            }
            if(userInfo.isOnline){
                this.isOnline = userInfo.isOnline;
            }
        },

        fasterLogin: async function():Promise<void>{//自动登录
            let result = await apolloClient.query({
                query: gql`query {
                    fasterLogin{
                        ID
                        status
                        phoneNumber
                        userName
                        profile
                        token
                    }
                }`
            });
            let data = result.data;
            if(!data.errors){
                data = data.fasterLogin;
                this.setAccount({ID:data.ID, token:data.token, userPhoneNumber:data.phoneNumber, userName: data.userName,userProfile: data.profile, isOnline: true});
                global.Bus.emit("login","");//广播用户上线通知
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
                let result = await apolloClient.query({
                    query: gql`query {
                        login(
                            phoneNumber:"${phoneNumber}"
                            password:"${password}"
                        ){
                            ID
                            status
                            phoneNumber
                            userName
                            profile
                            token
                        }
                    }`
                });

                let data = result.data;
                if(!data.errors){
                    ElMessage.success("登录成功");
                    data = data.login;
                    this.setAccount({ID:data.ID, token:data.token, userPhoneNumber:data.phoneNumber, userName: data.userName,userProfile: data.profile, isOnline: true});
                    global.Bus.emit("login");//广播用户上线通知
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
                let result = await apolloClient.mutate({
                    mutation: gql`mutation {
                        register(
                            phoneNumber:"${phoneNumber}"
                            password:"${password}"
                        ){
                            ID
                            status
                            phoneNumber
                            userName
                            profile
                            token
                        }
                    }`
                });

                let data = result.data;
                if(!data.errors){
                    ElMessage.success("注册成功");
                    data=data.register;
                    this.setAccount({ID:data.ID, token:data.token, userPhoneNumber:data.phoneNumber, userName: data.userName,userProfile: data.profile, isOnline: true});
                    global.Bus.emit("login");//广播用户上线通知
                }
            }
        },

        logOutAccount: async function() {
            this.removeAccount();
            global.Bus.emit("logout");//广播用户下线通知
        },

        changeUserName: async function(userName:string):Promise<boolean>{//用户设置名字
            let result = await apolloClient.mutate({
                mutation: gql`mutation {
                    changeUserName(userName:"${userName}")
                }`
            });
            
            let data = result.data;
            if(!data.errors){
                ElMessage.success("修改名字成功");
                this.InfoChange({userName});
                return true;
            }
            else{
                return false;
            }
        },

        changeUserPhoneNumber: async function(userPhoneNumber:string):Promise<boolean>{//用户设置手机号
            let result = await apolloClient.mutate({
                mutation: gql`mutation {
                    changeUserPhoneNumber(userPhoneNumber:"${userPhoneNumber}")
                }`
            });

            let data = result.data;
            if(!data.errors){
                ElMessage.success("修改手机号码成功");
                this.InfoChange({userPhoneNumber});
                return true;
            }
            else{
                return false;
            }
        },

        changeUserPassword: async function(userPassword:string):Promise<boolean> {
            let result = await apolloClient.mutate({
                mutation: gql`mutation {
                    changeUserPassword(userPassword:"${userPassword}")
                }`
            });

            let data = result.data;
            if(!data.errors){
                ElMessage.success("修改密码成功");
                return true;
            }
            else{
                return false;
            }
        },

        changeUserProfile: async function(params:any):Promise<void>{//用户设置头像
            // let result = await apolloClient.mutate({
            //     mutation: gql`mutation ($img:Upload!) {
            //         changeUserProfile(userProfile:$img)
            //     }`,
            //     variables:{img:formData.get("file")},
            //     context: {
            //         hasUpload: true, // Important!
            //     },
            // });
        },

    }
})