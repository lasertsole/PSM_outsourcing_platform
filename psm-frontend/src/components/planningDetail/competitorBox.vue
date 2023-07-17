<template>
    <div class="competitor">
        <div class="top">
            <div class="left">已应征的画师</div>
            <div class="right">已应征的画师数:<span class="num">{{ 1 }}人</span></div>
        </div>
        <div class="bottom">
            <div class="guy">
                <template v-for="(item, index) in infoArr">
                    <div class="competitor_info">
                        <div class="base">
                            <div class="profile">
                                <img :src="`${item.profile}`">
                            </div>
                            <div class="honour">
                                <div class="name">{{item.name}}</div>
                                <div class="commentNum">{{item.commentNum}}条评论</div>
                                <div class="certificate">{{item.certificate}}</div>
                            </div>
                        </div>
                    </div>
                </template>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import { PropType } from "vue"
    interface itemBoxInfo{
        name:string,
        profile:string,
        commentNum:number,
        certificate:string,
    }

    const props = defineProps({
        infoArr:{type:Array as PropType<itemBoxInfo[]>}, 
    })
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .competitor{
        @include fixedRetangle(900px, 130px);
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        >.top{
            display: flex;
            flex-direction: row;
            font-size: 12px;
            font-weight: bold;
            justify-content: space-between;
            color: #00a8e9;
            >.left{

            }
            >.right{
                margin-right: 120px;
                >.num{
                    color: #444;
                    margin-left: 5px;
                }
            }
        }
        >.bottom{
            display: flex;
            flex-grow: 1;
            align-items: center;
            .guy{
                .competitor_info{
                    margin-left: 10px;
                    min-width: 150px;
                    max-width: 150px;
                    display: flex;
                    flex-direction: column;
                    overflow: hidden;
                    .base{
                        height: 50px;
                        display: flex;
                        flex-direction: row;
                        .profile{
                            @include fixedSquare(50px);
                            border-radius: 50%;
                            overflow: hidden;
                            cursor: pointer;
                            img{
                                width: 100%;
                                height: 100%;
                            }
                        }
                        .honour{
                            margin-left: 10px;
                            overflow: hidden; /* 溢出部分隐藏 */
                            >*{
                                text-overflow: ellipsis; /* 文本溢出时显示省略号来代表被修剪的文本 */
                                overflow: hidden; /* 溢出部分隐藏 */
                                white-space: nowrap; /* 段落中的文本不进行换行 */
                            }
                            .name{
                                font-family: SourceSans3-Medium;
                                font-size: 15px;
                                font-weight: bold;
                            }
                            .commentNum{
                                color: #707070;
                                font-size: 10px;
                                line-height: 15px;
                            }
                            .certificate{
                                color: #707070;
                                font-size: 10px;
                                line-height: 15px;
                            }
                        }
                    }
                }
            }
        }
    }
</style>