<template>
    <div class="loginAndResigter">
        <div class="box">
            <router-link to="/index" class="logo">
                <div class="logo-image"></div>
                <div class="logo-text">
                    <h2>喵字幕</h2>
                    <h5>miaozimu.com</h5>
                </div>
            </router-link>
            <keep-alive>
                <transition mode="out-in">
                    <div v-if="pageController" class="login">
                        <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="手机号" v-model="loginAccountData" clearable/>
                        <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="密码" v-model="loginPasswordData" clearable/>
                        <el-button type="primary" @click="mainStore.loginAccount(loginAccountData, loginPasswordData)">登录</el-button>
                        <div class="select">
                            <span @click="changePage">注册新账号</span>
                            <span>忘记密码</span>
                        </div>
                    </div>
                    <div v-else :class="{register:true, show:!pageController}">
                        <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="手机号" v-model="registerAccountData" clearable/>
                        <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="密码" v-model="registerPasswordData" clearable/>
                        <div class="contract">请确认您已同意 <router-link to="/contract">《喵剪辑服务协议》</router-link></div>
                        <el-button type="primary" @click="mainStore.registerAccount(registerAccountData, registerPasswordData)">注册</el-button>
                        <div class="backLogin" @click="changePage">已有账号,去登录</div>
                    </div>
                </transition>
            </keep-alive>
        </div>
    </div>
</template>

<script setup lang="ts">
    import {ref} from "vue";
    import useGlobal from "@/global"

     /**********获取全局变量*********/
    const global = useGlobal();
    const mainStore = global?.UserInfo;

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
    .loginAndResigter{
        width: 100%;
        height: 100%;
        overflow-y: auto;
        background-color: rgb(230,234,238);
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;
        .box{
            box-sizing: border-box;
            padding: 60px 50px;
            background-color: white;
            margin-top: 60px;
            width: 450px;
            border-radius: 8px;
            overflow: hidden;
            display: flex;
            flex-direction: column;
            align-items: center;
            .logo{
                margin-bottom: 50px;
                display: flex;
                flex-direction: row;
                align-items: center;
                &-image{
                    @include fixedSquare(60px);
                    background-image: url(images/psmlogo.png);
                    background-size: 100%;
                }
                
                &-text{
                    h2{
                        font-size: 22px;
                        color: #fb7299;
                    }

                    h5{
                        font-size: 11.5px;
                        color: #fb7299;
                    }
                }
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
    //transition出入场动画
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
    .loginAndResigter{
        .box{
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
</style>