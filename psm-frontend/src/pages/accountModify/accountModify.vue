<template>
    <div class="accountModify">
        <ul class="infoBox">

            <li>
                <div class="show">
                    <span class="optionName">昵称</span>
                    <div class="optionValue">
                        <el-input v-model="temptUserName" placeholder="请输入昵称" clearable :disabled="modifyIndex!=0"/>
                    </div>
                    <span class="modifyButton" @click="modifyIndex=0" v-show="modifyIndex!=0">修改</span>
                </div>
                <transition 
                    :css="false"
                    @enter="onEnter"
                    @leave="onLeave"
                >
                    <div class="hide" v-show="modifyIndex==0">
                        <button class="save" @click="modifyUserName()">保存</button>
                        <span class="cancel" @click="modifyIndex=-1">取消</span>
                    </div>
                </transition>
            </li>

            <li>
                <div class="show">
                    <span class="optionName">手机号</span>
                    <div class="optionValue">
                        <el-input v-model="temptUserPhoneNumber" placeholder="请输入手机号" clearable :disabled="modifyIndex!=1"/>
                    </div>
                    <span class="modifyButton" @click="modifyIndex=1" v-show="modifyIndex!=1">修改</span>
                </div>
                <transition 
                    :css="false"
                    @enter="onEnter"
                    @leave="onLeave"
                >
                    <div class="hide" v-show="modifyIndex==1">
                        <button class="save" @click="modifyUserPhoneNumber()">保存</button>
                        <span class="cancel" @click="modifyIndex=-1">取消</span>
                    </div>
                </transition>
            </li>

            <li>
                <div class="show">
                    <span class="optionName">登录密码</span>
                    <div class="optionValue">
                        <el-input v-model="temptPassword" placeholder="请输入新的登录密码" clearable type="password" :disabled="modifyIndex!=2"/>
                    </div>
                    <span class="modifyButton" @click="modifyIndex=2" v-show="modifyIndex!=2">修改</span>
                </div>
                <transition
                    :css="false"
                    @enter="onEnter"
                    @leave="onLeave"
                >
                    <div class="hide" password v-show="modifyIndex==2">
                        <div class="identifyPassword">
                            <span class="optionName">登录密码</span>
                            <el-input v-model="identifyPassword" placeholder="确认新的登录密码" clearable type="password" v-show="modifyIndex==2"/>
                        </div>
                        <div>
                            <button class="save" @click="modifyUserPassword()">保存</button>
                            <span class="cancel" @click="modifyIndex=-1">取消</span>
                        </div>
                    </div>
                </transition>
            </li>

        </ul>
    </div>
</template>

<script setup lang="ts">
    import gsap from "gsap";
    import useGlobal from "global";
    import { storeToRefs } from "pinia";
    import { ElMessage } from 'element-plus';
    import { ref, onMounted, onUnmounted, watch } from "vue";

    const global = useGlobal();
    const accountInfo = global?.accountInfo;//获取用户账号信息的pinia
    const { userProfile, userName, userPhoneNumber } = storeToRefs(accountInfo);
    const Bus = global?.Bus;

    /**以下为展开合上盒子时的动画特效**/
    /*详情盒子动画钩子*/
    let animate:any = undefined;//gsap动画容器
    let isMouseInBox:boolean = false;//鼠标是否在详情盒子中
    let animateLock:boolean = false;//动画锁

    function onEnter(el:any, done:Function):void{
        animate = gsap.to(el,{
            opacity:1,
            duration: .3,//持续时间
            onStart:()=>{//开始触发函数
                animateLock=true;
            },
            onComplete:()=>{//结束触发函数
                done();
                animateLock=false;
            }
        });
    }

    function onLeave(el:any, done:Function):void{
        animate = gsap.to(el,{
            opacity:0,
            duration: 0,//持续时间
            onStart:()=>{//开始触发函数
                animateLock=true;
            },
            onComplete:()=>{//结束触发函数
                done();
                animateLock=false;
            }
        });
    }
    /**以上为展开合上盒子时的动画特效**/


    /**以下为修改信息部分**/
    const temptUserName = ref<string>("");//临时用户名
    const temptUserPhoneNumber = ref<string>("");//临时用户手机号
    const temptPassword = ref<string>("1111");//临时用户手机号
    const identifyPassword = ref<string>("");
    const modifyIndex = ref<number>(-1);

    watch(modifyIndex,(newValue, oldValue)=>{
        temptUserName.value = userName.value;
        temptUserPhoneNumber.value = userPhoneNumber.value;
        if(newValue!=2){
            temptPassword.value = "******";
        }
        else{
            temptPassword.value = "";
        }
        identifyPassword.value = "";
    })

    async function modifyUserName():Promise<void>{
        if(temptUserName.value.length == 0){
            ElMessage.error("名字不能为空");  
        }
        else if(temptUserName.value.length<3||temptUserName.value.length>13){
            ElMessage.error("请输入大于等于3且小于等于15长度的名字");   
        }
        else if(temptUserName.value == userName.value){
            ElMessage.error("修改后的名字不能与原来的一样");
        }
        else{
            let isOk = await accountInfo.changeUserName(temptUserName.value);
            if(isOk){
                temptUserName.value = userName.value;
                modifyIndex.value = -1;
            }
        }
    }

    async function modifyUserPhoneNumber():Promise<void>{
        if(temptUserPhoneNumber.value.length == 0){
            ElMessage.error('手机号不能为空');
        }
        else if(!/^1[0-9]{10}$/.test(temptUserPhoneNumber.value)){
            ElMessage.error('无效手机号');
        }
        else if(temptUserPhoneNumber.value == userPhoneNumber.value){
            ElMessage.error("修改后的手机号不能与原来的一样");
        }
        else{
            let isOk = await accountInfo.changeUserPhoneNumber(temptUserPhoneNumber.value);
            if(isOk){
                temptUserPhoneNumber.value = userPhoneNumber.value;
                modifyIndex.value = -1;
            }
        }
    }

    async function modifyUserPassword():Promise<void>{
        if(temptPassword.value.length<6){
            ElMessage.error('密码过短');
        }
        else if(temptPassword.value.length>=15){
            ElMessage.error('密码过长');
        }
        else if(temptPassword.value != identifyPassword.value){
            ElMessage.error('密码和确认密码不一致');
        }
        else{
            let isOk = await accountInfo.changeUserPassword(temptPassword.value);
            if(isOk){
                temptUserPhoneNumber.value = "******";
                modifyIndex.value = -1;
            }
        }
    }
    /**以上为修改信息部分**/
    
    /**********挂载触发*********/
    onMounted(()=>{
        temptUserName.value = userName.value;//临时用户名
        temptUserPhoneNumber.value = userPhoneNumber.value;//临时用户手机号
        temptPassword.value = "******";//临时用户手机号
        Bus.on("login",()=>{ 
            temptUserName.value = userName.value;//临时用户名
            temptUserPhoneNumber.value = userPhoneNumber.value;//临时用户手机号
            temptPassword.value = "******";//临时用户手机号
        });
    })

    /**********卸载触发*********/
    onUnmounted(()=>{
        Bus.off("login");
    })

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
            @include fixedWidth(500px);
            min-width: 350px;
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
                    }

                    .modifyButton{
                        display: inline-block;
                        color: #00a8e9;
                        cursor: pointer;
                    }
                }

                .hide{
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    margin-top: 10px;
                    opacity: 0;

                    &[password]{
                        flex-direction: column;
                        
                        .identifyPassword{
                            display: flex;
                            width: 100%;
                            justify-content: flex-start;
                            margin-top: 0px;

                            .optionName{
                                min-width: 20%;
                                font-size: 14px;
                                opacity: 0;
                            }
                        }
                    }

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

                &::v-deep(.el-input){
                    @include fixedRetangle(200px, 21px);
                    
                    &:not(:first-child){
                        margin-bottom: 10px;
                    }

                    &.is-disabled{
                        .el-input__wrapper{
                            background-color: inherit;
                            box-shadow: none;
                        }
                    }
                }
            }
        }
    }
</style>