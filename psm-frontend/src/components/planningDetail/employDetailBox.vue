<template>
    <div class="employDetail">
        <!-- 截止日期 -->
        <div class="deadLine">
            <div class="key">截止日期:</div>
            <div class="value">{{deadLine}}</div>
        </div>

        <!-- 预算金额 -->
        <div class="budget">
            <div class="key">预算金额:</div>
            <div class="value">{{ budget }}</div>
        </div>

        <!-- 应征截止日期 -->
        <div class="employFinishedTime">
            <div class="key">应征截止时间:</div>
            <div class="value">{{ employFinishedTime }}</div>
        </div>

        <!-- 应征企划按钮 -->
        <div class="employ">
            <button>应征企划</button>
        </div>

        <!-- 底下扩展 -->
        <hr/>
        <div class="extend">
            <div class="share">分享企划</div>
            <div class="back">返回列表</div>
        </div>
    </div>
</template>

<script setup lang="ts">
    const props = defineProps({
        deadLine:{type:String, required: true}, 
        budget:{type:String, required: true},
        employFinishedTime:{type:String, required: true},
    });
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .employDetail{
        color: #707070;
        margin-top: 50px;
        font-weight: bold;

        >div:not(:first-of-type),hr{
            margin-top: 5px;
        }
        .key{
            font-size: 17px;
        }
        .value{
            font-size: 14px;
            color: #00a8e9;
        }
        .employ{
            display: flex;
            justify-content: center;
            button{
                font-size: 20px;
                background-color: #00a8e9;
                color: white;
                outline: none;
                border: 0;
                padding: 2px 10px;
                width: 100%;
                display: flex;
                align-items: center;
                justify-content: center;
            }
        }

        .extend{
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            font-size: 12px;
            color: #707070;
        }
    }
</style>