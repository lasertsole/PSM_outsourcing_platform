<template>
    <div class="accountModify">
        <ul class="infoBox">
            <li>
                <div class="show">
                    <span class="optionName">昵称</span>
                    <div class="optionValue">
                        <el-input v-model="userName" placeholder="请输入昵称" clearable/>
                    </div>
                    <span :class="{ modifyButton: true, collasp: modifyIndex==0 }" @click="modifyIndex=0">修改</span>
                </div>
                <div :class="{ hide:true, collasp: modifyIndex!=0 }">
                    <button class="save">保存</button>
                    <span class="cancel" @click="modifyIndex=-1">取消</span>
                </div>
            </li>
            <li>
                <div class="show">
                    <span class="optionName">手机号</span>
                    <div class="optionValue">
                        <el-input v-model="userPhoneNumber" placeholder="请输入手机号" clearable/>
                    </div>
                    <span :class="{ modifyButton: true, collasp: modifyIndex==1 }" @click="modifyIndex=1">修改</span>
                </div>
                <div :class="{hide:true, collasp: modifyIndex!=1}">
                    <button class="save">保存</button>
                    <span class="cancel" @click="modifyIndex=-1">取消</span>
                </div>
            </li>
            <li>
                <div class="show">
                    <span class="optionName">登录密码</span>
                    <div class="optionValue">
                        <el-input v-model="password" placeholder="请输入新的登录密码" clearable type="password" show-password/>
                    </div>
                    <span :class="{ modifyButton: true, collasp: modifyIndex==2 }" @click="modifyIndex=2">修改</span>
                </div>
                <div :class="{hide:true, collasp: modifyIndex!=2}">
                    <button class="save">保存</button>
                    <span class="cancel" @click="modifyIndex=-1">取消</span>
                </div>
            </li>
        </ul>
    </div>
</template>

<script setup lang="ts">
    import { ref } from "vue";
    import useGlobal from "global";
    import { storeToRefs } from "pinia";

    const global = useGlobal();
    const mainStore = global?.UserInfo;//获取用户账号信息的pinia
    const { userProfile, userName, userPhoneNumber } = storeToRefs(mainStore);
    const password = ref<string>("1234");

    const modifyIndex = ref<number>(-1);

    function modifyUserName():void{

    }

    function modifyUserPhoneNumber():void{

    }

    function modifyUserPassword():void{

    }
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .accountModify{
        @include fullInParent();
        background-color: #e6eaee;
        display: flex;
        justify-content: center;
        align-items: center;

        .infoBox{
            @include fixedWidth(50%);
            min-width: 400px;
            background-color: white;
            border-radius: 7px;
            display: flex;
            flex-direction: column;

            li{
                padding: 30px;
                &:not(:last-child){
                    border-bottom: 1px solid #ececec;
                }

                .show{
                    display: flex;
                    justify-content: space-between;
                    align-items: flex-start;
                    font-size:12px;

                    .optionName{
                        min-width: 20%;
                        font-size: 14px;
                    }

                    .optionValue{
                        flex-grow: 1;
                        &::v-deep(.el-input){
                            @include fixedRetangle(200px, 21px);
                        }
                    }

                    .modifyButton{
                        display: inline-block;
                        color: #00a8e9;
                        cursor: pointer;
                        &.collasp{
                            display: none;
                        }
                    }
                }

                .hide{
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    margin-top: 10px;

                    &.collasp{
                        display: none !important;
                    }

                    .save{
                        @include fixedWidth(150px);
                        background-color: #00a8e9;
                        color: white;
                        border-radius: 4px;
                        font-size: 18px;
                        padding: 7px 24px;
                        outline: none;
                        border: none;

                        &:hover{
                            background-color: #33b9ed;
                            transition: .3s linear;
                        }
                    }

                    .cancel{
                        color: #00a8e9;
                        padding: 12px 10px;
                        font-size: 14px;
                        cursor: pointer;
                    }
                }

            }
        }
    }
</style>