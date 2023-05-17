<template>
    <div class="myPlanning">
        <div class="header">
            <span>我的企划</span>
        </div>
    </div>
</template>

<script setup lang="ts">
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "@/common.scss";

    .myPlanning{
        @include fixedRoundedRectangle(100%, 300px, 6px);
        background-color: white;
        box-sizing: border-box;

        .header{
            height: 60px;
            display: flex;
            align-items: center;
            padding: 10px 30px;
            border-bottom: 1px lightgray solid;
            box-sizing: border-box;
        }
    }
</style>