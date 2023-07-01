<template>
    <div class="personSpace">
        <div class="personAbtract">
            <div class="profile">
                <img src="images/xiProfile.jpg">
            </div>
            <div class="recomment">
                <div class="recomment-name">帕斯汐</div>
                <div class="recomment-title">个人简介:</div>
                <div class="recomment-content">加急需要提前联系 价格需要*2 特急*2.5 要发票*3。不接过于福利的封面......</div>
            </div>
            <div class="button-group">
                <div class="share">分享</div>
                <div class="edit">编辑信息</div>
            </div>
        </div>
        <div class="showcase">
            <h2>
                <img class="icon" src="icons/Vector.png" alt="">
                <span>橱窗</span>
            </h2>
            <div class="more">
                <div class="moreButton">查看更多</div>
            </div>
            <div class="boxContainer">
                <adaptContainer
                    :boxNum="showCaseBoxArr.length"
                    :boxWidth="290"
                    :boxHeight="180"
                >
                    <template v-for="(item, index) in showCaseBoxArr" #[index+1]>
                        <showCaseBox
                            :imgSrc="item.imgSrc"
                            :describt="item.describt"
                            ref="showCaseBoxDom"
                        ></showCaseBox>
                    </template>
                </adaptContainer>
            </div>
        </div>
        <div class="planning">
            <h2>
                <img class="icon" src="icons/planning.png" alt="">
                <span>企划</span>
            </h2>
            <div class="more">
                <div class="moreButton">查看更多</div>
            </div>
            <div class="boxContainer">
                <adaptContainer
                    :boxNum="planningBoxArr.length"
                    :boxWidth="500"
                    :boxHeight="180"
                >
                    <template v-for="(item, index) in planningBoxArr" #[index+1]>
                        <itemBox
                            :title="item.title"
                            :describe="item.describe"
                            :imgSrc="item.imgSrc"
                            :type="item.type"
                            :calendar="item.calendar"
                            :price="item.price"
                        ></itemBox>
                    </template>
                </adaptContainer>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import itemBox from "components/planning/itemBox.vue";
    import adaptContainer from "components/common/adaptContainer.vue"
    import showCaseBox from "components/personSpace/showCaseBox.vue"
    import { ref } from "vue"
    import { showCaseBoxInfo, planningBoxInfo} from "types/pageType/personSpace"

    /*橱窗盒子的数据*/
    const showCaseBoxArr = ref<showCaseBoxInfo[]>([
        {
            imgSrc: "Carousel/bg-1.jpg",
            describt: "虚拟主播 视频封面（16:10）"
        },
        {
            imgSrc: "Carousel/bg-1.jpg",
            describt: "虚拟主播 视频封面（16:10）"
        },
        {
            imgSrc: "Carousel/bg-1.jpg",
            describt: "虚拟主播 视频封面（16:10）"
        }
    ])
     /*企划盒子的数据*/
     const planningBoxArr = ref<planningBoxInfo[]>([
        {
            title:"双人联动轴",
            describe:"双人轴 带点日语和英语 需要和谐部分词汇",
            imgSrc:"Carousel/bg-1.jpg",
            type:"美工",
            calendar:"2023-5-10",
            price:"￥150-250",
        },
        {
            title:"双人联动轴",
            describe:"双人轴 带点日语和英语 需要和谐部分词汇",
            imgSrc:"Carousel/bg-1.jpg",
            type:"美工",
            calendar:"2023-5-10",
            price:"￥150-250",
        },
        {
            title:"双人联动轴",
            describe:"双人轴 带点日语和英语 需要和谐部分词汇",
            imgSrc:"Carousel/bg-1.jpg",
            type:"美工",
            calendar:"2023-5-10",
            price:"￥150-250",
        }
    ])
</script>

<style lang="scss" scoped>
    @mixin fixedSquare($size){
        min-width: $size;
        max-width: $size;
        min-height: $size;
        max-height: $size;
    }
    @mixin fixedCircle($size){
        @include fixedSquare($size);
        overflow: hidden;
        border-radius: 50%;
    }
    @mixin fiexedWidth($size){
        max-width: $size;
        min-width: $size;
    }
    .personSpace{
        width: 100%;
        height: 100%;
        padding: 20px;
        display: flex;
        flex-direction: column;
        align-items: center;
        .personAbtract{
            width: 100%;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding-bottom: 15px;
            border-bottom: 1px solid rgba(165, 165, 165, 0.3568627451);
            .profile{
                @include fixedCircle(100px);
                margin-bottom: 5px;
                img{
                    width: 100%;
                    height: 100%;
                    object-fit: cover;
                }
            }
            .recomment{
                display: flex;
                flex-direction: column;
                align-items: center;
                font-size: 12px;
                color: gray;
                font-weight: bold;
                >*{
                    margin-bottom: 5px;
                }
                &-name{
                    font-size: 18px;
                    color: black;
                }
                &-title{
                }
                &-content{
                    max-width: 180px;
                }
            }
            .button-group{
                min-width: 240px;
                display: flex;
                justify-content: space-between;
                color: white;
                div{
                    cursor: pointer;
                    display: flex;
                    justify-content: center;
                    min-width: 100px;
                    max-width: 100px;
                    border-radius: 4px;
                }
                .share{
                    background-color: #fb7299;
                }
                .edit{
                    background-color: #00a8e9;
                }
            }
        }
        .showcase{
            width: 100%;
            border-bottom: 1px solid rgba(165, 165, 165, 0.3568627451);
            padding-bottom: 15px;
            h2{
                padding: 13px 0px;
                text-align: center;
            }
            .boxContainer{
                display: flex;
                flex-direction: row;
                justify-content: center;
                .box{
                    @include fiexedWidth(250px);
                    margin: 0px 20px;
                    .top{
                        overflow: hidden;
                        border-radius: 12px;
                        height: 150px;
                        img{
                            width: 100%;
                            height: 100%;
                            object-fit: cover;
                        }
                    }
                    .bottom{
                        padding: 10px 0px 0px 10px;
                        height: 20px;
                        font-size: 12px;
                        font-weight: bolder;
                    }
                }
            }
        }
        .planning{
            width: 100%;
            border-bottom: 1px solid rgba(165, 165, 165, 0.3568627451);
            padding-bottom: 15px;
            h2{
                padding: 13px 0px;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .boxContainer{
                width: 100%;
                display: flex;
                flex-direction: row;
                justify-content: flex-start;
                align-content: flex-start;
                align-items: flex-start;
                flex-wrap: wrap;
                overflow-y: auto;
            }
        }

        .icon{
            @include fixedSquare(20px);
            margin-right: 10px;
        }
        .more{
            display: flex;
            justify-content: flex-end;
            .moreButton{
                font-size: 12px;
                font-weight: bold;
                background-color: #00a8e9;
                color: white;
                padding: 2px 5px;
                border-radius: 5px;
                cursor: pointer;
            }
        }
    }
</style>@/types/pageType/personSpace