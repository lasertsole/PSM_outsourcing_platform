<template>
    <!-- 橱窗概要 -->
    <div class="summary">
        <!-- 橱窗标题 -->
        <div class="title">
            <span class="text">{{ abstractInfo }}</span>
        </div>

        <!-- 用户信息 -->
        <div class="userInfo">
            <!-- 用户头像 -->
            <div class="userProfile" :style="`background-image: url(${profile});`"></div>
            
            <!-- 用户文字信息 -->
            <div class="userText">
                <!-- 用户名字 -->
                <div class="userName">{{ userName }}</div>

                <!-- 用户荣誉 -->
                <div class="userHonor">
                    <!-- 评论数量 -->
                    <div class="commendNum">
                        {{ commentNum }}条评论
                    </div>
                </div>
            </div>
        </div>

        <hr/>
        
        <!-- 橱窗信息 -->
        <div class="tradeInfo">
            <div class="title">橱窗信息</div>
            <!-- 橱窗价格 -->
            <div class="showcaseValue">
                <div class="key">橱窗价格</div>
                <div class="value"><span class="moneyToken">￥</span>{{price}}</div>
            </div>

            <div>
                <div class="key">分类</div>
                <div class="value">{{ PrimarySort[parseInt(primary)] }}</div>
            </div>

            <div>
                <div class="key">标签</div>
                <div class="value">{{ sortArr[parseInt(primary)][parseInt(last)] }}</div>
            </div>

            <div>
                <div class="key">档期空闲</div>
                <div class="value">{{ isIdle=='0'?"否":"是" }}</div>
            </div>

            <div>
                <div class="key">能否加急</div>
                <div class="value">{{ canQuicky=='0'?"否":"是" }}</div>
            </div>
        </div>

        <!-- 动作按钮 -->
        <div class="motionButton">
            <!-- 购买按钮 -->
            <div class="boughtBotton">立即沟通</div>

            <!-- 收藏按钮 -->
            <div class="collectionBotton">收藏橱窗</div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import { defineProps } from "vue"
    import { PrimarySort } from "@/types/pageType/showcase"
    
    const props = defineProps({
        abstractInfo:{type:String, required: true},
        profile:{type:String, required: true},
        userName:{type:String, required: true},
        commentNum:{type:String, required: true},
        price:{type:String, required: true},
        primary:{type:String, required: true},
        last:{type:String, required: true},
        isIdle:{type:String, required: true},
        canQuicky:{type:String, required: true},
    });

    //标签数组
    let sortArr=[
        ["直播切片","歌曲相关","视频后期"],
        ["直播封面","视频封面","其他美术"],
        ["日语类","英语类","韩语类"],
        ["日语类","英语类","韩语类"]
    ]
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .summary{
        position: sticky;
        top: 20px;
        .title{
            display: flex;
            align-items: center;
            .text{
                font-size: 18px;
                font-weight: bold;
                color: #707070;
            }
        }

        .userInfo{
            display: flex;
            padding-bottom: 10px;
            .userProfile{
                margin-top: 10px;
                @include fixedCircle(50px);
                @include backgroundImgCondition();
            }

            .userText{
                margin-left: 5px;
                display: flex;
                flex-direction: column;
                justify-content: center;
                font-weight: bold;
                .userName{
                    font-size: 16px;
                }
                .userHonor{
                    display: flex;
                    font-size: 10px;
                    .commendNum{
                        color: #707070;
                    }
                }
            }
        }
        .tradeInfo{
            box-sizing: border-box;
            padding: 0px 5px;
            color: #707070;
            display: flex;
            flex-direction: column;
            font-size: 14px;
            >*{
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-top: 20px;
            }


            .title{
                font-weight: bold;
                color: #4a4a4a;
                font-size: 16px;
            }

            .showcaseValue{
                .value{
                    font-size: 24px;
                    color: #fb7299;
                    .moneyToken{
                        font-size: 18px;
                    }
                }
            }
        }

        .motionButton{
            display: flex;
            flex-direction: column;
            justify-content: space-around;
            margin-top: 10px;
            color: white;
            >*{
                @include fixedHeight(34px);
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 0px 20px;
                border-radius: 4px;
                margin-top: 15px;
                cursor:pointer;
                font-size: 14px;
            }
            .boughtBotton{
                background-color: #00a8e9;
            }
            .collectionBotton{
                background-color: #fb7299;
            }
        }
    }
</style>