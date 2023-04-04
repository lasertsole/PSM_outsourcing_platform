<template>
    <div class="showcase">
        <div class="leftBox"></div>
        <div class="rightBox">
            <tabBar :tabList="tabList"></tabBar>
        </div>
        <router-view></router-view>
    </div>
</template>

<script setup lang="ts">
    import {ref} from "vue"
    import tabBar from "@/components/common/tabBar.vue"

    const tabList = ref<{tabName:string,linkTo:string}[]>([
        {tabName:"剪辑类",linkTo:""},
        {tabName:"美工类",linkTo:""},
        {tabName:"翻译类",linkTo:""},
        {tabName:"字幕类",linkTo:""},
    ]);
</script>

<style lang="scss" scoped>
    .showcase{
        width: 100%;
        height: 100%;
        overflow-y: auto;
        padding: 30px;
        .leftBox{

        }
        .rightBox{
            display: flex;
        }
    }
</style>