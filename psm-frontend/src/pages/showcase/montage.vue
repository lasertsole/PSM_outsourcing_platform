<template>
    <div class="montage">
        <filterBar></filterBar>
        <div class="itemBoxContainer">
            <template v-for="item in infoArr">
                <itemBox
                    :name = "item.name"
                    :profile = "item.profile"
                    :commentNum = "item.commentNum"
                    :certificate = "item.certificate"
                    :recomment = "item.recomment"
                    :works = "item.works"
                >
                </itemBox>
            </template>
        </div>
    </div>
</template>

<script setup lang="ts">
    import {ref} from "vue"
    import { itemBoxInfo } from "@/types/componentsType/itemBoxComponent";
    import filterBar from "@/components/indexPage/filterBar.vue"
    import itemBox from "@/components/indexPage/itemBox.vue";

    const infoArr = ref<itemBoxInfo[]>([
        {
            name: "帕斯猫",
            profile: "images/psmProfile.jpg",
            commentNum: 114,
            certificate: "日语N1认证",
            recomment: "加急需要提前联系 价格需要*2 特急*2.5 要发票*3",
            works:[
                {abstract:"直播歌切 带普轴", price:"200-300", imgPath:"Carousel/bg-1.jpg"},
                {abstract:"直播歌切 带普轴", price:"400-500", imgPath:"Carousel/bg-2.jpg"}
            ]
        },
        {
            name: "筱曌汐",
            profile: "images/xiProfile.jpg",
            commentNum: 114,
            certificate: "日语N1认证",
            recomment: "加急需要提前联系 价格需要*2 特急*2.5 要发票*3",
            works:[
                {abstract:"直播歌切 带普轴", price:"200-300", imgPath:"Carousel/bg-3.jpg"},
                {abstract:"直播歌切 带普轴", price:"400-500", imgPath:"Carousel/bg-1.jpg"}
            ]
        },
    ])
</script>

<style lang="scss" scoped>
    .montage{
        padding: 10px;
    }
</style>