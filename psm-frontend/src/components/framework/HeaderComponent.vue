<template>
    <div class="header">
        <div class="left">
            <!-- 左侧logo -->
            <logo></logo>
            <div class="tabBar" v-if="isOnline">
                <tabBar :tabBarArr="tabBarArr"></tabBar>
            </div>
        </div>
        <div class="right">
            <loginOrlogout></loginOrlogout>
            <div @click="drawer=!drawer" class="menu"></div>
        </div>
    </div>
    <dropMenu :tabBarArr="tabBarArr" :drawer="drawer" @closeDropMenu="closeDropMenu"></dropMenu>
</template>

<script setup lang="ts">
    import {ref} from "vue"
    import useGlobal from "global";
    import { storeToRefs } from "pinia";
    import {routerInfo} from "types/componentsType/HeaderComponent"//获取本页面要使用的复合类型

    import logo from "components/frameComponents/header/logo.vue"
    import tabBar from "components/frameComponents/header/tabBar.vue"
    import dropMenu from "components/frameComponents/header/dropMenu.vue"
    import loginOrlogout from "components/frameComponents/header/loginOrlogout.vue"
    
    const global = useGlobal();
    const accountInfo = global?.accountInfo;//获取用户账号信息的pinia
    const { isOnline } = storeToRefs(accountInfo);

    const drawer = ref<boolean>(false);//控制菜单下拉框显示与否
    function closeDropMenu():void{//屏幕大于600px时关闭下拉框
        drawer.value = false;
    }

    const tabBarArr = ref<routerInfo[]>([
        {text:"首页", linkTo:"/personInfo"},
        {text:"橱窗", linkTo:"/showcase"},
        {text:"企划", linkTo:"/planning"},
        {text:"作品", linkTo:"/works"},
    ])
</script>

<style lang="scss" scoped>
    @mixin square($edgeSize){
        width: $edgeSize;
        height: $edgeSize;
    }

    .header {
        transition: height .3s linear;
        position: relative;
        z-index: 2;
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
        border-bottom: 1px solid rgba(165, 165, 165, 0.3568627451);;
        .left{
            height: 100%;
            display: flex;
            flex-direction: row;
            align-items: center;
            .tabBar{
                display: flex;
                align-items: center;
            }
        }

        .right{
            .menu{
                display: none;
                @include square(25px);
                background-image: url(icons/menu.svg);
                background-size: 100%;
                cursor: pointer;
            }
        }
    }
    @media screen and (max-width: 800px) and (min-width: 600px) {
        .header{
            padding: 0px 0px;
            .left{
            }
        }
    }
    @media screen and (max-width: 600px) {
        .header{
            padding: 0px 15px;
            height: 50px;
            .left{
                .tabBar{
                    display: none;
                }
            }
            .right{
                .menu{
                    display: flex;
                }
            }
        }
    }
</style>