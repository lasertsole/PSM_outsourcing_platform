<template>
    <div class="personInfo">
        <div class="container">
            <div class="left">
                <div class="personCard">
                    <div class="top">
                        <personCard></personCard>
                    </div>
                    <div class="hr"><div class="line"></div></div>
                    <div class="bottom">
                        <selectItem></selectItem>
                    </div>
                </div>

                <collectOption></collectOption>

                <calendar></calendar>
            </div>
            <div class="right">
                <myPlanning></myPlanning>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import { ref, computed, onMounted, onUnmounted, toRefs } from "vue";
    import useGlobal from "@/global";
    import { storeToRefs } from "pinia";
    import selectItem from "@/components/personInfo/selectItem.vue";
    import collectOption from "@/components/personInfo/collectOption.vue";
    import calendar from "@/components/personInfo/calendar.vue";
    import myPlanning from "@/components/personInfo/myPlanning.vue";
    import personCard from "@/components/personInfo/personCard.vue";

    const global = useGlobal();
    const serverUrl:string = global?.serverUrl;//从环境变量中获取服务器地址

    const mainStore = global?.UserInfo;//获取用户账号信息的pinia
    const { userinfo } = storeToRefs(mainStore);

    const {userName, userProfile} = toRefs(userinfo.value);//从pinia中获取用户头像数据和用户名字
    const profile = ref<string>(serverUrl+userProfile.value);
    
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "@/common.scss";
    .personInfo{
        width: 100%;
        height: 100%;
        padding: 30px 80px 0px 80px;
        display: flex;
        justify-content:  center;
        overflow-y: auto;
        box-sizing: border-box;
        background-color: #ededed;
        >.container{
            width: 1000px;
            height: 100%;
            display: flex;
            justify-content: space-between;

            >.left{
                width: 250px;
                height: 100%;
                >.personCard{
                    width: 100%;
                    height: 350px;
                    border-radius: 4px;
                    background-color: white;
                    box-sizing: border-box;
                    
                    >div{
                        box-sizing: border-box;
                    }
                    >.top{
                        width: 100%;
                        height: 30%;
                        display: flex;
                        padding: 10px 20px 10px 20px;
                    }
                    >.hr{
                        height: 0.5px;
                        padding: 0px 20px;
                        .line{
                            height: 100%;
                            background-color: lightgray;
                        }
                    }
                    >.bottom{
                        width: 100%;
                        height: 70%;
                    }
                }
            }

            >.right{
                width: 740px;
                height: 100%;
                margin-left: 10px;
            }
        }
    }
</style>