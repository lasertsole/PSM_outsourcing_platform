<template>
    <div class="content">
        <div class="topBar">
            <div class="icon"></div>
            <span>创作阶段</span> 
        </div>
        <div class="progressBar">
            <pre>
                1
                2
                3
                4
                5
            </pre>
        </div>
    </div>
</template>

<script setup lang="ts">
    import progressBar from "components/common/progressBar.vue"
    import { phaseArr } from "@/types/common/phaseArr"

    const phase:phaseArr[] = [
        {name:"草稿阶段",positionNum:0},
        {name:"初步定稿",positionNum:50},
        {name:"确认收稿",positionNum:100},
    ]
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .content{
        flex-grow: 1;
        padding:10px;
        display: flex;
        flex-direction: column;

        .topBar{
            height: 20px;
            box-sizing: content-box;
            padding-bottom: 20px;
            margin-bottom: 20px;
            font-size: 16px;
            font-weight: bold;
            border-bottom: 1px solid #ececec;
            display: flex;
            justify-content: flex-start;
            align-items: center;
            color: #4a4a4a;

            .icon{
                @include fixedSquare(20px);
                background-image: url(icons/createPhase.svg);
                background-size: 100%;
                background-position: center;
                margin-right: 10px;
            }
        }
    }
</style>
