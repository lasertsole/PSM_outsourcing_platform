<template>
    <div class="commend">
        <div class="top">
            <div class="left">
                <div class="profile" ></div>
                <div class="name">{{ userName }}</div>
            </div>
            <div class="right">
                <div class="score">
                    <el-rate
                        v-model="starScore"
                        disabled
                    />
                </div>
            </div>
        </div>
        <div class="bottom">
            <div class="top">
                <div class="text">
                    {{ text }}
                </div>
            </div>
            <div class="bottom">
                <div class="date">
                    {{ date }}
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import { ref, defineProps } from "vue"

    const props = defineProps({
        userID:{type:String, required:true},
        userProfile:{type:String, required:true},
        userName:{type:String, required:true},
        score:{type:Number, required:true},
        date:{type:String, required:true},
        text:{type:String, required:true},
    });

    const starScore = ref<number>(props.score)
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .commend{
        display: flex;
        flex-direction: column;
        >.top{
            @include fixedHeight(50px);
            display: flex;
            justify-content: space-between;
            align-items: center;
            >.left{
                display: flex;
                align-items: center;
                >.profile{
                    @include fixedCircle(50px);
                    background-image:url("images/MOYE.jpg");
                    background-size: 100%;
                }
                >.name{
                    margin-left: 7px;
                    font-weight: bolder;
                    font-size: 18px;
                }
            }
            >.right{
                >.score{
                }
            }
        }
        >.bottom{
            margin-top:10px;
            color:#707070;
            padding-bottom:2px;
            >.top{
                >.text{
                    font-weight: bold;
                    font-size: 14px;
                }
            }
            >.bottom{
                margin-top: 5px;
                >.date{
                    display: flex;
                    font-size: 12px;
                }
            }
        }
    }
</style>
