<template>
    <div class="selectItem">
        <div class="lineContainer">
            <div class="line" :style="`transform: translateY(${selectIndex*100}%);`"></div>
        </div>
        <ul>
            <li :class="{selected:selectIndex==0}" @click="changeSelectIndex(0)">
                <img src="icons/bluePlanning.png"/>
                <div>我的企划</div>
            </li>
            <li :class="{selected:selectIndex==1}" @click="changeSelectIndex(1)">
                <img src="icons/blueDrawer.png"/>
                <div>我的画师</div>
            </li>
            <li :class="{selected:selectIndex==2}" @click="changeSelectIndex(2)">
                <img src="icons/bluePlanning.png"/>
                <div>我的稿件</div>
            </li>
            <li :class="{selected:selectIndex==3}" @click="changeSelectIndex(3)">
                <img src="icons/blueAnnounce.png"/>
                <div>系统通知</div>
            </li>
        </ul>
    </div>
</template>

<script setup lang="ts">
    import {ref} from "vue"
    const selectIndex = ref<number>(0);

    function changeSelectIndex(index:number):void{
        selectIndex.value=index;
    }
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .selectItem{
        box-sizing: border-box;
        width: 100%;
        height: 100%;
        display: flex;

        .lineContainer{
            height: 100%;
            width: 1.5%;
            padding: 20px 0px;
            box-sizing: border-box;
            .line{
                height: 25%;
                background-color: #0080ff;
                box-sizing: border-box;
                padding: 20px 0px;
                transition: all .3s ease-out;
            }
        }

        ul{
            display: block;
            width: 98.5%;
            height: 100%;
            padding: 20px 0px;
            box-sizing: border-box;
            font-size: 14px;
            
            li{
                box-sizing: border-box;
                height: 25%;
                display: flex;
                align-items: center;
                padding: 0px 20px;
                cursor: pointer;
                border-left: 3px solid transparent;
                color: gray;
                &.selected{
                    color: rgb(90, 90, 90);
                    font-weight: bold;
                }

                img{
                    @include fixedSquare(25px);
                    margin-right: 10px;
                }
            }
        }
    }
</style>