<template>
    <div class="calendar">
        <el-calendar ref="calendar">
            <template #header="{ date }">
                <div class="deadLine">截稿日历 {{ date }}</div>
                <el-button-group>
                    <el-button size="small" @click="selectDate('prev-month')">&lt</el-button>
                    <el-button size="small" @click="selectDate('today')">今天</el-button>
                    <el-button size="small" @click="selectDate('next-month')">&gt</el-button>
                </el-button-group>
            </template>
        </el-calendar>
    </div>
</template>
  
<script lang="ts" setup>
    import { ref } from 'vue'

    const calendar = ref()
    const selectDate = (val: string) => {
        calendar.value.selectDate(val)
    }
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
            border-radius: 6px;
            overflow: hidden;

            .el-calendar__header{
                flex-direction: column;
                align-items: center;
                >div{
                    padding: 3px 0px;
                }
                .deadLine{
                    font-size: 12px;
                }
            }
            .el-calendar-day{
                height: 30px;
            }
        }
    }
</style>