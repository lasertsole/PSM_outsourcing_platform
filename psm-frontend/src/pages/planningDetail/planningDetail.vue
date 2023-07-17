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
            <detailBox>
            </detailBox>

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
    import detailBox from "components/planningDetail/detailBox.vue";
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
            @include fixedRetangle(200px, 505px);
            >div{
                background-color: white;
                border-radius: 8px;
                overflow: hidden;
                padding: 20px;
            }
        }
        .rightBar{
            @include fixedRetangle(900px, 505px);
            display: flex;
            flex-direction: column;
            justify-content: space-between;

            >div{
                background-color: white;
                border-radius: 8px;
                padding: 10px;
            }
        }
    }
</style>