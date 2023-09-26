<template>
    <div :class="{videoBox:true, PIPController}"
        @mouseenter="mouseenterVideoBox"
        @mouseleave="mouseleavevideoBox"
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
    import { ref, onMounted, defineProps, defineExpose } from "vue";

    const props = defineProps({imgPath:String, videoPath:String, PIPController:Boolean});

    /*以下为控制动画效果*/
    function mouseenterVideoBox():void{//鼠标移入视频控制盒子事件
        if(player.state==4||player.ended){
            mockShow.value = false;
        }
    }

    function mouseleavevideoBox():void{//鼠标移出视频控制盒子事件
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

    const videoControllBox = ref();//获取视频控制盒子的dom
    defineExpose({
        videoControllBox
    });//将视频标签暴露出去
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
            right: 100px;
            bottom: 100px;
            @include fixedRetangle(320px, 180px);

            .xgplayer-poster{
                @include fullInParent();
                object-fit: contain;
            }
        }
    }
</style>