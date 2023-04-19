<template>
    <!-- 登录注册按钮 -->
    <div v-if="!hadToken" class="loginOrRegister">
        <router-link to="/loginAndRegister" class="login">登录</router-link>
        <router-link to="/loginAndRegister" class="register">注册</router-link>
    </div>
    <!-- 用户账号管理 -->
    <div v-else>

    </div>
</template>

<script lang="ts" setup>
    import { computed } from "vue";
    import useGlobal from "@/global";
    import { storeToRefs } from "pinia";

    const global = useGlobal();

    const mainStore = global?.UserInfo;//获取用户账号信息的pinia
    const { token } = storeToRefs(mainStore);

    const hadToken = computed(()=>{return token.value?true:false});//判断localStorage中是否已经有token</script>

<style lang="scss">
    @mixin button{
        width: 80px;
        height: 36px;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        margin: 0px 10px;
        border-radius: 4px;
        color: white;
        cursor: pointer;
    }
    .loginOrRegister{
        display: flex;
        flex-direction: row;
        .login{
            display: block;
            background-color: #fb7299;
            @include button;
        }
        .register{
            display: block;
            background-color: #00a8e9;
            @include button;
        }
    }
    @media screen and (max-width: 600px) {
        .loginOrRegister{
            display: none;
        }
    }
</style>