<template>
    <div class="filter-bar">
        <ul>
            <li>
                <ul class="detailArr">
                    <div>最新发布:</div>
                    <li v-for="item in bigClass">{{item.value}}</li>
                </ul>
            </li>
            <li>
                <ul class="detailArr">
                    <div>稿件类型:</div>
                    <li v-for="item in smallClass">{{item.value}}</li>
                </ul>
            </li>
            <li>
                <ul class="detailArr">
                    <div>截稿时间:</div>
                    <li v-for="item in timeClass">{{item.value}}</li>
                </ul>
            </li>
        </ul>
    </div>
</template>

<script setup lang="ts">
    import { optionInfo } from "@/types/pageType/showcase"
    import { ref, defineProps, PropType } from "vue"

    const props = defineProps({bigClass:{type:Array as PropType<optionInfo[]>}, 
                smallClass:{type:Array as PropType<optionInfo[]>},
                timeClass:{type:Array as PropType<optionInfo[]>}});
</script>

<style lang="scss" scoped>
    .filter-bar{
        font-size: 14px;
        >ul>li{
            border-bottom: 1px solid #DDD;
            &:first-of-type{
                border-top: 1px solid #DDD;
            }
            .detailArr{
                display: flex;
                flex-direction: row;
                align-items: center;
                div{
                    display: flex;
                    box-sizing: border-box;
                    align-items: center;
                    text-indent: 10px;
                    background-color: #f3f3f3;
                    max-width: 110px;
                    min-width: 110px;
                    height: 34px;
                    color: #666;
                    font-family: SourceSans3-Medium;
                    font-weight: bold;
                }
                li{
                    padding-left: 10px;
                    width: 100px;
                    flex-shrink: 1;
                    color: #005aa0;
                    font-size: 12px;
                }
            }
        }
    }
</style>