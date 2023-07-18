<template>
    <div class="showcase">
        <tabBar :tabList="tabList"></tabBar>
        <router-view v-slot="{ Component }">
            <keep-alive>
                <component :is="Component"/>
            </keep-alive>
        </router-view>
    </div>
</template>

<script setup lang="ts">
    import {ref} from "vue"
    import { tabBarItem } from "@/types/common/tabBarType"
    import tabBar from "@/components/common/tabBar.vue"
import axios from "axios";

    const tabList = ref<tabBarItem[]>([
        {tabName:"剪辑类",linkTo:"/showcase/montage"},
        {tabName:"美工类",linkTo:"/showcase/art"},
        {tabName:"翻译类",linkTo:"/showcase/translate"},
        {tabName:"字幕类",linkTo:"/showcase/captions"},
    ]);

    let value = await axios.get("http://127.0.0.1:8000/version");
    console.log(value);
</script>

<style lang="scss" scoped>
    .showcase{
        width: 100%;
        height: 100%;
        box-sizing: border-box;
        overflow-y: auto;
        padding: 30px 80px;
        display: flex;
        flex-direction: column;
    }
</style>