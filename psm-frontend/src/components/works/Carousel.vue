<template>
    <div class="block text-center" m="t-4">
        <el-carousel trigger="click" height="180px">
            <el-carousel-item v-for="item in pics" :key="item">
                <div class="image" :style="`background-image:url(${item});`"></div>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>

<script lang="ts" setup>
    import {defineProps, ref, PropType} from "vue";
    const props = defineProps(
        {carouselProcessArr:{type:Array as PropType<string[]>}}
    );
    let pics:string[] = ['Carousel/bg-1.jpg','Carousel/bg-2.jpg','Carousel/bg-3.jpg'];
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .block{
        overflow: hidden;
        border-radius: 10px;
        .demonstration {
            color: var(--el-text-color-secondary);
        }

        .el-carousel__item h3 {
            color: #475669;
            opacity: 0.75;
            line-height: 150px;
            margin: 0;
            text-align: center;
        }

        .el-carousel__item:nth-child(2n) {
            background-color: #99a9bf;
        }

        .el-carousel__item:nth-child(2n + 1) {
            background-color: #d3dce6;
        }

        .image{
            @include fullInParent();
            background-size: 100%;
            background-position: center;
        }
    }
</style>
