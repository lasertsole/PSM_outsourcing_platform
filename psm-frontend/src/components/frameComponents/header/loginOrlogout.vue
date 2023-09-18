<template>
    <!-- 登录注册按钮 -->
    <div v-if="!isOnline" class="loginOrRegister">
        <router-link to="/loginAndRegister/login" class="login">登录</router-link>
        <router-link to="/loginAndRegister/register" class="register">注册</router-link>
    </div>
    <!-- 用户账号管理 -->
    <ul class="userTool" v-else>
        <li class="profile" @mouseenter="showDetail" @mouseleave="hideDetail">
            <img :class="{userProfile:true, show:showUserDetail}" :src="profile" @click="onClick">
            <transition 
                :css="false"
                @enter="onEnter"
                @leave="onLeave"
            >
                <div v-show="showUserDetail" :class="{userDetail:true}">
                    <div class="name" @click="onClick">{{userName}}</div>
                    <ul class="numInfo">
                        <li>
                            <div class="top">57</div>
                            <div class="bottom">关注</div>
                        </li>
                        <li>
                            <div class="top">3</div>
                            <div class="bottom">粉丝</div>
                        </li>
                    </ul>
                    <ul class="option">
                        <li><router-link to="/accountModify"><img src="icons/profile.png"><span>账户设置</span><img src="icons/arrow.svg" alt=""></router-link></li>
                        <li><router-link to="/accountModify"><img src="icons/planning.png"><span>我的企划</span><img src="icons/arrow.svg" alt=""></router-link></li>
                        <li><router-link to="/accountModify"><img src="icons/Vector.png"><span>我的橱窗</span><img src="icons/arrow.svg" alt=""></router-link></li>
                        <hr>
                        <li @click="logout">
                            <div><img src="icons/longArrow.svg"><span>退出登录</span></div>
                        </li>
                    </ul>
                </div>
            </transition>
        </li>
        <li>动态</li>
        <li>收藏</li>
        <li>历史</li>
        <li>创作中心</li>
        <li>投稿</li>
    </ul>
</template>

<script lang="ts" setup>
    import gsap from "gsap";
    import router from "router";
    import useGlobal from "global";
    import { storeToRefs } from "pinia";
    import { ref, onMounted, onUnmounted, computed, ComputedRef } from "vue";

    const global = useGlobal();

    const mainStore = global?.UserInfo;//获取用户账号信息的pinia
    const { isOnline, userProfile, userName } = storeToRefs(mainStore);

    /*显示用户信息*/
    const serverUrl:string = global?.serverUrl;//从环境变量中获取服务器地址
    const profile:ComputedRef<string> = computed(()=>{//获取头像
        return serverUrl+userProfile.value;
    })

    /*鼠标移入时展示用户细节*/
    const showUserDetail = ref<boolean>(false);
    function showDetail():void{
        isMouseInBox=true;
        if(animateLock){return};//判断动画是否锁上
        showUserDetail.value=true;
        animate?.kill();
    }

    /*鼠标移出时隐藏用户细节*/
    function hideDetail():void{
        isMouseInBox=false;
        if(animateLock){return};//判断动画是否锁上
        showUserDetail.value=false;
        animate?.kill();
    }

    /*详情盒子动画钩子*/
    let animate:any = undefined;//gsap动画容器
    let isMouseInBox:boolean = false;//鼠标是否在详情盒子中
    let animateLock:boolean = false;//动画锁
    function onEnter(el:any, done:Function):void{
        animate = gsap.to(el,{
            opacity:1,
            top: 70,
            duration: .3,//持续时间
            onStart:()=>{//开始触发函数
                animateLock=true;
            },
            onComplete:()=>{//结束触发函数
                done();
                animateLock=false;
                if(!isMouseInBox){
                    hideDetail();
                }
            }
        });
    }
    function onLeave(el:any, done:Function):void{
        animate = gsap.to(el,{
            opacity:0,
            top: 30,
            duration: .3,//持续时间
            onStart:()=>{//开始触发函数
                animateLock=true;
            },
            onComplete:()=>{//结束触发函数
                done();
                animateLock=false;
                if(isMouseInBox){
                    showDetail();
                }
            }
        });
    }

    function onClick():void{//点击触发事件
        router.replace("/personInfo");
    }

    /************挂载事件************/
    onMounted(()=>{

    });

    /************卸载事件************/
    onUnmounted(()=>{

    })

    /************退出账号事件************/
    function logout():void{
        mainStore.logOutAccount();
        hideDetail();
    }
</script>

<style lang="scss">
    @use "sass:math";
    @import "commonScss";
    
    @mixin button{
        width: 80px;
        height: 36px;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        margin: 0px 10px;
        border-radius: 4px;
        color: white;
        cursor: pointer;
    }
    .loginOrRegister{
        display: flex;
        flex-direction: row;
        .login{
            display: block;
            background-color: #fb7299;
            @include button;
        }
        .register{
            display: block;
            background-color: #00a8e9;
            @include button;
        }
    }
    .userTool{
        display: flex;
        flex-direction: row;
        font-size: 13px;
        align-items: center;
        padding: 10px;
        li{
            padding: 5px;
            border-radius: 5px;
            &.profile{
                cursor: pointer;
                background-size: 100%;
                margin-right: 20px;
                padding: 0px;
                position: relative;
                $profileSize: 35px;
                .userProfile{
                    @include fixedCircle($profileSize);
                    z-index: 2;
                    position: relative;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    transition: linear .3s;

                    &.show{
                        transform: scale(2) translateY(75%);
                    }
                }
                .userDetail{
                    opacity: 0;
                    display: block;
                    z-index: 1;
                    position: absolute;
                    background-color: white;
                    @include fixedRoundedRectangle(200px,280px, 10px);
                    top: 30px;
                    left: math.div($profileSize, 2);
                    transform: translateX(-50%);
                    padding: math.div($profileSize, 2) + 25px 20px 10px;

                    .name{
                        display: flex;
                        justify-content: center;
                        font-weight: bolder;
                    }

                    .numInfo{
                        display: flex;
                        width: 100%;
                        justify-content: space-around;
                        
                        li{
                            cursor: pointer;
                            width: 50%;

                            &:hover{
                                background-color: #f5f5f5;
                                transition: .5s ease;
                            }

                            >div{
                                display: flex;
                                justify-content: center;

                                &.top{
                                    font-size: 16px;
                                    font-weight: bolder;
                                }
                                &.bottom{
                                    font-size: 10px;
                                    font-weight: normal;
                                }
                            }
                        }
                    }

                    .option{
                        margin-top: 15px;
                        li{
                            cursor: pointer;
                            font-weight: bold;
                            display: flex;
                            justify-content: space-between;
                            align-items: center;
                            
                            a{
                                flex-grow: 1;
                            }

                            span{
                                flex-grow: 1;
                                margin-left: 10px;
                            }

                            &:hover{
                                background-color: #f5f5f5;
                                transition: .5s ease;
                            }

                            div,a{
                                font-weight: inherit;
                                color: black;
                                display: flex;
                                align-items: center;
                                span{}

                                img{
                                    @include fixedSquare(15px);
                                }
                            }
                        }
                    }

                    hr{
                        margin: 10px 0px;
                    }
                }
            }
        }
    }
    @media screen and (max-width: 600px) {
        .loginOrRegister{
            display: none;
        }
        .userTool{
            display: none;
        }
    }
</style>