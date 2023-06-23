<template>
    <div class="header">
        <div class="left">
            <span>我的企划</span>
        </div>
        <div class="right">
            <div class="toolBar">
                <img src="icons/magnifier.svg"/>
                <img src="icons/setting.svg"/>
            </div>
            <tabBar :tabList="tabList"></tabBar>
        </div>
    </div>
</template>

<script setup lang="ts">
    import {ref} from "vue"
    import tabBar from "components/common/tabBar.vue"
    import { tabBarItem } from "types/common/tabBarType"

    const tabList = ref<tabBarItem[]>([
        {tabName:"进行中",linkTo:"/personInfo"},
        {tabName:"已归档",linkTo:"/personInfo"},
    ]);
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";

    .header{
        height: 60px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px 30px;
        box-sizing: border-box;
        
        .left{

        }

        .right{
            display: flex;
            align-items: center;
            .toolBar{
                @include fixedRetangle(150px, 100%);
                padding: 10px 30px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                img{
                    @include fixedCircle(30px);
                    padding: 5px;
                    border: 1px solid lightgray;
                    cursor: pointer;
                }
            }
            &::v-deep(.full-line){
                background-color: transparent;
            }
        }
    }
</style>