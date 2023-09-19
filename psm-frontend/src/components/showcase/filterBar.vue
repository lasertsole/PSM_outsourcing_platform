<template>
    <div class="filter-bar">
        <el-select v-model="typeValue" class="m-2" :placeholder="bigClass&&bigClass[0].label" size="small">
            <el-option
                v-for="item in bigClass"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
        </el-select>

        <el-select v-model="sortValue" class="m-2" :placeholder="smallClass&&smallClass[0].label" size="small">
            <el-option
                v-for="item in smallClass"
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
</template>

<script setup lang="ts">
    import { optionInfo } from "types/pageType/showcase"
    import { ref, defineProps, defineEmits, PropType, watch } from "vue"

    const props = defineProps({
        bigClass:{type:Array as PropType<optionInfo[]>}, 
        smallClass:{type:Array as PropType<optionInfo[]>}
    });

    const emits = defineEmits(["changeClassifyOption"]);

    //大类选项
    const typeValue = ref<number>(0);

    //小类选项
    const sortValue = ref<number>(0);

    //档期空闲？
    const isIdle = ref<boolean>(false);

    //能否加急
    const canQuicky = ref<boolean>(false);

    //当上述条件变化时向服务器发出请求
    watch([typeValue, sortValue, isIdle, canQuicky],(newVal, oldVal)=>{
        emits("changeClassifyOption", newVal);
    });
</script>

<style lang="scss" scoped>
    .filter-bar{
        .el-select{
            box-sizing: border-box;
            width: 108px;
            ::placeholder{
                color: #707070;
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