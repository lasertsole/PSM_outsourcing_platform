<template>
    <div class="videoControllBox"
        @mouseenter="mouseenterVideoBox"
        @mouseleave="mouseleavevideoBox"
        ref="videoControllBox"
    >
        <transition 
            :css="false"
            @enter="onEnter"
            @leave="onLeave"
        >
            <img v-show="mockShow" :src="imgPath">
        </transition>
        <video 
            :src="videoPath"
            :poster="imgPath"
            ref="video"
            preload="false"
            controls
            controlslist="nodownload"
            oncontextmenu = "return false"
        >
        </video>
    </div>
</template>

<script setup lang="ts">
    import { ref, defineProps, defineExpose, onMounted } from "vue"
    import gsap from "gsap";

    const props = defineProps({imgPath:String, videoPath:String});

    /*以下为控制动画效果*/
    function mouseenterVideoBox():void{//鼠标移入视频控制盒子事件
        if(loadDone){
            mockShow.value = false;
        }
    }

    function mouseleavevideoBox():void{//鼠标移出视频控制盒子事件
        if(videoDom.ended){
            mockShow.value = true;
        }
    }

    let animate:any = undefined;//gsap动画容器
    const mockShow=ref<boolean>(true);//控制遮盖显示与否
    let loadDone:boolean = false;//视频是否已经加载完成
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
    /*以上为控制动画效果*/
    
    const video = ref();//获取视频dom
    let videoDom:any;
    onMounted(()=>{
        videoDom=video.value;
        videoDom.oncanplay = function(){
            loadDone=true;
        };

        videoDom.addEventListener('play',function(){
            hadPlay=true;
        });

        videoDom.addEventListener('ended',function(){
            hadPlay=false;//刷新播放状态
            mockShow.value=true;
        });
    });

    const videoControllBox = ref();//获取视频控制盒子的dom
    defineExpose({
        video,
        videoControllBox
    });//将视频标签暴露出去
</script>
  
<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .videoControllBox{
        @include fullInParent();
        position: relative;
        img{
            @include fullInParent();
            position: absolute;
            object-fit: cover;
            z-index: 1;
            transition: .3s ease-out;
            opacity: 1;
        }
        video{
            @include fullInParent();
            position: absolute;
            object-fit: cover;
            z-index: 0;
        }
    }
</style>