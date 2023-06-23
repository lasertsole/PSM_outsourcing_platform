<template>
    <div class="itemBox">
        <div class="itemContent">
            <div class="top">
                <div class="left">
                    <div class="title">{{title}}</div>
                    <div class="describe">{{describe}}</div>
                </div>
                <div class="right">
                    <img :src="imgSrc">
                </div>
            </div>
            <div class="bottom">
                <div class="left">
                    <div class="type">
                        <img src="icons/computer.svg" alt="">
                        <span>{{type}}</span>
                    </div>
                    <div class="calendar">
                        <img src="icons/calendar.svg" alt="">
                        <span>{{calendar}}</span>
                    </div>
                </div>
                <div class="right">{{price}}</div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import {defineProps, ref, PropType} from "vue"
    const props = defineProps({
        title:{type:String, required:true},
        describe:{type:String, required:true},
        imgSrc:{type:String, required:true},
        type:{type:String, required:true},
        calendar:{type:String, required:true},
        price:{type:String, required:true},
    });
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .itemBox{
        @include fixedRetangle(500px ,180px);
        display: flex;
        flex-direction: row;
        padding: 10px;
        box-sizing: border-box;
        .itemContent{
            width: 100%;
            height: 100%;
            border-radius: 5px;
            overflow: hidden;
            display: flex;
            flex-direction: column;
            border: 1.5px solid rgba(165, 165, 165, 0.3568627451);
            .top{
                @include fixedHeight(120px);
                padding: 10px 10px 0px 10px;
                box-sizing: border-box;
                display: flex;
                flex-direction: row;
                >*{
                    box-sizing: border-box;
                    height: 100%;
                    border-bottom: 1px solid rgba(165, 165, 165, 0.3568627451);
                    padding-bottom: 10px;
                }
                .left{
                    @include fixedWidth(60%);
                    .title{
                        font-family: SourceSans3-Medium;
                        font-weight: bolder;
                        text-overflow: ellipsis; /* 文本溢出时显示省略号来代表被修剪的文本 */
                        overflow: hidden; /* 溢出部分隐藏 */
                        white-space: nowrap; /* 段落中的文本不进行换行 */
                        height: 24px;
                    }
                    .describe{
                        color: #606266;
                        //多行文本溢出处理方法
                        overflow: hidden;
                        text-overflow: ellipsis;
                        display: -webkit-box;
                        -webkit-line-clamp: 3;
                        -webkit-box-orient: vertical;
                        height: 75px;
                    }
                }
                .right{
                    @include fixedWidth(40%);
                    img{
                        width: 100%;
                        height: 100%;
                        object-fit: cover;
                    }
                }
            }
            .bottom{
                display: flex;
                flex-direction: row;
                @include fixedHeight(30px);
                padding: 0px 10px;
                justify-content: space-between;
                align-items: center;
                font-size: 14px;
                >*{
                    height: 100%;
                    display: flex;
                    flex-direction: row;
                    align-items: center;
                    >*{
                        display: flex;
                        align-items: center;
                        flex-basis: 150px;
                        flex-shrink: 1;
                        img{
                            height: 15px;
                        }
                        span{
                            white-space: nowrap; /* 段落中的文本不进行换行 */
                        }
                    }
                }
                .left{
                    @include fixedWidth(60%);
                    display: flex;
                    justify-content: flex-start;
                    .type{
                        font-family: 仓耳舒圆体W03;
                    }
                    .calendar{
                        font-size: 12px;
                        color: #606266;
                    }
                }
                .right{
                    @include fixedWidth(30%);
                    display: flex;
                    justify-content: flex-end;
                    color: #f87299;
                    text-overflow: ellipsis; /* 文本溢出时显示省略号来代表被修剪的文本 */
                    overflow: hidden; /* 溢出部分隐藏 */
                    white-space: nowrap; /* 段落中的文本不进行换行 */
                }
            }
        }
    }
</style>