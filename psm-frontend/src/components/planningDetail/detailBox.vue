<template>
    <div class="detail">
        <div class="title">设计一个背景墙</div>

        <div class="hr"></div>

        <div class="selectInfoBox">
            <div>企划发布时间：2023年3月10日</div>
            <div>封面/美工</div>
        </div>
        <div class="content">
            <div class="left">
                <div class="detailRequire">
                    <div class="title">
                        详情要求
                    </div>
                    <div class="value">
                        周末要用，周二就要定稿了尺寸为高2.5米X宣4米或者5米背最墙是用于活动会场供客户到场打卡拍照留念，看望能够生动有越，可以Q一点的动漫形象 主要的色系需要明亮浅色图为会场比较赔圆面主震内容:用各种货币锁成的道路，一排挖掘机车队组成战队在前进，并且城口号
                        参考样图
                        编节什么员体沟通
                    </div>
                </div>
                <div class="workRequire">
                    <div class="title">
                        稿件要求
                    </div>
                    <div class="value">
                        <div>
                            <div class="key">风格要求:</div>
                            <div class="value">风格不限</div>
                        </div>
                        <div>
                            <div class="key">尺寸规格:</div>
                            <div class="value">具体要私信沟通</div>
                        </div>
                        <div>
                            <div class="key">使用途径:</div>
                            <div class="value">个人商用</div>
                        </div>
                        <div>
                            <div class="key">发布需求:</div>
                            <div class="value">需按约定公开发布</div>
                        </div>
                        <div>
                            <div class="key">试稿要求:</div>
                            <div class="value">不需要试稿</div>
                        </div>
                        <div>
                            <div class="key">反馈需求:</div>
                            <div class="value">需定期反馈进度</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="right">
                <div class="title">参考样图</div>
                <div class="picture" style="background-image: url(Carousel/bg-1.jpg);"></div>
            </div>
        </div>
        <hr/>
        <div class="step">
            <div class="planningStep">企划流程</div>
            <el-steps :active="5" align-center>
                <el-step title="开始合作" description="0%" />
                <el-step title="草稿阶段" description="30%" />
                <el-step title="初步定稿" description="50%" />
                <el-step title="中期确定" description="70%" />
                <el-step title="确认收稿" description="100%" />
            </el-steps>
        </div>
    </div>
</template>

<script setup lang="ts">
    const props = defineProps({
        // deadLine:{type:String, required: true}, 
        // budget:{type:String, required: true},
        // employFinishedTime:{type:String, required: true},
    });
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .detail{
        background-color: #00a8e9;
        @include fixedRetangle(900px, 380px);
        display: flex;
        flex-direction: column;
        .title{
            color: #444444;
            font-weight: bold;
            padding: 5px 0px;
        }
        .hr{
            @include fixedHeight(1.5px);
            background-color: #cbcbcb;
        }
        .selectInfoBox{
            display: flex;
            font-size: 12px;
            color: #707070;
            font-weight: bold;
            >div{
                margin-right: 50px;
                padding: 2px 0px;
            }
        }
        .content{
            display: flex;
            justify-content: space-between;
            @include fixedRetangle(100%,210px);
            box-sizing: border-box;
            >.left{
                @include fixedRetangle(70%,100%);
                >div{
                    .title{
                        color: #00a8e9;
                    }
                }
                .detailRequire{
                    .value{
                        font-size: 12px;
                        color: #707070;
                        font-weight: bold;
                    }
                }
                .workRequire{
                    .value{
                        font-size: 14px;
                        display: flex;
                        flex-direction: row;
                        justify-content: space-between;
                        flex-wrap: wrap;
                        >div{
                            margin-left: 5px;
                            display: flex;
                            flex-direction: row;
                            @include fixedRetangle(250px,20px);
                            border-left: #00a8e9 5px solid;
                            font-weight: bold;
                            align-items: center;
                            flex-wrap: wrap;
                            &:not(:nth-of-type(1),:nth-of-type(2)){
                                margin-top: 10px;
                            }
                            .key{
                                color: #00a8e9;
                                margin-left: 5px;
                            }
                            .value{
                                margin-left: 20px;
                                color: #707070;
                            }
                        }
                    }
                }
            }
            >.right{
                @include fixedRetangle(30%,100%);
                >.title{
                    color: #00a8e9;
                }
                .picture{
                    @include fixedRetangle(250px,150px);
                    background-repeat: no-repeat;
                    background-size: 100%;
                    background-position: center;
                }
            }
        }
        .step{
            .planningStep{
                color: #00a8e9;
                font-size: 18px;
            }
            &::v-deep(.el-steps){
                padding-top: 10px;
                .el-step__icon{
                    @include fixedSquare(15px);
                    .el-step__icon-inner{
                        font-size: 8px;
                    }
                }
                .el-step__main{
                    
                    .el-step__description{
                        position: absolute;
                        top: -10px;
                        left: 50%;
                        transform: translateX(-50%);
                    }
                }
            }
        }
    }
</style>