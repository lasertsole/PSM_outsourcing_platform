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
            <!-- 右上详情盒子 -->
            <div class="detail">
                <div class="title">设计一个背景墙</div>

                <div class="hr"></div>

                <div class="selectInfoBox">
                    <div>企划发布时间：2023年3月10日</div>
                    <div>封面/美工</div>
                </div>
                <div class="step">
                    <div class="planningStep">企划流程</div>
                    <el-steps :active="5" align-center>
                        <el-step title="开始合作" description="0%" />
                        <el-step title="草稿阶段" description="30%" />
                        <el-step title="初步定稿" description="50%" />
                        <el-step title="中期确定" description="70%" />
                        <el-step title="确认收稿" description="100%" />
                    </el-steps>
                </div>
            </div>

            <!-- 右下应征人员盒子 -->
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
        </div>
    </div>
</template>

<script setup lang="ts">
    import { ref } from "vue";
    import useGlobal from "global";
    import { storeToRefs } from "pinia";
    import { useRoute } from "vue-router";
    
    interface itemBoxInfo{
        name:string,
        profile:string,
        commentNum:number,
        certificate:string,
    }

    /**获取全局变量**/
    const global = useGlobal();

    /**获取获取用户信息**/
    const mainStore = global?.UserInfo;//获取用户账号信息的pinia
    const { userinfo } = storeToRefs(mainStore);
    const serverUrl:string = global?.serverUrl;//从环境变量中获取服务器地址

    /**获取获取用户信息**/
    const infoArr = ref<itemBoxInfo[]>([
        {
            name: "帕斯猫",
            profile: "images/psmProfile.jpg",
            commentNum: 114,
            certificate: "日语N1认证",
        },
    ])

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
            @include fixedRetangle(900px, 510px);
            display: flex;
            flex-direction: column;
            justify-content: space-between;

            >div{
                background-color: white;
                border-radius: 8px;
                padding: 10px;
            }
            .detail{
                @include fixedRetangle(900px, 360px);
                display: flex;
                flex-direction: column;
                .title{
                    color: #444444;
                    font-weight: bold;
                    padding: 5px 0px;
                }
                .hr{
                    @include fixedHeight(1.5px);
                    background-color: #cbcbcb;
                }
                .selectInfoBox{
                    display: flex;
                    font-size: 12px;
                    color: #707070;
                    font-weight: bold;
                    >div{
                        margin-right: 50px;
                        padding: 2px 0px;
                    }
                }
            }
            .step{
                .planningStep{
                    color: #00a8e9;
                    font-weight: bold;
                    font-size: 18px;
                }
            }

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
        }
    }
</style>