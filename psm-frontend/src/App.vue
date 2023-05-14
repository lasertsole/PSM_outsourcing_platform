<template>
    <div class="main">
        <HeaderComponent></HeaderComponent>
        <div class="wrapper">
            <router-view v-slot="{ Component }">
                <keep-alive>
                    <component :is="Component" />
                </keep-alive>
            </router-view>
            <session></session>
        </div>
        <LARModel></LARModel>
    </div>
</template>

<script setup lang="ts">
    import axios from "axios";
    import useGlobal from "@/global"
    import { storeToRefs } from "pinia";
    import { ElMessage } from "element-plus";
    import session from "@/components/session.vue";
    import LARModel from "@/components/LARModel.vue";
    import HeaderComponent from "@/components/HeaderComponent.vue";

    /**********获取全局变量*********/
    const global = useGlobal();
    const mainStore = global?.UserInfo;
    const { token,userinfo } = storeToRefs(mainStore);

    /**********自动登录*********/
    if(token.value){//如果存在token则自动登录
        mainStore.fasterLogin(token.value);
    };
    async function fasterLogin(){//自动登录
        let result = await axios.get("api/user/fasterLogin");
        let data = result.data;
        if(data.status==1){
            ElMessage.success(data.msg);
            token.value = result.data.token;
            userinfo.value = result.data.userinfo;
        }
        else{
            ElMessage.error(data.msg);
        }
    }
</script>

<style lang="scss" scoped>
    .main{
        overflow: hidden;
        position: relative;
        z-index: 1;
        display: flex;
        flex-direction: column;
        width: 100%;
        height: 100%;
        box-sizing: border-box;
        .wrapper{
            width: 100%;
            height: 100%;
            display: flex;
            flex-direction: row;
            flex-grow: 1;
            overflow: auto;
            position: relative;
            z-index: 0;
        }
    }
</style>
