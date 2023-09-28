<template>
    <div class="showcaseDetail"
        ref="rootDom"
        @scroll="controlPictureInpicture"
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

                <div class="tabBar" ref="tabBarDiv">
                    <tabBar 
                        :tabList="tabList"
                        @changeClassifyIndex="changeClassifyIndex"
                        :focusIndex="classifyIndex"
                    ></tabBar>
                    <div class="report">举报橱窗</div>
                </div>
                <div class="detailBox" ref="detailBox">
                    <showcaseDetailInfo :info="params?.mainInfo&&JSON.parse(params.mainInfo).detail"></showcaseDetailInfo>
                    <createPhase :info="params?.mainInfo&&JSON.parse(params.mainInfo).phrase"></createPhase>
                    <commendOfShowcase :article="`2`"></commendOfShowcase>
                </div>
            </div>

            <!-- 右栏 -->
            <div class="rightBar">
                <summaryInfo
                    v-if="params"
                    :abstractInfo="params?.abstractInfo"
                    :profile="serverUrl+params?.profile"
                    :userName="params?.userName"
                    :commentNum="params?.commentNum"
                    :price="params?.price"
                    :primary="params?.primarySort"
                    :last="params?.lastSort"
                    :isIdle="params?.isIdle"
                    :canQuicky="params?.canQuicky"
                >
                </summaryInfo>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import { ref, onMounted, watch } from "vue";
    import useGlobal from "@/global";
    import { storeToRefs } from "pinia";
    import { useRoute } from "vue-router";
    import { tabBarItem } from "@/types/common/tabBarType";
    import xgplayerOfVideo from "@/components/common/xgplayerOfVideo.vue";
    import tabBar from "@/components/common/tabBar.vue";
    import type { TabsPaneContext } from 'element-plus'

    import showcaseDetailInfo from "@/components/showcaseDetail/showcaseDetailInfo.vue";
    import createPhase from "@/components/showcaseDetail/createPhase.vue";
    import commendOfShowcase from "@/components/showcaseDetail/commendOfShowcase.vue";
    import summaryInfo from "@/components/showcaseDetail/summaryInfo.vue";

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
    async function getShowcaseBoxDetail():Promise<any>{
        result.value = await showcaseInfo.getShowcaseBoxDetail(route.query.ID);
        if(result.value){
            params.value = result.value;
        }
    }

    /**挂载时请求数据**/
    let promise:Promise<void>;
    promise = getShowcaseBoxDetail();//向服务器请求数据

    /**以下为鼠标滚动事件**/
    const videoControllBoxDom = ref<HTMLElement>();//获取视频控制盒子的dom
    const rootDom = ref<HTMLElement>();//页面根节点的dom
    let root:any;
    const scrollTopNum = ref<number>(0);//记录root滚动距离
    let videoControl:any;
    let videoHeight:number;
    let videoOffsetHeight:number;
    let remoteBetween:number;

    onMounted(()=>{
        promise.then(()=>{
            root = rootDom.value;
            videoControl = videoControllBoxDom.value;//初始化视频控制盒子dom
            videoHeight = videoControl.getBoundingClientRect().height;
            videoOffsetHeight = videoControl.getBoundingClientRect().y+videoHeight;
            remoteBetween = videoOffsetHeight-root.getBoundingClientRect().y;
        });
    })

    const PIPController = ref<boolean>(false);//画中画控制器
    function controlPictureInpicture():void{//当视频在播放时向下拉可进入画中画模式
        scrollTopNum.value=root.scrollTop;
        if(scrollTopNum.value>remoteBetween){
            PIPController.value=true;
        }
        else{
            PIPController.value=false;
        }
    }

    const tabList = ref<tabBarItem[]>([
        {tabName:"橱窗详情",index:0},
        {tabName:"创作阶段",index:1},
        {tabName:"橱窗评价",index:2},
    ]);
    const detailBox = ref<HTMLElement>();
    const tabBarDiv = ref<HTMLElement>();
    let detailBoxDom:HTMLElement;//详情信息栏的dom
    let tabBarDom:HTMLElement;//切页栏的dom
    let detailBoxChildrenDoms:NodeList;//detailBoxDom的子dom列表
    let childrenDomsRemoteTopList:number[]=[];//记录子dom列表的锚定距离

    onMounted(()=>{
        promise.then(()=>{
            if(tabBarDiv.value instanceof HTMLElement){
                tabBarDom=tabBarDiv.value;
                if(detailBox.value instanceof HTMLElement){
                    detailBoxDom=detailBox.value;
                    detailBoxChildrenDoms=detailBoxDom.childNodes;
                    detailBoxChildrenDoms.forEach((item, index)=>{
                        childrenDomsRemoteTopList.push((<any>item).getBoundingClientRect().y-tabBarDom.getBoundingClientRect().height-root.getBoundingClientRect().y);
                    })
                }
            }

            watch(scrollTopNum, (newValue, oldValue)=>{
                if(newValue<childrenDomsRemoteTopList[1]){
                    classifyIndex.value = 0;
                }
                else if(newValue<childrenDomsRemoteTopList[2]){
                    classifyIndex.value = 1;
                }
                else{
                    classifyIndex.value = 2;
                }
            })
        });
    })
    
    const classifyIndex = ref<number>(0);
    function changeClassifyIndex(index: number):void{
        root.scrollTop = childrenDomsRemoteTopList[index];
        controlPictureInpicture();
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
                    z-index: 10;
                    top: -$paddingCol - 1px;
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
                    overflow: hidden;
                    z-index: 5;
                    position: sticky;
                }
            }

            .rightBar{
                @include fixedRetangle(35%, 100%);
            }
        }
    }
</style>