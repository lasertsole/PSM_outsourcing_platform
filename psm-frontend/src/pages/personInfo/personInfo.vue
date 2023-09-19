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
                <router-view v-slot="{ Component }">
                    <keep-alive>
                        <component :is="Component"/>
                    </keep-alive>
                </router-view>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import selectItem from "components/personInfo/selectItem.vue";
    import collectOption from "components/personInfo/collectOption.vue";
    import calendar from "components/personInfo/calendar.vue";
    import personCard from "components/personInfo/personCard.vue";
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
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
                    height: 330px;
                    border-radius: 4px;
                    background-color: white;
                    box-sizing: border-box;
                    
                    >div{
                        box-sizing: border-box;
                    }
                    >.top{
                        width: 100%;
                        height: 20%;
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
                        height: 80%;
                    }
                }
            }

            >.right{
                width: 740px;
                height: 100%;
                margin-left: 15px;
            }
        }
    }
</style>