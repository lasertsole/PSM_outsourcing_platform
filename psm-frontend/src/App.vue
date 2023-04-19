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
    import useGlobal from "@/global"
    import { storeToRefs } from "pinia";
    import session from "@/components/session.vue";
    import LARModel from "@/components/LARModel.vue";
    import HeaderComponent from "@/components/HeaderComponent.vue";

     /**********获取全局变量*********/
    const global = useGlobal();
    const mainStore = global?.UserInfo;
    const { token } = storeToRefs(mainStore);

    if(token.value){//如果存在token则自动登录
        mainStore.fasterLogin();
    };
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
        }
    }
</style>
