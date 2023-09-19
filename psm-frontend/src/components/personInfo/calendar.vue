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
    @import "commonScss";
    .calendar{
        display: block;
        $boxHeight: 280px;
        @include fixedRetangle(100%, $boxHeight);
        margin-top: 20px;
        &::v-deep(.el-calendar){
            @include fixedRetangle(100%, $boxHeight);
            border-radius: 6px;
            overflow: hidden;

            .el-calendar__header{
                flex-direction: column;
                align-items: center;
                border: none;
                padding-bottom: 0px;

                .el-button-group{
                    >button{
                        border: none;
                    }
                }

                >div{
                    padding: 3px 0px;
                }
                .deadLine{
                    font-size: 12px;
                }
            }

            .el-calendar__body{
                padding-top: 0px;
                padding-bottom: 0px;

                .el-calendar-table{
                    
                    thead{

                        th{
                            font-size: 12px;
                            color: #989898;
                        }
                    }
                    
                    tbody{

                        td{
                            border: 1px solid rgba(0,0,0,0);
                            border-radius: 5px;
                            overflow: hidden;
                            
                            &.is-selected{
                                background-color: rgba(0,0,0,0);
                                border-color: #00a8e9;
                            }
                            .el-calendar-day{
                                display: flex;
                                justify-content: center;
                                align-items: center;
                                height: 30px;
                                padding: 0px;
                                font-size: 12px;
                            }
                        }
                    }
                }
            }
        }
    }
</style>