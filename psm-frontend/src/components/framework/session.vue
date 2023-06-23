<template>
    <div :class="{session:true, hide:hideContacts, fullPage:fullPage}">
        <div @click="clickShowContacts" class="header">
            <div class="left">私信</div>
            <div class="right">
                <img :class="{down:!hideContacts}" src="icons/sessionArrow.svg">
            </div>
        </div>
        <div class="body">
            <ul class="contacts">
                <li @click="changeFullPage">
                    <img class="profile" src="icons/eatBean.svg"/>
                    <div class="info">
                        <div class="top">
                            <div class="name">汉偶武1</div>
                            <div class="time">2021/9/3</div>
                        </div>
                        <div class="bottom">你能不能超越计划啊(气宇轩昂)(普信能量爆满)</div>
                    </div>
                </li>
                <li>
                    <img class="profile" src="icons/eatBean.svg"/>
                    <div class="info">
                        <div class="top">
                            <div class="name">汉偶武2</div>
                            <div class="time">2021/9/3</div>
                        </div>
                        <div class="bottom">你不要忘记了期末考试的时候</div>
                    </div>
                </li>
            </ul>
            <div class="communication">
                <ul :class="{content:true,show:fullPage}">
                    <sessionBox v-for="item in chatArr"
                        :isMe="item.isMe"
                        :name="item.name"
                        :profile="item.profile"
                        :time="item.time"
                        :content="item.content"
                    ></sessionBox>
                </ul>
                <textarea class="input" maxlength="200" @click="sendMessage"></textarea>
            </div>
        </div>
    </div>
</template>

<script lang="ts" setup>
    import { ref } from 'vue';
    import useGlobal from "global";
    import { storeToRefs } from "pinia";
    import { sessionInfo } from "types/pageType/session"
    import sessionBox from 'components/frameComponents/session/sessionBox.vue';

    const global = useGlobal();
    const mainStore = global?.UserInfo;
    const { userinfo } = storeToRefs(mainStore);

    const hideContacts = ref<Boolean>(true);//控制隐藏和显示联系人
    function clickShowContacts():void{//点击私聊栏顶部时向上显示联系人
        hideContacts.value=!hideContacts.value;
        if(fullPage.value==true){
            fullPage.value=false;
        }
    }

    const fullPage = ref<Boolean>(false);//控制私聊框全屏
    function changeFullPage():void{//点击某个联系人时全屏
        fullPage.value=true;
    }

    /**************以下是获取聊天记录**************/
    let WS = global?.WS;
    const { WSConnect } = storeToRefs(WS);
    function sendMessage():void{//点击某个联系人时全屏
        // WSConnect.value.send(`{
        //     "type": "Chat",
        //     "msg": [
        //         {
        //             "time": "2023-06-01 11:56:14",
        //             "user_id": 2,
        //             "data": [
        //                 {
        //                     "type": "text",
        //                     "text": "123"
        //                 },
        //                 {
        //                     "type": "face",
        //                     "id": "156"
        //                 }
        //             ]
        //         }
        //     ]
        // }`);
        WSConnect.value.send(`{"user_id":2,"type":"getChatInfo"}`);
    }

    /**************以上是获取聊天记录**************/

    const chatArr:sessionInfo[] =[
        {
            isMe:false,
            name:"汉偶武1",
            profile:"images/MOYA.jpg",
            time:"2021/9/3/23:30",
            content:"你能不能超越计划啊?你能不能超越计划啊?你能不能超越计划啊?你能不能超越计划啊?你能不能超越计划啊?你能不能超越计划啊?你能不能超越计划啊?"
        },
        {
            isMe:true,
            name:"汉偶武2",
            profile:"images/MOYE.jpg",
            time:"2021/9/3/23:31",
            content:"哼，也许吧!哼，也许吧!哼，也许吧!哼，也许吧!哼，也许吧!哼，也许吧!哼，也许吧!哼，也许吧!哼，也许吧!哼，也许吧!哼，也许吧!哼，也许吧!"
        },
        {
            isMe:false,
            name:"汉偶武1",
            profile:"images/MOYA.jpg",
            time:"2021/9/3/23:31",
            content:"你不要忘记了！期末考试的时候！你不要忘记了！期末考试的时候！你不要忘记了！期末考试的时候！你不要忘记了！期末考试的时候！你不要忘记了！期末考试的时候！"
        },
    ] 
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "@/common.scss";
    .session{
        display: flex;
        flex-direction: column;
        position: fixed;
        box-sizing: border-box;
        bottom: 0px;
        right: 20px;
        z-index: 1;
        height: 400px;
        width: 264px;
        box-sizing: border-box;
        border-bottom: 2px ;
        background-color: white;
        border-radius: 5px;
        transition: all .3s linear;
        overflow: hidden;
        &.hide{
            transform: translateY(calc(100% - 44px));
        }

        &.fullPage{
            width: 700px;
            right: 20px;
        }
        .header{
            display: flex;
            justify-content: space-between;
            cursor: pointer;
            padding: 10px 0px 10px 10px;
            border-bottom: 1px white solid;
            background-color: #00aeec;
            color: white;
            @include fixedRetangle(100%, 44px);
            .left{
                font-weight: bold;
            }
            .right{
                display: flex;
                align-items: center;
                margin-right: 10px;
                img{
                    @include fixedSquare(17px);
                    &.down{
                        transform: rotate(180deg);
                    }
                }
            }
        }
        .body{
            flex-grow: 1;
            height: 388px;
            display: flex;
            flex-direction: row;
            justify-content: flex-start;
            .contacts{
                @include fixedWidth(264px);
                overflow-y: auto;
                padding: 10px;
                width: 100%;
                box-sizing: border-box;
                li{
                    display: flex;
                    flex-direction: row;
                    justify-content: flex-start;
                    align-items: center;
                    height: 70px;
                    box-sizing: border-box;
                    padding: 10px;
                    cursor: pointer;
                    transition: background-color .3s ease-in-out;
                    &:hover{
                        background-color: rgba($color: #000000, $alpha: .2);
                    }
                    .profile{
                        width: 35px;
                        height: 35px;
                        border-radius: 50%;
                        box-sizing: border-box;
                    }
                    .info{
                        margin-left: 10px;
                        display: flex;
                        flex-direction: column;
                        justify-content: space-between;
                        overflow: hidden;
                        .top{
                            display: flex;
                            flex-direction: row;
                            justify-content: space-between;
                        }
                        .bottom{
                            width: 100%;
                            display: block;
                            text-overflow: ellipsis; /* 文本溢出时显示省略号来代表被修剪的文本 */
                            white-space: nowrap; /* 段落中的文本不进行换行 */
                            overflow: hidden; /* 溢出部分隐藏 */
                        }
                    }
                }
            }
            .communication{
                @include fixedHeight(440px);
                display: flex;
                flex-direction: column;
                border-left: 2px #d4d4d4 solid;
                .content{
                    @include fixedHeight(250px);
                    flex-grow: 1;
                    overflow-y: scroll;
                    overflow-x: hidden;
                    max-width: 0px;
                    transition: max-width .3s linear;
                    padding: 5px;
                    box-sizing: border-box;
                    &.show{
                        max-width: 435px;
                        display: block;
                    }
                    &::-webkit-scrollbar {
                        width: 4px;
                        border-radius: 10px;
                        background: white;
                    }

                    &::-webkit-scrollbar-thumb {
                        border-radius: 10px;
                        background: rgba($color: #000000, $alpha: .2);
                    }
                }
                .input{
                    @include fixedHeight(190px);
                    box-sizing: border-box;
                    resize:none;
                    outline: none;
                    overflow-y: auto;
                    border: none;
                    border-top: 2px #d4d4d4 solid;
                    background-color: transparent;
                }
            }
        }
    }
</style>