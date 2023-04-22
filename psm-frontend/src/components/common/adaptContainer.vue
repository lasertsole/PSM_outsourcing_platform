<template>
    <div class="container" ref="container">
        <template v-for="item in boxNum">
            <slot :name="`${item}`"></slot>
        </template>
        <div class="fixBox" v-for="item in needFixBox"></div>
    </div>
</template>

<script setup lang="ts">
    import { ref, defineProps, onMounted, onUnmounted, computed } from 'vue';
    const props = defineProps({boxNum:{type:Number, required: true}, boxWidth:{type:Number, required: true}, boxHeight:{type:Number, required: true}}); 

    /*************计算fixBox样式属性*************/
    const computedBoxWidth = computed(()=>{
        return props.boxWidth+"px";
    })
    const computedBoxHeight = computed(()=>{
        return props.boxHeight+"px";
    })

    /*************当屏幕大小变化时填充空盒子实现布局自适应*************/
    const container = ref<any>();
    let boxWidth:number = props.boxWidth;//单个橱窗盒子的外部宽度
    let boxNum:number = props.boxNum;//总盒子个数
    const needFixBox = ref<number>(0);//需要填补的盒子个数

    function fixBoxes(){//用于计算需要填充的盒子个数
        /*动态获取container结点的内部宽度*/
        let containerInnerWidth = parseInt(getComputedStyle(container.value).width)-parseInt(getComputedStyle(container.value).paddingLeft)-parseInt(getComputedStyle(container.value).paddingRight);
        let boxesEachRow = Math.floor(containerInnerWidth/boxWidth);//动态计算出每行能容下多少个盒子
        let rowsholder=Math.ceil(boxNum/boxesEachRow);//动态计算出所有盒子需要占用多少行
        if(boxNum<boxesEachRow){//如果已有盒子总数小于每行能容下的盒子个数，则不用填充空盒子
            needFixBox.value=0;
        }
        else{
            needFixBox.value=rowsholder*boxesEachRow-boxNum;//动态计算出需要多少个盒子来填充
        }
    }

    /****************************挂载触发****************************/
    onMounted(()=>{
        fixBoxes();
        window.addEventListener("resize",fixBoxes,false);//绑定监听屏幕大小变化事件
    })
    /****************************卸载解绑****************************/
    onUnmounted(()=>{
        window.removeEventListener("resize",fixBoxes);//解绑监听屏幕大小变化事件
    })
</script>

<style lang="scss" scoped>
    @mixin fixedBox($widthSize, $heightSize){
        min-width: $widthSize;
        max-width: $widthSize;
        min-height: $heightSize;
        max-height: $heightSize;
    }
    .container{
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: row;
        justify-content: center;
        flex-wrap: wrap;
        .fixBox{//填充盒子
            box-sizing: border-box;
            $boxWidth: v-bind(computedBoxWidth);
            $boxHeight: v-bind(computedBoxHeight);
            @include fixedBox($boxWidth, $boxHeight);
        }
    }
</style>