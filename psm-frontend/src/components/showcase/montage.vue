<template>
    <div class="baseNode">
        <filterBar
            :bigClass="bigClass"
            :smallClass="smallClass"
            @changeClassifyOption="changeClassifyOption"
        ></filterBar>
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
    import useGlobal from "global";
    import { storeToRefs } from "pinia";
    import { LastSort } from "@/types/pageType/showcase";
    import { optionInfo } from "@/types/pageType/showcase";
    import itemBox from "@/components/showcase/itemBox.vue";
    import filterBar from "@/components/showcase/filterBar.vue";
    import { itemBoxInfo } from "@/types/componentsType/itemBoxComponent";
    import { ref, defineProps, PropType, onMounted, onUnmounted } from "vue";

    /**********获取父组件传来的变量*********/
    const props = defineProps({
        primarySort:{type:Number, required: true}
    });

    /**********获取全局变量*********/
    const global = useGlobal();
    const showcaseInfo = global?.showcaseInfo;//用户状态信息

    //过滤器选项
    const bigClass:optionInfo[] = [
        {
            label: '直播切片',
            value: 0,
        },
        {
            label: '歌曲相关',
            value: 1,
        },
        {
            label: '视频后期',
            value: 2,
        },
    ]

    const smallClass:optionInfo[] = [
        {
            label: LastSort[0],
            value: 0,
        },
        {
            label: LastSort[1],
            value: 1,
        },
        {
            label: LastSort[2],
            value: 2,
        },
    ]

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

    //请求橱窗盒子函数
    async function getShowcaseBoxes(infoArr=[0,0,0,false,false]):Promise<void>{
        let result = await showcaseInfo.getShowcaseBoxes(infoArr);
    }

    //当参数变化时重新请求橱窗盒子
    async function changeClassifyOption(infoArr:any):Promise<void>{
        infoArr.unshift(props.primarySort);
        getShowcaseBoxes(infoArr);
    }

    onMounted(()=>{
        getShowcaseBoxes();
    })

    onUnmounted(()=>{
        
    })

</script>

<style lang="scss" scoped>
    .baseNode{
        padding: 10px;
    }
</style>