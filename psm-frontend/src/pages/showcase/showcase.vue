<template>
    <div class="showcase">
        <tabBar :tabList="tabList" @changeClassifyIndex="changeClassifyIndex"></tabBar>
        
        <!-- vue-router有bug，三层以上的路由，页面会挂载两次，因此用v-if -->
        <div v-if="classifyIndex == 0">
            <montage :primarySort="0"></montage>
        </div>
        <div v-else-if="classifyIndex == 1">
            <art :primarySort="1"></art>
        </div>
        <div v-else-if="classifyIndex == 2">
            <translate :primarySort="2"></translate>
        </div>
        <div v-else-if="classifyIndex == 3">
            <translate :primarySort="3"></translate>
        </div>
        <div v-else>
            <captions :primarySort="4"></captions>
        </div>

    </div>
</template>

<script setup lang="ts">
    import { ref } from "vue"
    import tabBar from "@/components/common/tabBar.vue"
    import { tabBarItem } from "@/types/common/tabBarType"
    import { PrimarySort } from "@/types/pageType/showcase"
    import montage from "@/components/showcase/montage.vue";
    import art from "@/components/showcase/art.vue";
    import translate from "@/components/showcase/translate.vue";
    import captions from "@/components/showcase/captions.vue";

    const tabList = ref<tabBarItem[]>([
        {tabName:PrimarySort[0],index:0},
        {tabName:PrimarySort[1],index:1},
        {tabName:PrimarySort[2],index:2},
        {tabName:PrimarySort[3],index:3},
    ]);

    const classifyIndex = ref<number>(0);
    function changeClassifyIndex(index: number):void{
        classifyIndex.value = index;
    }

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