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
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import { computed, ComputedRef } from "vue";
    import useGlobal from "global";
    import { storeToRefs } from "pinia";

    const global = useGlobal();
    const serverUrl:string = global?.serverUrl;//从环境变量中获取服务器地址

    const accountInfo = global?.accountInfo;//获取用户账号信息的pinia

    const { userName, userProfile } = storeToRefs(accountInfo);//从pinia中获取用户头像数据和用户名字

    const profile:ComputedRef<string> = computed(()=>{
        return serverUrl+userProfile.value;
    })
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
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
                    @include fixedCircle(48px);
                    background-color: white;
                }
            }
            >.right{
                margin-left: 8px;
                display: flex;
                flex-direction: column;

                >.top{
                    display: flex;
                    flex-direction: column;

                    >span{
                        font-size: 14px;
                    }
                    >.transformRule{
                        color: #00a8e9;
                        font-weight: bold;
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