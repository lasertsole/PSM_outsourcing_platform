<template>
    <div class="showcaseDetail">
        <div class="leftBar">
            <!-- 左上个人信息盒子 -->
            <div class="personInfoBox">
                <!-- 用户头像 -->
                <div class="userProfile" :style="`background-image: url(${serverUrl+userinfo.userProfile});`"></div>
                
                <!-- 用户名 -->
                <div class="userName">{{ userinfo.userName }}</div>

                <!-- 用户评分 -->
                <div class="userScore">{{ 9.6 }}</div>

                <!-- 用户评论数 -->
                <div class="commentNum">评论数:{{ 13 }}</div>

                <!-- 用户完成率 -->
                <div class="compleleRate">完成率:{{ 10 }}/{{ 10 }}</div>
            </div>
            
            <!-- 左下企划价格盒子 -->
            <div class="planningDetail">
                <!-- 截止日期 -->
                <div class="deadLine">
                    <div class="key">截止日期:</div>
                    <div class="value">2023年5月12日</div>
                </div>

                <!-- 预算金额 -->
                <div class="budget">
                    <div class="key">预算金额:</div>
                    <div class="value">200-500元</div>
                </div>

                <!-- 应征截止日期 -->
                <div class="employTime">
                    <div class="key">应征截止时间:</div>
                    <div class="value">2023年4月10日</div>
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
        </div>

        <div class="rightBar">

        </div>
    </div>
</template>

<script setup lang="ts">
    import useGlobal from "global";
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
    @import "commonScss";
    .showcaseDetail{
        @include fixedRetangle(100%, 100%);
        background-color: #ededed;
        padding: 30px 80px 0px 80px;
        display: flex;
        justify-content: space-between;
        .leftBar{
            @include fixedRetangle(200px, 100%);
            >div{
                background-color: white;
                border-radius: 8px;
                overflow: hidden;
                padding: 20px;
            }
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

            .planningDetail{
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
                        font-weight: bolder;
                        background-color: #00a8e9;
                        color: white;
                        outline: none;
                        border: 0;
                        padding: 5px 10px;
                        width: 100%;
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
        }
        .rightBar{
            
        }
    }
</style>