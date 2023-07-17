<template>
    <div class="showcaseDetail">
        <div class="leftBar">
            <!-- 左上个人信息盒子 -->
            <personInfoBox
                :userProfile="serverUrl+userinfo.userProfile"
                :userName="userinfo.userName?userinfo.userName:''"
                :userScore="9.6"
                :commentNum="13"
                :compleled="10"
                :totalDeal="10"
            >
            </personInfoBox>
            
            <!-- 左下企划价格盒子 -->
            <employDetailBox
                :deadLine="`2023年5月12日`"
                :budget="`200-500元`"
                :employFinishedTime="`2023年4月10日`"
            >
            </employDetailBox>
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
            <competitorBox
                :infoArr="infoArr"
            >
            </competitorBox>
        </div>
    </div>
</template>

<script setup lang="ts">
    import personInfoBox from "components/planningDetail/personInfoBox.vue";
    import employDetailBox from "components/planningDetail/employDetailBox.vue";
    import competitorBox from "components/planningDetail/competitorBox.vue";
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
                    font-size: 18px;
                }
                &::v-deep(.el-steps){
                    padding-top: 10px;
                    .el-step__icon{
                        @include fixedSquare(15px);
                        .el-step__icon-inner{
                            font-size: 8px;
                        }
                    }
                    .el-step__main{
                        
                        .el-step__description{
                            position: absolute;
                            top: -10px;
                            left: 50%;
                            transform: translateX(-50%);
                        }
                    }
                }
            }
        }
    }
</style>