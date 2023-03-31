<template>
    <div class="header">
        <div class="left">
            <!-- 左侧logo -->
            <router-link :to="'index'" class="logo">
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
            <div @click="drawer=!drawer" class="menu"></div>
        </div>
    </div>
    <div :class="{selectList:true, show:drawer}">
        <router-link v-for="item in tabBarArr" active-class="active" :to="<RouteLocationRaw>item.linkTo">{{item.text}}</router-link>
        <hr>
        <a href="#">登录</a>
        <a href="#">注册</a>
    </div>
</template>

<script setup lang="ts">
    import {ref} from "vue"
    import {RouteLocationRaw} from "vue-router"
    import {routerInfo} from "@/types/componentsType/HeaderComponent"//获取本页面要使用的复合类型
    import tabBar from "@/components/frameComponents/header/tabBar.vue"
    
    const drawer = ref<Boolean>(false);//控制菜单下拉框显示与否
    const tabBarArr = ref<routerInfo[]>([
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
    @mixin square($edgeSize){
        width: $edgeSize;
        height: $edgeSize;
    }
    @keyframes selectListMove{
        0%{
            transform: translateY(15px);
        }
        100%{
            transform: translateY(0px);
        }
    }

    .header {
        transition: height .3s linear;
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
        padding: 0px 30px;
        white-space: nowrap;
        box-sizing: border-box;
        .left{
            display: flex;
            flex-direction: row;
            align-items: center;
            .logo{
                display: flex;
                flex-direction: row;
                align-items: center;
                margin-right: 10px;
                .logo-image{
                    @include square(35px);
                    background-image: url(icons/vite.svg);
                    background-repeat: no-repeat;
                    background-position: center;
                    background-size: 100%;
                    margin-right: 5px;
                }
                .logo-info{
                    h2{
                        font-size: 24px;
                    }

                    h5{
                        font-size: 13.5px;
                    }
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
            .menu{
                display: none;
                @include square(25px);
                background-image: url(icons/menu.svg);
                background-size: 100%;
                cursor: pointer;
            }
        }
    }
    .selectList{
        display: none;
        background-color: white;
        transition: all .3s ease-in-out;
        box-sizing: border-box;
        width: 100%;
        height: 0px;
        position: absolute;
        top: 50px;
        left: 0px;
        z-index: 2;
        overflow: hidden;
        padding: 0px 15px;
        &.show{
            height: 388px;
        }

        a{
            padding: 15px 10px;
            color: #4a4a4a;
            font-size: 14px;
            display: block;
            transition: background-color .3s ease-in-out;
            border-radius: 5px;
            &:hover{
                background-color: #cccccc;
            }
        }
        hr{
            height: 1px;
            margin: 5px 0px;
        }
    }
    @media screen and (max-width: 800px) and (min-width: 600px) {
        .header{
            padding: 0px 0px;
            .left{
                .logo{
                    display: none;
                }
            }
        }
    }
    @media screen and (max-width: 600px) {
        .header{
            padding: 0px 15px;
            height: 50px;
            .left{
                .logo{
                    .logo-image{
                        @include square(25px);
                    }
                    .logo-info{
                        h2{
                            font-size: 17px;
                        }

                        h5{
                            font-size: 7.5px;
                        }
                    }
                }
                .tabBar{
                    display: none;
                }
            }
            .right{
                .loginOrRegister{
                    display: none;
                }
                .menu{
                    display: flex;
                }
            }
        }
        .selectList{
            display: block;
            &.show{
                padding: 10px 15px;
            }
            animation: selectListMove .3s linear;
        }
    }
</style>