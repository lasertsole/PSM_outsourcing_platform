<template>
    <div class="works">
        <div class="page">
            <Carousel></Carousel>
            <tabBar
                :tabList="tabList"
                :paddingLeft="30"
                :paddingRight="30"
            ></tabBar>
            <router-view  v-slot="{ Component }">
                <keep-alive>
                    <component :is="Component" />
                </keep-alive>
            </router-view>
        </div>
    </div>
</template>

<script setup lang="ts">
    import Carousel from "@/components/works/Carousel.vue";
    import { tabBarItem } from "types/common/tabBarType";
    import tabBar from "@/components/common/tabBar.vue";
    import { storeToRefs } from "pinia";
    import useGlobal from "@/global";
    import {ref} from "vue"

    const tabList = ref<tabBarItem[]>([
        {tabName:"个人",linkTo:"/works/recommend"},
        {tabName:"企业",linkTo:"/works/hot"},
    ]);
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .works{
        overflow-y: auto;
        @include fullInParent();
        display: flex;
        flex-direction: column;
        align-items: center;
        .page{
            margin: 50px 0px;
            @include fixedRetangle(1100px,100%);
        }
    }
</style>