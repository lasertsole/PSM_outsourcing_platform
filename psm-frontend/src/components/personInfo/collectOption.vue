<template>
    <ul class="collectOption">
        <li>
            <div>
                <img src="icons/drawerCollect.png" class="collectIcon"/>
                <div class="collectName">画师收藏</div>
            </div>
        </li>
        <li class="center">
            <div>
                <img src="icons/worksCollect.png" class="collectIcon"/>
                <div class="collectName">作品收藏</div>
            </div>
        </li>
        <li>
            <div>
                <img src="icons/showcaseCollect.png" class="collectIcon"/>
                <div class="collectName">橱窗收藏</div>
            </div>
        </li>
    </ul>
</template>

<script setup lang="ts">
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .collectOption{
        background-color: white;
        display: flex;
        font-size: 12px;
        margin-top: 20px;
        align-items: center;
        padding: 15px;
        box-sizing: border-box;
        @include fixedRoundedRectangle(100%, 100px, 4px);

        li{
            @include fixedWidth(math.div(100%,3));
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 10px;
            box-sizing: border-box;
            color: gray;
            @include fixedHeight(70px);
            cursor: pointer;
            >div{
                display: flex;
                flex-direction: column;
                align-items: center;
                .collectIcon{
                    @include fixedSquare(38px);
                }

                .collectName{

                }
            }

            &.center{
                padding: 10px 0px;
                display: flex;
                align-items: center;
                justify-content: space-between;
                &::after,&::before{
                    content: "";
                    @include fixedRetangle(0.5px, 30px);
                    background-color: lightgray;
                }
            }
        }
    }
</style>