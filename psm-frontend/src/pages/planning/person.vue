<template>
    <div class="baseNode">
        <filterBar
            :bigClass="bigClass"
            :smallClass="smallClass"
            :timeClass="timeClass"
        >
        </filterBar>
        <div class="itemBoxContainer">
            <template v-for="(item,index) in itemDetailBox">
                <itemBox
                    :title="item.title"
                    :describe="item.describe"
                    :imgSrc="item.imgSrc"
                    :type="item.type"
                    :calendar="item.calendar"
                    :price="item.price"
                    :link="item.link"
                ></itemBox>
            </template>
        </div>
    </div>
</template>

<script setup lang="ts">
    import {ref} from "vue";
    import { optionInfo } from "types/pageType/showcase";
    import itemBox from "components/planning/itemBox.vue";
    import filterBar from "components/planning/filterBar.vue";
    import { itemBoxInfo } from "types/componentsType/itemBoxComponent";

    //过滤器选项
    const bigClass:optionInfo[] = [
        {
            value: '报酬由高到低',
            label: '报酬由高到低',
        },
        {
            value: '最新发布',
            label: '最新发布',
        },
        {
            value: '应征人数',
            label: '应征人数',
        },
    ]

    const smallClass:optionInfo[] = [
        {
            value: '美工类',
            label: '美工类',
        },
        {
            value: '字幕类',
            label: '字幕类',
        },
        {
            value: '翻译类',
            label: '翻译类',
        },
        {
            value: '剪辑类',
            label: '剪辑类',
        },
    ]

    const timeClass:optionInfo[] = [
        {
            value: '30天以内',
            label: '30天以内',
        },
        {
            value: '15天以内',
            label: '15天以内',
        },
        {
            value: '7天以内',
            label: '7天以内',
        },
        {
            value: '3天以内',
            label: '3天以内',
        },
    ]

    const itemDetailBox = ref([
        {
            title:'123',
            describe:"123",
            imgSrc:"Carousel/bg-1.jpg",
            type:"123",
            calendar:"2021-1-1",
            price:"123",
            link:"/planningDetail"
        },
        {
            title:'123',
            describe:"123",
            imgSrc:"Carousel/bg-1.jpg",
            type:"123",
            calendar:"2021-1-1",
            price:"123",
            link:"/planningDetail"
        },
        {
            title:'123',
            describe:"123",
            imgSrc:"Carousel/bg-1.jpg",
            type:"123",
            calendar:"2021-1-1",
            price:"123",
            link:"/planningDetail"
        }
    ]);
</script>

<style lang="scss" scoped>
    .baseNode{
        padding: 10px;
        flex-direction: column;
        flex-grow: 1;
        box-sizing: border-box;
        display: flex;
        .itemBoxContainer{
            min-width: 100%;
            max-width: 100%;
            flex-grow: 1;
            display: flex;
            flex-direction: row;
            justify-content: flex-start;
            align-content: flex-start;
            align-items: flex-start;
            flex-wrap: wrap;
            overflow-y: auto;
        }
    }
</style>