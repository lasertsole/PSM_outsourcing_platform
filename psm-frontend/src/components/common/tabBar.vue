<template>
    <div class="classify">
        <ul>
            <li @click="changeTabIndex(index)" v-for="item,index in tabList">
                <router-link :class="{selected:tabIndex == index}" :to="item.linkTo">{{item.tabName}}</router-link>
            </li>
        </ul>
        <div class="lineBar">
            <div class="line" :style="`width:${1/tabList.length*100}%; transform: translateX(${tabIndex*100}%);`"></div>
        </div>
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
</style>