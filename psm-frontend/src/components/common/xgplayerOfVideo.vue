<template>
    <div :class="{videoBox:true, PIPController}"
        @mouseenter="mouseenterVideoBox"
        @mouseleave="mouseleavevideoBox"
        @mousedown="PIPDrag"
        ref="videoControllBox"
    >
        <transition 
            :css="false"
            @enter="onEnter"
            @leave="onLeave"
        >
            <div class="coverImg" v-show="mockShow" :style="`background-image:url(${imgPath})`"></div>
        </transition>
        <div class="videoContainer">
            <div ref="videoPlayer"></div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import gsap from "gsap";
    import Player from 'xgplayer';
    import 'xgplayer/dist/index.min.css';
    import { ref, onMounted, defineProps } from "vue";

    const props = defineProps({imgPath:String, videoPath:String, PIPController:Boolean});

    /*以下为控制动画效果*/
    function mouseenterVideoBox():void{//鼠标移入视频控制盒子事件
        if(player.state==4||player.ended){
            mockShow.value = false;
        }
    }

    function mouseleavevideoBox():void{
        if(player.ended||player.state==4){
            mockShow.value = true;
        }
    }

    let animate:any = undefined;//gsap动画容器
    const mockShow=ref<boolean>(true);//控制遮盖显示与否
    let hadPlay:boolean=false;//是否已经播放过
    function onEnter(el:any, done:Function):void{
        if(!hadPlay){
            animate = gsap.to(el,{
                opacity:1,
                duration: .3,//持续时间
                onStart:()=>{//开始触发函数
                },
                onComplete:()=>{//结束触发函数
                    done();
                }
            });
        }
    }
    function onLeave(el:any, done:Function):void{
        animate = gsap.to(el,{
            opacity:0,
            duration: .3,//持续时间
            onStart:()=>{//开始触发函数
            },
            onComplete:()=>{//结束触发函数
                done();
            }
        });

    }

    const video = ref();//获取视频dom
    let videoDom:any;
    /*以上为控制动画效果*/

    const videoPlayer = ref<any>();
    let player:any;//存储播放器的控制器
    onMounted(()=>{
        const config = {
            "el": videoPlayer.value,
            "url": props.videoPath,
            "playsinline": true,
            "poster": props.imgPath,
            "plugins": [],
            "playbackRate": [
                0.5,
                1,
                1.25,
                1.5,
                2
            ],
            "cssFullscreen":false,
            "volume": 1
        }

        player  = new Player(config);
    });

    /**以下是画中画拖拽部分**/
    const videoControllBox = ref<HTMLElement>();
    let videoControllBoxDom:HTMLElement;

    const boxRight = ref<string>("50px");//画中画距离盒子右边距离
    const boxBottom = ref<string>("50px");//画中画距离盒子底端距离
    
    onMounted(()=>{
        if(videoControllBox.value instanceof HTMLElement){
            videoControllBoxDom=videoControllBox.value;
        }
    });

    function PIPDrag(event:any):void{//拖拽视频盒子
        if(props.PIPController){
            event = event || window.event;
            let al:number = event.clientX - videoControllBoxDom.offsetLeft; //求出鼠标到盒子距离
            let at:number = event.clientY - videoControllBoxDom.offsetTop; //求出鼠标到盒子距离
            let biasLeft:number;
            let biasTop:number;
            let windowWidth = (window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth);
            let windowHeight = (window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight);
            document.onmousemove = function(event){
                event = event || window.event;
                biasLeft = event.clientX - al;
                biasTop = event.clientY - at ;
                boxRight.value = windowWidth - biasLeft - parseInt(getComputedStyle(videoControllBoxDom).width) + "px";
                boxBottom.value = windowHeight - biasTop - parseInt(getComputedStyle(videoControllBoxDom).height) + "px";
            }

            document.onmouseup = function(){
                //取消事件
                this.onmousemove = null; 
                this.onmouseup = null;
            }
        }
    }
    /**以上是画中画拖拽部分**/
</script>
  
<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .videoBox{
        @include fullInParent();
        position: relative;
        .coverImg{
            @include fullInParent();
            position: absolute;
            z-index: 1;
            object-fit: cover;
            background-size: 100% 100%;
        }
        
        .videoContainer{
            @include fullInParent();
            position: absolute;
            z-index: 0;
            &::v-deep(.xgplayer){
                @include fullInParent();
                video{
                    @include fullInParent();
                    object-fit: cover;
                }

                .xgplayer-poster{
                    @include fullInParent();
                    object-fit: cover;
                    background-size: 100% 100%;
                }
            }
        }

        &.PIPController{
            position: fixed;
            z-index: 10;
            $boxWidth: 320px;
            $boxHeight: 180px;
            right: v-bind(boxRight);
            bottom: v-bind(boxBottom);
            @include fixedRetangle($boxWidth, $boxHeight);

            .xgplayer-poster{
                @include fullInParent();
                object-fit: contain;
            }
        }
    }
</style>