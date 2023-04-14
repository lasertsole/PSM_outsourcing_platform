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
            <transition mode="out-in">
                <div v-if="pageController" class="login">
                    <div class="title">用户登录</div>
                    <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="昵称/邮箱/手机号" clearable/>
                    <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="密码" clearable/>
                    <el-button type="primary">登录</el-button>
                    <div class="select">
                        <span @click="changePage">注册新账号</span>
                        <span>忘记密码</span>
                    </div>
                </div>
                <div v-else :class="{register:true, show:!pageController}">
                    <div class="title">用户注册</div>
                    <div class="role">
                        <div :class="{show:roleController}" @click="changeRole(true)">我是需求方</div>
                        <div :class="{show:!roleController}" @click="changeRole(false)">我是创作者</div>
                    </div>
                    <div class="lineContainer"><div :class="{line:true, move:!roleController}"></div></div>
                    <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="昵称" v-model="registerAccount" clearable/>
                    <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="密码" v-model="registerPassword" clearable/>
                    <div class="contract">请确认您已同意 <router-link to="/contract" @click="LARModel.hideFloat()">《喵剪辑服务协议》</router-link></div>
                    <el-button type="primary">注册</el-button>
                    <div class="backLogin" @click="changePage">已有账号,去登录</div>
                </div>
            </transition>
        </div>
    </el-dialog>
</template>

<script lang="ts" setup>
    import { ref } from "vue";
    import { storeToRefs } from "pinia";
    import useGlobal from "@/global";

    const global = useGlobal();
    const LARModel = global?.LARFloat;
    const { isShowFloat } = storeToRefs(LARModel);//用pinia得知是否开启model

    const registerAccount = ref<string>("");//注册账号
    const registerPassword = ref<string>("")//注册密码


    const pageController = ref<boolean>(true);
    function changePage():void{
        pageController.value=!pageController.value;
    }

    const roleController = ref<boolean>(true);
    function changeRole(option:boolean):void{
        roleController.value=option;
    }

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
                .role{
                    display: flex;
                    flex-direction: row;
                    justify-content: space-between;
                    align-items: center;
                    >div{
                        display: flex;
                        flex-direction: row;
                        justify-content: center;
                        align-items: center;
                        width: 175px;
                        height: 40px;
                        color: #c0c0c0;
                        cursor: pointer;
                        &.show{
                            color: #4a4a4a;
                        }
                    }
                }
                .lineContainer{
                    width: 100%;
                    height: 2px;
                    margin-bottom: 15px;
                    .line{
                        width: 175px;
                        height: 100%;
                        background-color: #00a8e9;
                        transition: transform .3s ease;
                        &.move{
                            transform: translateX(100%);
                        }
                    }
                }
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