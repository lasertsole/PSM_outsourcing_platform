<template>
    <div class="selectItem">
        <div class="blackLineContainer">
            <div class="blackLine" :style="`transform: translateY(${selectIndex*100}%);`"></div>
        </div>
        <ul>
            <li :class="{selected:selectIndex==0}" @click="changeSelectIndex(0)">我的企划</li>
            <li :class="{selected:selectIndex==1}" @click="changeSelectIndex(1)">我的画师</li>
            <li :class="{selected:selectIndex==2}" @click="changeSelectIndex(2)">我的稿件</li>
            <li :class="{selected:selectIndex==3}" @click="changeSelectIndex(3)">系统通知</li>
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
    .selectItem{
        box-sizing: border-box;
        width: 100%;
        height: 100%;
        display: flex;

        .blackLineContainer{
            height: 100%;
            width: 1.5%;
            padding: 20px 0px;
            box-sizing: border-box;
            .blackLine{
                height: 25%;
                background-color: black;
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
                    color: black;
                    font-weight: bold;
                }
            }
        }
    }
</style>