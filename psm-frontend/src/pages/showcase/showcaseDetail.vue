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
                        <div class="userProfile" :style="`background-image: url(${`http://frp-few.top:26246/media/userProfile/default.png`});`"></div>
                        
                        <!-- 用户文字信息 -->
                        <div class="userText">
                            <!-- 用户名字 -->
                            <div class="userName">帕斯猫</div>

                            <!-- 用户荣誉 -->
                            <div class="userHonor">
                                <!-- 评论数量 -->
                                <div class="commendNum">
                                    {{ 123 }}条评论
                                </div>

                                <!-- 用户评分 -->
                                <div class="userRate">
                                    {{10.0}}
                                </div>
                            </div>
                        </div>
                    </div>

                    <hr/>
                    
                    <!-- 交易信息 -->
                    <div class="tradeInfo">
                        <!-- 橱窗价格 -->
                        <div class="showcaseValue">
                            <div class="key">橱窗价格</div>
                            <div class="value"><span class="moneyToken">￥</span>{{100}}</div>
                        </div>

                        <!-- 已售数量 -->
                        <div class="sold">
                            <div class="key">已售</div>
                            <div class="value">{{2}}件</div>
                        </div>

                        <!-- 库存 -->
                        <div class="inventory">
                            <div class="key">库存</div>
                            <div class="value">{{3}}/{{3}}</div>
                        </div>

                        <!-- 截稿日期 -->
                        <div class="deadline">
                            <div class="key">截稿时间</div>
                            <div class="value">{{"确认接单后24小时"}}</div>
                        </div>
                    </div>

                    <!-- 动作按钮 -->
                    <div class="motionButton">
                        <!-- 购买按钮 -->
                        <div class="boughtBotton">购买</div>

                        <!-- 收藏按钮 -->
                        <div class="collectionBotton">收藏</div>
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
                                .userRate{
                                    margin-left: 5px;
                                    color: #fb7299;
                                }
                            }
                        }
                    }
                    .tradeInfo{
                        box-sizing: border-box;
                        padding: 5px;
                        margin-top: 5px;
                        color: #707070;
                        display: flex;
                        flex-direction: column;
                        font-size: 16px;
                        font-weight: bold;
                        >*{
                            display: flex;
                            justify-content: space-between;
                            align-items: center;
                            .key{

                            }
                            .value{

                            }
                        }
                        .showcaseValue{
                            .key{
                                font-size: 20px;
                            }
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
                        justify-content: space-around;
                        margin-top: 35px;
                        color: white;
                        font-weight: bold;
                        >*{
                            display: flex;
                            justify-content: center;
                            padding: 0px 20px;
                            border-radius: 4px;
                        }
                        .boughtBotton{
                            background-color: #00a8e9;
                        }
                        .collectionBotton{
                            background-color: #fb7299;
                        }
                    }
                }
            }
        }
    }
</style>