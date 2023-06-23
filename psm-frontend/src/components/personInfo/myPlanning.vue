<template>
    <div class="myPlanning">
        <planningHeader/>
        <RouterView></RouterView>
    </div>
</template>

<script setup lang="ts">
    import planningHeader from "components/personInfo/planningHeader.vue";
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";

    .myPlanning{
        @include fixedRoundedRectangle(100%, 300px, 6px);
        background-color: white;
        box-sizing: border-box;
    }
</style>