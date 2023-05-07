<template>
    <div class="works">
        <uploadImg></uploadImg>
    </div>
</template>

<script setup lang="ts">
    import uploadImg from "@/components/works/uploadImg.vue"
</script>

<style lang="scss" scoped>
    .works{
        width: 100%;
        height: 100%;
        overflow-y: auto;
    }
</style>