<template>
    <div class="classify">
        <div class="full-contain">
            <div class="tabBox">
                <ul>
                    <li @click="changeTabIndex(index)" v-for="item,index in tabList">
                        <router-link :class="{selected:tabIndex == index}" :to="item.linkTo">{{item.tabName}}</router-link>
                    </li>
                </ul>
                <div class="lineBar">
                    <div class="line" :style="`width:${1/tabList.length*100}%; transform: translateX(${tabIndex*100}%);`"></div>
                </div>
            </div>
        </div>
        <div class="full-line"></div>
    </div>
</template>

<script setup lang="ts">
    import {ref} from "vue"
    import {tabBarItem} from "@/types/common/tabBarType"
    import { defineProps, PropType } from "vue";

    const tabIndex = ref<number>(0);
    function changeTabIndex(index:number):void{
        tabIndex.value=index;
    }

   const props = defineProps({tabList:{type:Array as PropType<tabBarItem[]>, default:[], required: false}});
</script>

<style lang="scss" scoped>
    .classify{
        .full-contain{
            display: flex;
            flex-direction: row;
            position: relative;
            z-index: 2;
            .tabBox{
                display: flex;
                flex-direction: column;
                ul{
                    display: flex;
                    li{
                        display: flex;
                        justify-content: center;
                        padding: 10px;
                        a{
                            color: #707070;
                            &.selected{
                                color: #00a8e9;
                            }
                        }
                    }
                }
                .lineBar{
                    height: 2px;
                    .line{
                        height: 100%;
                        background-color: #00a8e9;
                        transition: transform .3s ease;
                    }
                }
            }
        }
        .full-line{
            height: 1.5px;
            background-color: #a5a5a55b;
            position: relative;
            z-index: 1;
            transform: translateY(-1.75px);
        }
    }
</style>