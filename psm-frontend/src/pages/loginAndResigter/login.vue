<template>
    <form class="login">
        <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="手机号" v-model="loginAccountData" clearable/>
        <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="密码" v-model="loginPasswordData" clearable/>
        <el-button type="primary" @click="accountInfo.loginAccount(loginAccountData, loginPasswordData)">登录</el-button>
        <div class="select">
            <router-link to="register">注册新账号</router-link>
            <span>忘记密码</span>
        </div>
    </form>
</template>

<script lang="ts" setup>

    import useGlobal from "global";
    import { ref } from "vue";

    /**********获取全局变量*********/
    const global = useGlobal();
    const accountInfo = global?.accountInfo;

    /**********用户登录*********/
    const loginAccountData = ref<string>("");//注册账号
    const loginPasswordData = ref<string>("")//注册密码

</script>

<style lang="scss" scoped>
    .login{
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
        .select{
            width: 100%;
            font-size: 14px;
            color: #00a8e9;
            display: flex;
            justify-content: space-between;
            >span{
                cursor: pointer;
            }
        }
    }
</style>