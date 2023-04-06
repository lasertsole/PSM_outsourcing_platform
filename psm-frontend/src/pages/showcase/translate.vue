<template>
    <div class="translate">
        <div class="filter-bar">
            <el-select v-model="typeValue" class="m-2" placeholder="直播封面" size="small">
                <el-option
                v-for="item in typeOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                />
            </el-select>

            <el-select v-model="sortValue" class="m-2" placeholder="筛选类型" size="small">
                <el-option
                v-for="item in sortOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                />
            </el-select>

            <el-switch
                v-model="isIdle"
                active-text="档期空闲"
            />

            <el-switch
                v-model="canQuicky"
                active-text="能否加急"
            />
        </div>
    </div>
</template>

<script setup lang="ts">
    import {ref} from "vue"
    import { optionInfo} from "@/types/pageType/showcase"
    import { tabBarItem } from "@/types/common/tabBarType"

    const tabList = ref<tabBarItem[]>([
        {tabName:"剪辑类",linkTo:""},
        {tabName:"美工类",linkTo:""},
        {tabName:"翻译类",linkTo:""},
        {tabName:"字幕类",linkTo:""},
    ]);

    //大类分类
    const typeValue = ref<string>('')
    const typeOptions:optionInfo[] = [
        {
            value: '直播切片',
            label: '直播切片',
        },
        {
            value: '歌曲相关',
            label: '歌曲相关',
        },
        {
            value: '视频后期',
            label: '视频后期',
        },
    ]

    //小类分类
    const sortValue = ref<string>('')
    const sortOptions:optionInfo[] = [
        {
            value: '按综合',
            label: '按综合',
        },
        {
            value: '按活跃',
            label: '按活跃',
        },
        {
            value: '按评论',
            label: '按评论',
        },
    ]

    //档期空闲？
    const isIdle = ref<boolean>(false);

    //能否加急
    const canQuicky = ref<boolean>(false);

</script>

<style lang="scss" scoped>
</style>
<style lang="scss">
    .translate{
        padding: 10px;
        .el-select{
            box-sizing: border-box;
            width: 108px;
            ::placeholder{
                color: black;
            }
            margin-right: 15px;
        }
        .el-popper{
            box-sizing: border-box;
        }
        .el-switch{
            margin-right: 15px;
        }
    }
</style>