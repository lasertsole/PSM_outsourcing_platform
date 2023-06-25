<template>
    <div class="progressBar">
        <div class="progressNames">
            <template v-for="(item,index) in progressPhase">
                <div class="progressName"
                    :style="`color: ${item.positionNum<=progressNum?'#00a8e9':'black'};
                        z-index: ${index};
                        width:${Math.round(item.positionNum / props.progressMax * 10000) / 100}%
                    `"
                    >
                    <span :style="`width: ${item.name.length}em;`">{{ item.name }}</span>
                </div>
            </template>
        </div>
        <div class="progressLine">
            <div class="doneLine"></div>
            <template v-for="(item,index) in progressPhase">
                <div class="phaseLine" :style="`z-index: ${index}; width:${Math.round(item.positionNum / props.progressMax * 10000) / 100}%`">
                    <div class="dot" :style="`background-color: ${item.positionNum<=progressNum?'#00a8e9':'black'};`"></div>
                </div>
            </template>
        </div>
    </div>
</template>

<script setup lang="ts">
    import { ref, defineProps, PropType } from 'vue';
    import { phaseArr } from "@/types/common/phaseArr"

    const props = defineProps({
        progressNum:{type:Number, required: true},
        progressMax:{type:Number, required: true},
        progressPhase:{type:Array as PropType<phaseArr[]>, required: true},
        progressTime:{type:Number, required: false},
        progressDelay:{type:Number, required: false},
    });

    /*****进度条变化*****/
    const percent = ref<string>("0%");//计算百分比
    setTimeout(
        ()=>{percent.value=Math.round(props.progressNum / props.progressMax * 10000) / 100 + "%"},
        props.progressDelay?props.progressDelay:500);
    
    const computedProgressTime = ref<string>(props.progressTime?props.progressTime/1000+"s":"0.7s");
    
    /*****阶段激活*****/
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .progressBar{
        width: 100%;
        .progressNames{
            position: relative;
            $progressNamesHeight:25px;
            @include fixedHeight($progressNamesHeight);
            margin-bottom: 5px;
            .progressName{
                text-align: right;
                position: absolute;
                
                span{
                    display: inline-block;
                    @include fixedHeight($progressNamesHeight);
                }
                &:first-of-type{
                    span{
                        transform: translateX(-50%);
                    }
                }
                
                &:not(:first-of-type){
                    span{
                        transform: translateX(50%);
                    }
                }
            }
        }
        .progressLine{
            width: 100%;
            height: 1px;
            background-color: black;
            position: relative;
            .doneLine{
                background-color: #00a8e9;
                height: 100%;
                $progressTime: v-bind(computedProgressTime);
                transition: all $progressTime ease-out;
                $percent: v-bind(percent);
                width: $percent;
            }
            
            .phaseLine{
                background-color: rgba($color: #ffffff, $alpha: 0);
                position: absolute;
                height: 100%;
                .dot{
                    @include fixedCircle(6px);
                    float: right;
                    transform: translateY(-50%);
                    position: relative;
                    top:-1px;
                }
            }
        }
    }
</style>