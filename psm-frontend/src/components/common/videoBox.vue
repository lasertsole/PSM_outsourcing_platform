<template>
    <div class="videoBox"
        @mouseenter="playVideo"
        @mouseleave="resetVideo"
        @mousemove="changeVideoProgress"
    >
        <img :src="imgPath">
        <video 
            :src="videoPath"
            ref="video"
            preload="false"
        >
        </video>
    </div>
</template>

<script setup lang="ts">
    import { ref, defineProps, onMounted } from "vue"
    const props = defineProps({imgPath:String, videoPath:String});

    const video = ref();//获取视频dom

    /**以下是视频dom控制方法**/
    function playVideo():void{//鼠标放入时播放
        if(video.value&&video.value.networkState==1){//video标签挂载完成并且视频加载完成时
            video.value.play()
        }
    }

    function resetVideo():void{//鼠标移除时重置
        if(video.value&&video.value.networkState==1){//video标签挂载完成并且视频加载完成时
            video.value.currentTime = 0;
            video.value.pause();
        }
    }

    function changeVideoProgress(event:any):void{//改变视频播放进度
        if(video.value&&video.value.networkState==1){//video标签挂载完成并且视频加载完成时
            let videoDom = video.value;
            let videoDomWidth = videoDom.getBoundingClientRect().width;
            let biasLeft = event.clientX-videoDom.getBoundingClientRect().left;//鼠标相对于dom的位置
            if(biasLeft<0){resetVideo();}//限制偏差范围
            else if(biasLeft>videoDomWidth){resetVideo();}
            video.value.currentTime = biasLeft/videoDomWidth*video.value.duration;
        }
    }
</script>
  
<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .videoBox{
        @include fullInParent();
        position: relative;
        img{
            @include fullInParent();
            position: absolute;
            object-fit: cover;
            z-index: 1;
            &:hover{
                transition: .3s ease-out;
                opacity: 0;
            }
        }
        video{
            @include fullInParent();
            position: absolute;
            object-fit: cover;
            z-index: 0;
        }
    }
</style>