<template>
    <el-dialog class="LARModel"
        top="140px"
        close-on-press-escape 
        v-model="isShowFloat"
        :show-close="true"
        :destroy-on-close="true"
        :append-to-body="true">
        <!-- 模态框内容 -->
        <div class="modelContent">
            <keep-alive>
                <transition mode="out-in">
                    <div v-if="pageController" class="login">
                        <div class="title">用户登录</div>
                        <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="昵称/邮箱/手机号" v-model="loginAccountData" clearable/>
                        <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="密码" v-model="loginPasswordData" clearable/>
                        <el-button type="primary" @click="mainStore.loginAccount(loginAccountData, loginPasswordData)">登录</el-button>
                        <div class="select">
                            <span @click="changePage">注册新账号</span>
                            <span>忘记密码</span>
                        </div>
                    </div>
                    <div v-else :class="{register:true, show:!pageController}">
                        <div class="title">用户注册</div>
                        <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="昵称" v-model="registerAccountData" clearable/>
                        <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="密码" v-model="registerPasswordData" clearable/>
                        <div class="contract">请确认您已同意 <router-link to="/contract" @click="LARModel.hideFloat()">《喵剪辑服务协议》</router-link></div>
                        <el-button type="primary" @click="mainStore.registerAccount(registerAccountData, registerPasswordData)">注册</el-button>
                        <div class="backLogin" @click="changePage">已有账号,去登录</div>
                    </div>
                </transition>
            </keep-alive>
        </div>
    </el-dialog>
</template>

<script lang="ts" setup>
    import { ref } from "vue";
    import { storeToRefs } from "pinia";
    import useGlobal from "global";

    /**********获取全局变量*********/
    const global = useGlobal();
    const LARModel = global?.LARFloat;
    const { isShowFloat } = storeToRefs(LARModel);//用pinia得知是否开启model

    const mainStore = global?.UserInfo;//获取账号管理的pinia

    /**********切换注册登录页*********/
    const pageController = ref<boolean>(true);
    function changePage():void{
        pageController.value=!pageController.value;
    }

    /**********用户登录*********/
    const loginAccountData = ref<string>("");//注册账号
    const loginPasswordData = ref<string>("")//注册密码

    /**********用户注册*********/
    const registerAccountData = ref<string>("");//注册账号
    const registerPasswordData = ref<string>("")//注册密码

</script>

<style lang="scss" scoped>
    @mixin fixedSquare($size){
        max-width: $size;
        min-width: $size;
        max-height: $size;
        min-height: $size;
        box-sizing: border-box;
    }
    .LARModel{
        display: flex;
        flex-direction: row;
        justify-content: center;
        overflow-y: scroll;
        box-sizing: border-box;
        background-color: #fb7299;
        .modelContent{
            width: 100%;
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 25px;
            padding: 0px 50px 60px;
            .title{
                display: flex;
                flex-direction: row;
                justify-content: center;
                font-size: 30px;
                margin-bottom: 50px;
                color: #8e8e8e;
                font-weight: bolder;
            }
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
        }
    }
    .v-enter-from{
        opacity: 0;
    }
    .v-enter-active{
        transition: opacity .7s ease;
    }
    .v-enter-to{
        opacity: 1;
    }
    .v-leave-from{
        opacity: 1;
    }
    .v-leave-active{
        transition: opacity .7s ease;
    }
    .v-leave-to{
        opacity: 0;
    }
</style>
<style lang="scss">
    .LARModel{
        &.el-dialog{
            width: 450px;
            background-color: white;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            border-radius: 8px;

            .el-dialog__body{
                width: 100%;
                padding: 0px;
                display: flex;
                position: relative;
                .login{
                    .el-input{
                        margin-bottom: 15px;
                    }

                    .el-button{
                        width: 100%;
                        box-sizing: border-box;
                        height: 34px;
                        margin-bottom: 15px;
                    }
                }
                .register{
                    .el-input{
                    margin-bottom: 15px;
                    }

                    .el-button{
                        width: 100%;
                        box-sizing: border-box;
                        height: 34px;
                        margin-bottom: 15px;
                    }
                }
            }
        }
    }
</style>