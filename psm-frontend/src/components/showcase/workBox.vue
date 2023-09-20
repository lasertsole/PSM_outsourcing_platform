<template>
    <div class="workBox" @click="jumpToShowcaseDetail()">
        <div class="top">
            <span class="abstract">{{abstract}}</span>
            <span class="price">￥{{price}}</span>
        </div>
        <div class="bottom">
            <img :src="`${serverUrl+imgPath}`">
        </div>
    </div>
</template>

<script setup lang="ts">
    import useGlobal from "global";
    import { defineProps } from "vue"
    import { useRouter } from "vue-router";
    const props = defineProps({abstract:String, price:String, imgPath:String});

    /*点击橱窗盒子的作品盒子后页面跳转*/
    const router = useRouter();
    function jumpToShowcaseDetail():void{
        router.replace({ name: 'showcaseDetail',query:{username:"fuck u"}});
    }

    const global = useGlobal();
    const serverUrl:string = global?.serverUrl;//从环境变量中获取服务器地址
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .workBox{
        box-sizing: border-box;
        @include fixedWidth(200px);
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
            >*{
                text-overflow: ellipsis; /* 文本溢出时显示省略号来代表被修剪的文本 */
                overflow: hidden; /* 溢出部分隐藏 */
                white-space: nowrap; /* 段落中的文本不进行换行 */
            }
            .abstract{
                @include fixedWidth(100px);
                font-family: SourceSans3-Medium;
                font-weight: bold;
            }
            .price{
                @include fixedWidth(73px);
                font-family: SourceSans3-Medium;
                font-weight: bold;
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