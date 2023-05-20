<template>
    <div class="personCard">
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
</template>

<script setup lang="ts">
    import { ref, toRefs } from "vue";
    import useGlobal from "@/global";
    import { storeToRefs } from "pinia";

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
    .personCard{
        width: 100%;
        height: 100%;
        box-sizing: border-box;
        display: flex;
        flex-direction: column;
        .top{
            width: 100%;
            height: 65%;
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
            height: 35%;
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
</style>