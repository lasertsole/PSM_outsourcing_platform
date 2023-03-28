<template>
    <div class="header">
        <div class="left">
            <!-- 左侧logo -->
            <router-link :to="'/index'" class="logo">
                <div class="logo-image"></div>
                <div class="logo-info">
                    <h2>喵字幕</h2>
                    <h5>miaozimu.com</h5>
                </div>
            </router-link>

            <div class="tabBar">
                <tabBar :tabBarArr="tabBarArr"></tabBar>
            </div>
        </div>
        <div class="right">
            <div class="loginOrRegister">
                <div class="login">登录</div>
                <div class="register">注册</div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import tabBar from "/src/components/frameComponents/header/tabBar.vue"
    import {ref} from "vue"
    
    const tabBarArr = ref<{text:String, linkTo:String}[]>([
        {text:"剪辑", linkTo:""},
        {text:"美工", linkTo:""},
        {text:"翻译", linkTo:""},
        {text:"橱窗", linkTo:""},
        {text:"作品", linkTo:""},
    ])
</script>

<style lang="scss" scoped>
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

    .header {
        position: relative;
        z-index: 1;
        background: white;
        display: flex;
        flex-direction: row;
	    align-items: center;
        justify-content: space-between;
        flex-shrink: 0;
        height: 80px;
        width: 100%;
        padding: 0 30px;
        white-space: nowrap;
        box-sizing: border-box;
        .left{
            display: flex;
            flex-direction: row;
            .logo{
                display: flex;
                flex-direction: row;
                margin-right: 10px;
                .logo-image{
                    width: 50px;
                    height: 50px;
                    background-image: url(icons/vite.svg);
                    background-repeat: no-repeat;
                    background-position: center;
                    margin-right: 5px;
                }
                .logo-info{

                }
            }
            .tabBar{
                display: flex;
                align-items: center;
            }
        }

        .right{
            .loginOrRegister{
                display: flex;
                flex-direction: row;
                .login{
                    background-color: #fb7299;
                    @include button;
                }
                .register{
                    background-color: #00a8e9;
                    @include button;
                }
            }
        }
    }
</style>
