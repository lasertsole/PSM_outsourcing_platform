<template>
    <div class="accountModify">
        <ul class="infoBox">
            <li>
                <span class="optionName">昵称</span>
                <input class="optionValue" :value="userName" disabled>
                <span class="modifyButton">修改</span>
            </li>
            <li>
                <span class="optionName">手机号</span>
                <input class="optionValue" :value="userPhoneNumber" disabled>
                <span class="modifyButton">修改</span>
            </li>
            <li>
                <span class="optionName">登录密码</span>
                <input class="optionValue" :value="111111" disabled type="password">
                <span class="modifyButton">修改</span>
            </li>
        </ul>
    </div>
</template>

<script setup lang="ts">
    import useGlobal from "global";
    import { storeToRefs } from "pinia";

    const global = useGlobal();
    const mainStore = global?.UserInfo;//获取用户账号信息的pinia
    const { userProfile, userName, userPhoneNumber } = storeToRefs(mainStore);

    console.log(userPhoneNumber.value);

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
                display: flex;
                justify-content: space-between;
                align-items: center;
                font-size:12px;
                &:not(:last-child){
                    border-bottom: 1px solid #ececec;
                }

                .optionName{
                    min-width: 20%;
                    font-size: 14px;
                }

                .optionValue{
                    color: #9b9b9b;
                    flex-grow: 1;
                    border: none;
                    outline: none;
                    background-color: rgba(0,0,0,0);
                }

                .modifyButton{
                    display: inline-block;
                    color: #00a8e9;
                }
            }
        }
    }
</style>