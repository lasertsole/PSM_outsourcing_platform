<template>
    <form class="register">
        <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="手机号" v-model="registerAccountData" clearable/>
        <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="密码" v-model="registerPasswordData" type="password" clearable/>
        <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="确认密码" v-model="confirmRegisterPasswordData" type="password" clearable/>
        <div class="contract">请确认您已同意 <router-link to="/contract">《喵剪辑服务协议》</router-link></div>
        <el-button type="primary" @click="modifyUserPassword()">注册</el-button>
        <router-link to="login" class="backLogin">已有账号,去登录</router-link>
    </form>
</template>

<script lang="ts" setup>

import useGlobal from "global";
    import { ref } from "vue";
    import { ElMessage } from "element-plus";

    /**********获取全局变量*********/
    const global = useGlobal();
    const accountInfo = global?.accountInfo;

    /**********用户注册*********/
    const registerAccountData = ref<string>("");//注册账号
    const registerPasswordData = ref<string>("")//注册密码
    const confirmRegisterPasswordData = ref<string>("")//确认密码

    async function modifyUserPassword():Promise<void>{
        if(registerPasswordData.value.length<6){
            ElMessage.error('密码过短');
        }
        else if(registerPasswordData.value.length>=15){
            ElMessage.error('密码过长');
        }
        else if(registerPasswordData.value != confirmRegisterPasswordData.value){
            ElMessage.error('密码和确认密码不一致');
        }
        else{
            accountInfo.registerAccount(registerAccountData, registerPasswordData);
        }
    }
</script>

<style lang="scss" scoped>
    .register{
        width: 100%;

        .contract{
            font-size: 14px;
            margin-bottom: 10px;
            a{
                color: #00a8e9;
            }
        }
        .backLogin{
            width: 100%;
            font-size: 14px;
            color: #00a8e9;
            display: flex;
            justify-content: center;
            cursor: pointer;
            >span{
                cursor: pointer;
            }
        }
    }
</style>