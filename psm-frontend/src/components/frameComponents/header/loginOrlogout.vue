<template>
    <div class="loginOrRegister">
        <router-link to="loginAndRegister" class="login">登录</router-link>
        <router-link to="loginAndRegister" class="register">注册</router-link>
    </div>
</template>

<script lang="ts" setup>

</script>

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