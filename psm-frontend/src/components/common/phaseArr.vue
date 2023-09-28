<template>
    <div class="phraseArr">
        <div class="item" v-for="(item, index) in itemArr">
            <div class="percentage">{{ item.percentage }}</div>
            <div class="text">{{ item.text }}</div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import { defineProps, ref, PropType } from "vue";
    import { phaseArr } from "@/types/common/phaseArr";

    /**********获取父组件传来的变量*********/
    const props = defineProps({
        itemArr:{type:Object as PropType<phaseArr[]>, required: true, default:[]}
    });
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .phraseArr{
        .item{
            display: flex;
            align-items: center;
            margin-bottom: 16px;

            .percentage{
                display: flex;
                justify-content: center;
                color: #00a8e9;
                font-size: 12px;
                @include fixedRetangle(51px, 21px);
                border: #00a8e9 solid 0.8px;
                border-radius: 10.5px;
                margin-right: 15px;
                position: relative;
            }

            .text{
                font-size: 16px;
            }

            &:not(:last-of-type){
                .percentage{
                    &::after{
                        display: block;
                        content: "";
                        @include fixedRetangle(1px, 10px);
                        background-color: #00a8e9;
                        position: absolute;
                        bottom: -15px;
                    }
                }
            }
        }
    }
</style>