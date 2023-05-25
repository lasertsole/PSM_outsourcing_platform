<template>
    <div class="planning">
        <tabBar
            :tabList="tabList"
            :paddingLeft="30"
            :paddingRight="30"
        ></tabBar>
        <router-view></router-view>
    </div>
</template>

<script setup lang="ts">
    import {ref} from "vue"
    import { tabBarItem } from "@/types/common/tabBarType"
    import tabBar from "@/components/common/tabBar.vue"

    const tabList = ref<tabBarItem[]>([
        {tabName:"个人",linkTo:"/planning/person"},
        {tabName:"企业",linkTo:"/planning/commerce"},
    ]);
</script>

<style lang="scss" scoped>
    .planning{
        width: 100%;
        height: 100%;
        box-sizing: border-box;
        overflow-y: auto;
        padding: 30px 80px;
        display: flex;
        flex-direction: column;
    }
</style>