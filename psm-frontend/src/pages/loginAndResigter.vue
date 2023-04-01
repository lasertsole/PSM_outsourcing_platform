<template>
    <div class="loginAndResigter">
        <div class="box">
            <div class="logo">
                <div class="logo-image"></div>
                <div class="logo-text">
                    <h2>喵字幕</h2>
                    <h5>miaozimu.com</h5>
                </div>
            </div>
            <transition mode="out-in">
                <div v-if="pageController" class="login">
                    <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="昵称/邮箱/手机号" clearable/>
                    <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="密码" clearable/>
                    <el-button type="primary">登录</el-button>
                    <div class="select">
                        <span @click="changePage">注册新账号</span>
                        <span>忘记密码</span>
                    </div>
                </div>
                <div v-else :class="{register:true, show:!pageController}">
                    <div class="role">
                        <div :class="{show:roleController}" @click="changeRole(true)">我是需求方</div>
                        <div :class="{show:!roleController}" @click="changeRole(false)">我是创作者</div>
                    </div>
                    <div class="lineContainer"><div :class="{line:true, move:!roleController}"></div></div>
                    <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="昵称" clearable/>
                    <el-input :input-style="{lineHeight:'48px',minHeight:'48px'}" :maxlength="12" placeholder="密码" clearable/>
                    <el-button type="primary">注册</el-button>
                    <div class="backLogin" @click="changePage">已有账号,去登录</div>
                </div>
            </transition>
        </div>
    </div>
</template>

<script setup lang="ts">
    import {ref} from "vue"

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
    @keyframes showPage{
        0%{
            opacity: 0;
        }
        100%{
            opacity: 1;
        }
    }
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
                .backLogin{
                    width: 100%;
                    font-size: 14px;
                    color: #00a8e9;
                    display: flex;
                    justify-content: center;
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