<template>
    <el-carousel 
        height="100%"
        arrow="never"
        :pause-on-hover="false"
        indicator-position="none">
        <el-carousel-item v-for="item in imageArr" :key="item">
            <img class="carousel-image" :src="item.toString()" alt="">
        </el-carousel-item>
    </el-carousel>
</template>

<script lang="ts" setup>
    import {ref} from "vue"
    const imageArr = ref<String[]>(["/Carousel/bg-1.jpg","/Carousel/bg-2.jpg","/Carousel/bg-3.jpg"]);
</script>

<style scoped>
    .carousel-image{
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
</style>
<style lang="scss">
    .el-carousel{
        width: 100%;
        height: 100%;
    }
</style>