<template>
    <div class="workBox">
        <div class="top">
            <span class="abstract">{{abstract}}</span>
            <span class="price">￥{{price}}</span>
        </div>
        <div class="bottom">
            <img :src="`${imgPath}`">
        </div>
    </div>
</template>

<script setup lang="ts">
    import {defineProps} from "vue"
    const props = defineProps({abstract:String, price:String, imgPath:String});
    
    console.log(props);
</script>

<style lang="scss" scoped>
    .workBox{
        box-sizing: border-box;
        min-width: 200px;
        max-width: 200px;
        height: 150px;
        display: flex;
        flex-direction: column;
        border: 2px solid #959595;
        border-radius: 10px;
        padding: 5px;
        margin-left: 15px;
        cursor: pointer;
        .top{
            height: 25px;
            flex-shrink: 0;
            display: flex;
            justify-content: space-between;
            font-size: 14px;
            align-items: center;
            .abstract{
                font-family: 仓耳舒圆体W03;
            }
            .price{
                font-family: 仓耳舒圆体W04;
                color: #fb7299;
            }
        }
        .bottom{
            flex-grow: 1;
            overflow:hidden;
            img{
                width: 100%;
                height: 100%;
                object-fit: cover;
            }
        }
    }
</style>