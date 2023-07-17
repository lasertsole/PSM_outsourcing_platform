<template>
    <div class="personInfoBox">
        <!-- 用户头像 -->
        <div class="userProfile" :style="`background-image: url(${userProfile});`"></div>
        
        <!-- 用户名 -->
        <div class="userName">{{ userName }}</div>

        <!-- 用户评分 -->
        <div class="userScore">{{ userScore }}</div>

        <!-- 用户评论数 -->
        <div class="commentNum">评论数:{{ commentNum }}</div>

        <!-- 用户完成率 -->
        <div class="compleleRate">完成率:{{ compleled }}/{{ totalDeal }}</div>
    </div>
</template>

<script setup lang="ts">
    const props = defineProps({
        userProfile:{type:String, required: true}, 
        userName:{type:String, required: true},
        userScore:{type:Number, required: true},
        commentNum:{type:Number, required: true},
        compleled:{type:Number, required: true},
        totalDeal:{type:Number, required: true},
    });
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .personInfoBox{
        @include fixedRetangle(100%, 200px);
        display: flex;
        flex-direction: column;
        align-items: center;

        >div:not(:first-of-type){
            margin-top: 5px;
        }
        .userProfile{
            @include fixedCircle(50px);
            @include backgroundImgCondition();
        }
        .userName{
            font-size: 17px;
            font-weight: 500;
        }
        .userScore{
            font-size: 12px;
            font-weight: bold;
            color: #fb7299;
        }
        .commentNum,.compleleRate{
            color: #707070;
            font-size: 12px;
            font-weight: bold;
        }
    }
</style>