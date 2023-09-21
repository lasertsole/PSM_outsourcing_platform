<template>
    <div class="baseNode">
        <filterBar
            :bigClass="bigClass"
            @changeClassifyOption="changeClassifyOption"
        ></filterBar>
        <div class="itemBoxContainer">
            <template v-for="item in infoArr">
                <itemBox
                    :boxInfo="item"
                >
                </itemBox>
            </template>
        </div>
    </div>
</template>

<script setup lang="ts">
    import useGlobal from "global";
    import { SortWay } from "@/types/pageType/showcase";
    import { optionInfo } from "@/types/pageType/showcase";
    import itemBox from "@/components/showcase/itemBox.vue";
    import filterBar from "@/components/showcase/filterBar.vue";
    import { ref, defineProps, onMounted, onUnmounted } from "vue";
    import { ShowcaseBoxesInfo } from "@/types/stores/ShowcaseInfo";

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
            label: '普通轴',
            value: 0,
        },
        {
            label: '外语轴',
            value: 1,
        },
        {
            label: '特效轴',
            value: 2,
        },
    ]

    const infoArr = ref<ShowcaseBoxesInfo[]>([]);

    //请求橱窗盒子函数
    async function getShowcaseBoxes(paramsArr=[0,0,0,false,false]):Promise<void>{
        let result:ShowcaseBoxesInfo[] = await showcaseInfo.getShowcaseBoxes(paramsArr);
        infoArr.value = result;
    };

    //当参数变化时重新请求橱窗盒子
    async function changeClassifyOption(paramsArr:any):Promise<void>{
        paramsArr.unshift(props.primarySort);
        getShowcaseBoxes(paramsArr);
    };

    onMounted(()=>{
        getShowcaseBoxes();
    });

    onUnmounted(()=>{
        
    });

</script>

<style lang="scss" scoped>
    .baseNode{
        padding: 10px;
    }
</style>