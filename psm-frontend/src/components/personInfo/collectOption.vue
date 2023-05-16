<template>
    <ul class="collectOption">
        <li>画师收藏</li>
        <li class="center">作品收藏</li>
        <li>橱窗收藏</li>
    </ul>
</template>

<script setup lang="ts">
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "@/common.scss";
    .collectOption{
        background-color: white;
        display: flex;
        font-size: 12px;
        margin-top: 10px;
        align-items: center;
        padding: 15px;
        box-sizing: border-box;
        @include fixedRoundedRectangle(100%, 100px, 4px);

        li{
            @include fixedWidth(math.div(100%,3));
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 10px;
            box-sizing: border-box;
            @include fixedHeight(70px);
            &.center{
                padding: 10px 0px;
                display: flex;
                align-items: center;
                justify-content: space-between;
                &::after,&::before{
                    content: "";
                    @include fixedRetangle(0.5px, 30px);
                    background-color: lightgray;
                }
            }
        }
    }
</style>