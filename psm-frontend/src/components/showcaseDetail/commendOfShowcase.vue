<template>
    <div class="contentArr">
        <div class="topBar">
            <div class="icon"></div>
            <span>橱窗评价</span> 
        </div>
        <template v-for="(item,index) in commendArr">
            <comment
                :userID="item.userID"
                :userProfile="item.userProfile"
                :userName="item.userName"
                :score="item.score"
                :date="item.date"
                :text="item.text"
            ></comment>
        </template>
    </div>
</template>

<script setup lang="ts">
    import comment from "components/showcaseDetail/commend.vue";
    import { showcaseDetailBox } from "types/pageType/showcaseDetail"
    import { ref } from "vue";

    const commendArr = ref<showcaseDetailBox[]>([
        {
            userID:"112233",
            userProfile:"images/psmlogo.png",
            userName:"MOYE",
            score:5,
            date:"2023-05-23",
            text:"又快又美，吹爆！！"
        },
        {
            userID:"112233",
            userProfile:"images/psmlogo.png",
            userName:"MOYE",
            score:5,
            date:"2023-05-23",
            text:"又快又美，吹爆！！"
        },
        {
            userID:"112233",
            userProfile:"images/psmlogo.png",
            userName:"MOYE",
            score:5,
            date:"2023-05-23",
            text:"又快又美，吹爆！！"
        },
        {
            userID:"112233",
            userProfile:"images/psmlogo.png",
            userName:"MOYE",
            score:5,
            date:"2023-05-23",
            text:"又快又美，吹爆！！"
        },
        {
            userID:"112233",
            userProfile:"images/psmlogo.png",
            userName:"MOYE",
            score:5,
            date:"2023-05-23",
            text:"又快又美，吹爆！！"
        },
        {
            userID:"112233",
            userProfile:"images/psmlogo.png",
            userName:"MOYE",
            score:5,
            date:"2023-05-23",
            text:"又快又美，吹爆！！"
        }
    ]);
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .contentArr{
        .topBar{
            height: 20px;
            box-sizing: content-box;
            padding-bottom: 20px;
            margin-bottom: 20px;
            font-size: 16px;
            font-weight: bold;
            border-bottom: 1px solid #ececec;
            display: flex;
            justify-content: flex-start;
            align-items: center;
            color: #4a4a4a;

            .icon{
                @include fixedSquare(20px);
                background-image: url(icons/showcaseComment.svg);
                background-size: 100%;
                background-position: center;
                margin-right: 10px;
            }
        }

        flex-grow: 1;
        padding:10px;
        display: flex;
        flex-direction: column;
        
        &::v-deep(.commend:not(:last-of-type)){
            margin-bottom: 13px;
            border-bottom: 1px solid lightgray;
        }
    }

</style>
