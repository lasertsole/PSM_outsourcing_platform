<template>
    <div class="showcaseDetail">
        <div class="page">
            <!-- 左栏 -->
            <div class="leftBar">

            </div>

            <!-- 右栏 -->
            <div class="rightBar">
                <!-- 橱窗概要 -->
                <div class="summary">

                    <!-- 橱窗标题 -->
                    <div class="title">
                        <span class="text">虚拟主播直播封面</span>
                        <span class="tap">美工</span>
                    </div>

                    <!-- 用户信息 -->
                    <div class="userInfo">
                        <!-- 用户头像 -->
                        <div class="userProfile" :style="`background-image: url(${serverUrl+userinfo.userProfile});`"></div>
                        
                        <!-- 用户文字信息 -->
                        <div class="userText">
                            <!-- 用户名字 -->
                            <div class="userName">帕斯猫</div>

                            <!-- 用户荣誉 -->
                            <div class="userHonor"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import useGlobal from "@/global";
    import { storeToRefs } from "pinia";
    import { useRoute } from "vue-router";

    /**获取全局变量**/
    const global = useGlobal();

    /**获取获取用户信息**/
    const mainStore = global?.UserInfo;//获取用户账号信息的pinia
    const { userinfo } = storeToRefs(mainStore);
    const serverUrl:string = global?.serverUrl;//从环境变量中获取服务器地址

    /**获取路由传参**/
    const route = useRoute();
    console.log(route.query);
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "@/common.scss";
    .showcaseDetail{
        @include fixedRetangle(100%, 100%);
        background-color: #ededed;
        padding: 30px 80px 0px 80px;
        display: flex;
        justify-content: center;
        .page{
            @include fixedRetangle(1000px, 100%);
            background-color: white;
            padding: 20px;
            display: flex;
            justify-content: space-between;

            .leftBar{
                @include fixedRetangle(55%, 100%);
                background-color: red;
            }

            .rightBar{
                @include fixedRetangle(40%, 100%);

                .summary{
                    .title{
                        display: flex;
                        align-items: center;
                        .text{
                            font-size: 18px;
                            font-weight: bold;
                            color: #707070;
                        }

                        .tap{
                            font-size: 12px;
                            font-weight: bold;
                            color: #00aee0;
                            padding: 0px 5px;
                            margin-left: 10px;
                            border: #00aee0 1px solid;
                            background-color: cyan;
                            border-radius: 4px;
                        }
                    }

                    .userProfile{
                        margin-top: 10px;
                        @include fixedCircle(50px);
                        @include backgroundImgCondition();
                    }

                    .userText{

                    }
                }
            }
        }
    }
</style>