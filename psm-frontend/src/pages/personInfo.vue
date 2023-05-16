<template>
    <div class="personInfo">
        <div class="container">
            <div class="left">
                <div class="personCard">
                    <div class="top">
                        <div class="top">
                            <div class="left">
                                <img class="profile" :src="profile"/>
                            </div>
                            <div class="right">
                                <div class="top">
                                    <span class="transformRule">企划方</span>
                                    <span>{{ userName }}</span>
                                </div>
                                <div class="bottom">
                                    <span class="gray">切换为画师</span>
                                </div>
                            </div>
                        </div>
                        <div class="bottom">
                            <span class="gray">今日剩余邀请次数</span>
                            <span class="times">10 / 10</span>
                        </div>
                        
                    </div>
                    <div class="hr"><div class="line"></div></div>
                    <div class="bottom">
                        <selectItem></selectItem>
                    </div>
                </div>

                <collectOption></collectOption>

                <calendar></calendar>
            </div>
            <div class="right"></div>
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
            background-color: aqua;
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
                        flex-direction: column;
                        padding: 20px 20px 10px 20px;

                        .top{
                            width: 100%;
                            height: 40%;
                            display: flex;
                            >.left{
                                >.profile{
                                    @include fixedCircle(50px);
                                    background-color: white;
                                }
                            }
                            >.right{
                                margin-left: 8px;
                                display: flex;
                                flex-direction: column;
                                >.top{
                                    >span{
                                        font-size: 14px;
                                        font-weight: bold;
                                    }
                                    >.transformRule{
                                        color: #00a8e9;
                                        margin-right: 10px;
                                    }
                                }
                                >.bottom{
                                    >.gray{
                                        color: lightgray;
                                        font-size: 12px;
                                    }
                                }
                            }
                        }
                        .bottom{
                            width: 100%;
                            height: 60%;
                            display: flex;
                            justify-content: space-between;
                            align-items: flex-end;
                            padding-top: 5px;
                            >.gray{
                                color: lightgray;
                                font-size: 12px;
                                margin-top: 4px;
                            }
                            >.times{
                                color: #00a8e9;
                                font-size: 16px;
                            }
                        }
                        
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
                background-color: orange;
                width: 740px;
                height: 100%;
            }
        }
    }
</style>