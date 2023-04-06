<template>
    <div class="showcase">
        <tabBar :tabList="tabList"></tabBar>
        <router-view></router-view>
    </div>
</template>

<script setup lang="ts">
    import {ref} from "vue"
    import { tabBarItem } from "@/types/common/tabBarType"
    import tabBar from "@/components/common/tabBar.vue"

    const tabList = ref<tabBarItem[]>([
        {tabName:"剪辑类",linkTo:"/showcase/montage"},
        {tabName:"美工类",linkTo:"/showcase/art"},
        {tabName:"翻译类",linkTo:"/showcase/translate"},
        {tabName:"字幕类",linkTo:"/showcase/captions"},
    ]);
</script>

<style lang="scss" scoped>
    .showcase{
        width: 100%;
        height: 100%;
        box-sizing: border-box;
        overflow-y: auto;
        padding: 30px;
    }
</style>