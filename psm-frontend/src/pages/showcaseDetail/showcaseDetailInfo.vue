<template>
    <div class="content">
        {{ article }}
    </div>
</template>

<script setup lang="ts">
    import {ref} from "vue";

    const article = ref<string>(`
    安好:擅长古民值男/各种图组少女灶。
    [请说明一下外或年龄偏好 同人大欢连!
不接:真人J纯文宇设/大叔/正太 车队(等不起反馈是一款我的问题)
张吉成增的间只有营单背看1带点水彩的厚流《西古风设定会更水影一点)笔教多且乱，请确本可以楼受例围啦!!!《走价了会比双朝编节一表表:封面上面两张对比下面两张这样)
9连胜:色脑照理不大改! 不大改!不大改!有要说! !谢市谢谢谢谢谢
喜欢的设定会多画一点我是升梅使)跌认复杂的设定可以赁化，或餐+30
请给获清明确的无据条的立脸! !! 者能看的参!  谢谢谢谢谢谢谢谢谢谢`);
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .content{
        flex-grow: 1;
        padding:10px;
        display: flex;
        flex-direction: column;
    }
</style>
