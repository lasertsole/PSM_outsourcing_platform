<template>
    <div class="calendar">
        <el-calendar v-model="value">
            <template #header="{ date }">截稿日历 {{ date }}</template>
        </el-calendar>
    </div>
</template>
  
<script lang="ts" setup>
    import { ref } from 'vue'
    const value = ref(new Date())
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "@/common.scss";
    .calendar{
        display: block;
        @include fixedRetangle(100%, 300px);
        margin-top: 10px;
        &::v-deep(.el-calendar){
            @include fixedRetangle(100%, 300px);
            .el-calendar-day{
                height: 30px;
            }
        }
    }
</style>