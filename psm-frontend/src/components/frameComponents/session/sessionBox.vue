<template>
    <li :class="{sessionBox:true, isMe:isMe}">
        <div class="top">
            <div class="name">{{name}}</div>
            <div class="time">{{time}}</div>
        </div>
        <div class="bottom">
            <div class="profile" :style="`background-image: url(${profile});`"></div>
            <div class="chatContent">{{content}}</div>
        </div>
    </li>
</template>

<script lang="ts" setup>
    import { ref, defineProps, onMounted, onUnmounted } from "vue";
    const props = defineProps({isMe:Boolean, name:String, profile:String, time:String, content:String});//从父组件传值到本组件
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .sessionBox{
        margin-bottom: 10px;
        max-width: 426px;
        min-width: 426px;
        display: flex;
        flex-direction: column;
        overflow: hidden;
        .top{
            display: flex;
            justify-content: space-between;
            font-size: 14px;
            color: blue;
            .name{
            }
            .time{
            }
        }
        .bottom{
            display: flex;
            flex-direction: row;
            .profile{
                @include fixedCircle(40px);
                background-position: center;
                background-repeat: no-repeat;
                background-size: 100%;
                object-fit: cover;
                margin-left: 3px;
            }
            .chatContent{
                max-width: 100%;
                background-color: #00aeec;
                margin-left: 20px;
                color: white;
                position: relative;
                border-radius: 5px;
                line-height: 40px;
                padding: 0px 10px;
                &:before{
                    content: "";
                    display: block;
                    border-top: 5px solid transparent;
                    border-bottom: 5px solid transparent;
                    border-right: 12px solid #00aeec;
                    position: absolute;
                    top: 16px;
                    left: -11px;
                }
                &:after{
                    content: "";
                    display: none;
                    border-top: 5px solid transparent;
                    border-bottom: 5px solid transparent;
                    border-left: 12px solid #00aeec;
                    position: absolute;
                    top: 16px;
                    right: -11px;
                }
            }
        }

        &.isMe{
            direction:rtl;
            .bottom{
                .profile{
                    margin-left: 0px;
                    margin-right: 3px;
                }
                .chatContent{
                    background-color: #00aeec;
                    margin-left: 0px;
                    margin-right: 20px;
                    &:before{
                        display: none;
                    }
                    &:after{
                        display: block;
                    }
                }
            }
        }
    }
</style>