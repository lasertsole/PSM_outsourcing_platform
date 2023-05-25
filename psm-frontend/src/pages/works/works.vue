<template>
    <div class="works">
        <uploadImg></uploadImg>
    </div>
</template>

<script setup lang="ts">
    import uploadImg from "@/components/works/uploadImg.vue"
    import useGlobal from "@/global"
    import { storeToRefs } from "pinia";

    /**********获取全局变量*********/
    const global = useGlobal();
    const mainStore = global?.UserInfo;
    const { userinfo, token } = storeToRefs(mainStore);
</script>

<style lang="scss" scoped>
    .works{
        width: 100%;
        height: 100%;
        overflow-y: auto;
    }
</style>