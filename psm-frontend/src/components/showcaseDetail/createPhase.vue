<template>
    <div class="content">
        <div>
        </div>
        <div class="progressBar">
            <progressBar
                :progressNum="50"
                :progressMax="100"
                :progressTime="700"
                :progressPhase="phase"
            ></progressBar>
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
        align-items: center;
        .progressBar{
            @include fixedWidth(450px);
        }
    }
</style>
