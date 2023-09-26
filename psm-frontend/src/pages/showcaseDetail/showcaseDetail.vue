<template>
    <div class="showcaseDetail"
        ref="rootDom"
        @mousewheel="controlPictureInpicture"
    >
        <div class="page">
            <!-- 左栏 -->
            <div class="leftBar">
                <div class="runningVideo"
                    ref="videoControllBoxDom"
                >
                    <xgplayerOfVideo
                        v-if="result"
                        :imgPath="serverUrl+params?.imgPath"
                        :videoPath="serverUrl+params?.videoPath"
                        :PIPController="PIPController"
                    >
                    </xgplayerOfVideo>
                </div>
                <div class="tabBar">
                    <tabBar :tabList="tabList"></tabBar>
                    <div class="report">举报橱窗</div>
                </div>
                <div class="detailBox">
                    <router-view v-slot="{ Component }">
                        <keep-alive>
                            <component :is="Component"/>
                        </keep-alive>
                    </router-view>
                </div>
            </div>

            <!-- 右栏 -->
            <div class="rightBar">
                <!-- 橱窗概要 -->
                <div class="summary">
                    <!-- 橱窗标题 -->
                    <div class="title">
                        <span class="text">{{ params?.abstractInfo }}</span>
                    </div>

                    <!-- 用户信息 -->
                    <div class="userInfo">
                        <!-- 用户头像 -->
                        <div class="userProfile" :style="`background-image: url(${serverUrl+params?.profile});`"></div>
                        
                        <!-- 用户文字信息 -->
                        <div class="userText">
                            <!-- 用户名字 -->
                            <div class="userName">{{ params?.userName }}</div>

                            <!-- 用户荣誉 -->
                            <div class="userHonor">
                                <!-- 评论数量 -->
                                <div class="commendNum">
                                    {{ params?.commentNum }}条评论
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

                        <!-- 截稿日期 -->
                        <div class="deadline">
                            <div class="key">截稿时间</div>
                            <div class="value">{{"确认接单后24小时"}}</div>
                        </div>
                    </div>

                    <!-- 动作按钮 -->
                    <div class="motionButton">
                        <!-- 购买按钮 -->
                        <div class="boughtBotton">立即沟通</div>

                        <!-- 收藏按钮 -->
                        <div class="collectionBotton">收藏橱窗</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import { ref, onMounted } from "vue"
    import useGlobal from "@/global";
    import { storeToRefs } from "pinia";
    import { useRoute } from "vue-router";
    import { tabBarItem } from "@/types/common/tabBarType"
    import xgplayerOfVideo from "@/components/common/xgplayerOfVideo.vue";
    import tabBar from "@/components/common/tabBar.vue"

    /**获取全局变量**/
    const global = useGlobal();

    /**获取获取用户信息**/
    const mainStore = global?.UserInfo;//获取用户账号信息的pinia
    const { userinfo } = storeToRefs(mainStore);
    const showcaseInfo = global?.showcaseInfo;//用户状态信息
    const serverUrl:string = global?.serverUrl;//从环境变量中获取服务器地址

    /**获取路由传参**/
    const route = useRoute();

    const params = ref<any>();
    const result = ref<any>();
    async function getShowcaseBoxDetail(){
        result.value = await showcaseInfo.getShowcaseBoxDetail(route.query.ID);
        if(result.value){
            params.value = result.value;
        }
    }

    /**挂载时请求数据**/
    onMounted(()=>{
        getShowcaseBoxDetail();
    });

    /**二级路由切换**/
    const tabList = ref<tabBarItem[]>([
        {tabName:"橱窗详情",linkTo:"/showcaseDetail/showcaseDetailInfo"},
        {tabName:"创作阶段",linkTo:"/showcaseDetail/createPhase"},
        {tabName:"橱窗评价",linkTo:"/showcaseDetail/commendOfShowcase"},
    ]);

    /**以下为鼠标滚动事件**/
    const videoControllBoxDom = ref();//获取视频控制盒子的dom
    const rootDom = ref();//页面根节点的dom
    let root:any;
    let videoControl:any;
    let videoHeight:number;
    let videoOffsetHeight:number;
    let remoteBetween:number;

    onMounted(()=>{
        root = rootDom.value;
        videoControl = videoControllBoxDom.value;//初始化视频控制盒子dom
        videoHeight = videoControl.getBoundingClientRect().height;
        videoOffsetHeight = videoControl.getBoundingClientRect().y+videoHeight;
        remoteBetween = videoOffsetHeight-root.getBoundingClientRect().y;
    })

    const PIPController = ref<boolean>(false);//画中画控制器
    function controlPictureInpicture():void{//当视频在播放时向下拉可进入画中画模式
        if(root.scrollTop>remoteBetween){
            PIPController.value=true;
            console.log(PIPController.value);
        }
        else{
            PIPController.value=false;
            console.log(PIPController.value);
        }
    }

</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";

    .showcaseDetail{
        width: 100%;
        min-height: 100%;
        background-color: white;
        $paddingCol: 30px;
        padding: $paddingCol 80px $paddingCol 80px;
        display: flex;
        flex-wrap: wrap;//使page填满整个容器的关键属性
        justify-content: center;
        overflow: scroll;
        box-sizing: border-box;
        .page{
            @include fixedWidth(1000px);
            padding: 20px;
            display: flex;
            justify-content: space-between;

            .leftBar{
                @include fixedRetangle(60%, 100%);
                .runningVideo{
                    height: 300px;
                }
                .tabBar{
                    background-color: white;
                    margin-top: 20px;
                    display:flex;
                    flex-direction: column;
                    position: sticky;
                    top: -$paddingCol;
                    &::v-deep(.classify){
                        position: relative;
                        z-index: 1;
                    }
                    .report{
                        position: absolute;
                        z-index: 3;
                        display: inline-block;
                        right: 5px;
                        bottom: 12px;
                        font-size: 12px;
                        color: #b3b3b3;
                        font-weight: bold;
                        cursor: pointer;
                    }
                }
                .detailBox{
                    @include fixedRetangle(100%, 1000px);
                    display: flex;
                    flex-direction: column;
                }
            }

            .rightBar{
                @include fixedRetangle(35%, 100%);

                .summary{
                    position: sticky;
                    top: 20px;
                    .title{
                        display: flex;
                        align-items: center;
                        .text{
                            font-size: 18px;
                            font-weight: bold;
                            color: #707070;
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
                        flex-direction: column;
                        justify-content: space-around;
                        margin-top: 10px;
                        color: white;
                        >*{
                            @include fixedHeight(34px);
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            padding: 0px 20px;
                            border-radius: 4px;
                            margin-top: 15px;
                            cursor:pointer;
                            font-size: 14px;
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