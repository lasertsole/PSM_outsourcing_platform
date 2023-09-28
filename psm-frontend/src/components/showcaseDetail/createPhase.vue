<template>
    <div class="content">
        <div class="topBar">
            <div class="icon"></div>
            <span>创作阶段</span> 
        </div>
        <div class="content">
            <phaseArr
                :itemArr="info?.itemArr"
            ></phaseArr>
        </div>
    </div>
</template>

<script setup lang="ts">
    import { defineProps, PropType } from "vue";
    import { createPhase } from "types/componentsType/showacaseDetailComponent";
    import phaseArr from "@/components/common/phaseArr.vue";

    /**********获取父组件传来的变量*********/
    const props = defineProps({
        info:{type:Object as PropType<createPhase>, required: true, default:{text:""}}
    });

    console.log(props.info.itemArr);
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .content{
        flex-grow: 1;
        padding: 0px 10px;
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
