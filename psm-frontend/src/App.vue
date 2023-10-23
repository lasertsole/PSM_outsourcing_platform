<template>
    <div class="main">
        <HeaderComponent></HeaderComponent>
        <div class="wrapper">
            <div class="content">
                <router-view v-slot="{ Component }">
                    <keep-alive exclude="showcaseDetail">
                        <component :is="Component"/>
                    </keep-alive>
                </router-view>
            </div>
        </div>
        <LARModel></LARModel>
    </div>
</template>

<script setup lang="ts">
    import useGlobal from "global"
    import { storeToRefs } from "pinia";
    import LARModel from "components/framework/LARModel.vue";
    import { onMounted, onUnmounted } from "vue";
    import HeaderComponent from "components/framework/HeaderComponent.vue";
    import { useRouter, useRoute } from "vue-router";

    /**********获取全局变量*********/
    const global = useGlobal();
    const Bus = global?.Bus;//获取事件总线
    const accountInfo = global?.accountInfo;//用户状态信息
    const { token, userinfo } = storeToRefs(accountInfo);

    /**********自动登录*********/
    if(token.value){//如果存在token则自动登录
        accountInfo.fasterLogin(token.value);
    };

    function initUserInfo():void{//如果没有token值则清空账户信息
        if(token.value==undefined){
            accountInfo.removeAccount();
        }
    }

    /**********挂载事件********/
    onMounted(()=>{
        initUserInfo();
    })

    /**********卸载事件********/
    onUnmounted(()=>{
        
    })

    /**********以下是路由守卫********/
    const router = useRouter();

    Bus.on("login", ()=>{//用户上线时跳转到个人信息页
        router.replace('/personInfo');
    })

    Bus.on("logout", ()=>{//用户下线时跳转到首页
        router.replace('/index');
    })

    /**控制页面是否被keepalive缓存,由路由表中的meta控制**/
    const route = useRoute();
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
