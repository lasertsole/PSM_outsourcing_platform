<template>
    <div class="selectItem">
        <div class="lineContainer">
            <div class="line" :style="`transform: translateY(${selectIndex*100}%);`"></div>
        </div>
        <ul>
            <li :class="{selected:selectIndex==0}" @click="changeSelectIndex(0)">
                <router-link to="/personInfo/myShowcase">
                    <img src="icons/showcaseCollect.png"/>
                    <div>我的橱窗</div>
                </router-link>
            </li>
            <li :class="{selected:selectIndex==1}" @click="changeSelectIndex(1)">
                <router-link to="/personInfo">
                    <img src="icons/bluePlanning.png"/>
                    <div>我的企划</div>
                </router-link>
            </li>
            <li :class="{selected:selectIndex==2}" @click="changeSelectIndex(2)">
                <router-link to="/personInfo">
                    <img src="icons/worksCollect.png"/>
                    <div>我的作品</div>
                </router-link>
            </li>
            <li :class="{selected:selectIndex==3}" @click="changeSelectIndex(3)">
                <router-link to="/personInfo">
                    <img src="icons/blueAnnounce.png"/>
                    <div>系统通知</div>
                </router-link>
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

                a{
                    display: flex;
                    color: inherit;
                    height: 100%;
                    align-items: center;
                    flex-grow: 1;

                    img{
                        @include fixedSquare(25px);
                        margin-right: 10px;
                    }
                }
            }
        }
    }
</style>