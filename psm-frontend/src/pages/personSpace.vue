<template>
    <div class="personSpace">
        <div class="personAbtract">
            <div class="profile">
                <img src="images/xiProfile.jpg">
            </div>
            <div class="recomment">
                <div class="recomment-name">帕斯汐</div>
                <div class="recomment-title">个人简介:</div>
                <div class="recomment-content">加急需要提前联系 价格需要*2 特急*2.5 要发票*3。不接过于福利的封面......</div>
            </div>
            <div class="button-group">
                <div class="share">分享</div>
                <div class="edit">编辑信息</div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
    
</script>

<style lang="scss" scoped>
    @mixin fixedCircle($size){
        min-width: $size;
        max-width: $size;
        min-height: $size;
        max-height: $size;
        overflow: hidden;
        border-radius: 50%;
    }
    .personSpace{
        width: 100%;
        height: 100%;
        padding: 20px;
        display: flex;
        flex-direction: column;
        align-items: center;
        .personAbtract{
            width: 100%;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding-bottom: 15px;
            border-bottom: 1px solid rgba(165, 165, 165, 0.3568627451);
            .profile{
                @include fixedCircle(100px);
                margin-bottom: 5px;
                img{
                    width: 100%;
                    height: 100%;
                    object-fit: cover;
                }
            }
            .recomment{
                display: flex;
                flex-direction: column;
                align-items: center;
                font-size: 12px;
                color: gray;
                font-weight: bold;
                >*{
                    margin-bottom: 5px;
                }
                &-name{
                    font-size: 18px;
                    color: black;
                }
                &-title{
                }
                &-content{
                    max-width: 180px;
                }
            }
            .button-group{
                min-width: 240px;
                display: flex;
                justify-content: space-between;
                color: white;
                div{
                    cursor: pointer;
                    display: flex;
                    justify-content: center;
                    min-width: 100px;
                    max-width: 100px;
                    border-radius: 4px;
                }
                .share{
                    background-color: #fb7299;
                }
                .edit{
                    background-color: #00a8e9;
                }
            }
        }
    }
</style>