<template>
    <div class="content">
        <pre>
            1
            2
            3
            4
            5
        </pre>
    </div>
</template>

<script setup lang="ts">
    import { ref, defineProps } from "vue";
    /**********获取父组件传来的变量*********/
    const props = defineProps({
        article:{type:String, required: true}
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
