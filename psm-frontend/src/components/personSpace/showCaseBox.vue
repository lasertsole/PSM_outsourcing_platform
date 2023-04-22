<template>
    <div class="box">
        <div class="top"><img :src="imgSrc" alt=""></div>
        <div class="bottom">{{describt}}</div>
    </div>
</template>

<script setup lang="ts">
    import {defineProps} from "vue"
    const props = defineProps({imgSrc:String, describt:String});
</script>

<style lang="scss" scoped>
    @mixin fiexedWidth($size){
        max-width: $size;
        min-width: $size;
    }
    .box{
        @include fiexedWidth(250px);
        margin: 10px 20px;
        .top{
            overflow: hidden;
            border-radius: 12px;
            height: 150px;
            img{
                width: 100%;
                height: 100%;
                object-fit: cover;
            }
        }
        .bottom{
            padding: 10px 0px 0px 10px;
            height: 20px;
            font-size: 12px;
            font-weight: bolder;
        }
    }
</style>