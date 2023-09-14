<template>
    <div class="main">
        <HeaderComponent></HeaderComponent>
        <div class="wrapper">
            <div class="content">
                <router-view v-slot="{ Component }">
                    <keep-alive>
                        <component :is="Component"/>
                    </keep-alive>
                </router-view>
            </div>
        </div>
        <LARModel></LARModel>
    </div>
</template>

<script setup lang="ts">
    import axios from "axios";
    import useGlobal from "global"
    import { storeToRefs } from "pinia";
    import { ElMessage } from "element-plus";
    import LARModel from "components/framework/LARModel.vue";
    import HeaderComponent from "components/framework/HeaderComponent.vue";

    /**********获取全局变量*********/
    const global = useGlobal();
    const Bus = global?.Bus;//获取事件总线
    const mainStore = global?.UserInfo;//用户状态信息
    const { token, userinfo } = storeToRefs(mainStore);

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
            flex-direction: column;
            flex-grow: 1;
            overflow: auto;
            position: relative;
            z-index: 0;
            .content{
                flex-grow: 1;
                overflow: hidden;
                display: flex;
                overflow-y: auto;
                flex-direction: row;
                justify-content: center;
            }
        }
    }
</style>
