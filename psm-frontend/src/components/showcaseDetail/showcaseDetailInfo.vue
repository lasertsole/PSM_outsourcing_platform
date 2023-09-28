<template>
    <div class="content">
        {{ info.text }}
    </div>
</template>

<script setup lang="ts">
    import { defineProps, PropType } from "vue";
    import { showcaseDetailInfo } from "types/componentsType/showacaseDetailComponent";
    /**********获取父组件传来的变量*********/
    const props = defineProps({
        info:{type:Object as PropType<showcaseDetailInfo>, required: true, default:{text:""}}
    });
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .content{
        flex-grow: 1;
        padding:10px;
        display: flex;
        flex-direction: column;
    }
</style>
