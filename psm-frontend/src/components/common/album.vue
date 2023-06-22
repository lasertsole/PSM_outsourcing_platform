<template>
    <div class="album">
        <div class="pic" :style="`background-image:url(${pics[index]});`"></div>
        <div class="catalog">
            <div class="token" @click="changeIndex(index-1<0?pics.length-1:index-1)">&lt;</div>
            <div class="catalogPics">
                <template v-for="(selectItem, selectIndex) in pics">
                    <div class="catalogPic"
                        :style="`background-image:url(${selectItem});`"
                        @click="changeIndex(selectIndex)">
                        <div :class="{full:true, mock:selectIndex!=index}"></div>
                    </div>
                </template>
            </div>
            <div class="token" @click="changeIndex((index+1)%pics.length)">&gt;</div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import {defineProps, ref} from "vue";
    const index = ref<number>(0);
    let pics:string[] = ['Carousel/bg-1.jpg','Carousel/bg-2.jpg','Carousel/bg-3.jpg'];

    function changeIndex(selectIndex:number):void{
        index.value = selectIndex;
    }
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "@/common.scss";
    .album{
        @include fixedRetangle(100%, 100%);
        background-color: white;
        border: 2px solid #dbdbdb;
        border-radius: 10px;
        overflow: hidden;
        padding: 20px 10px 10px 5px;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-end;
        .pic{
            @include fixedRetangle(100%, 205px);
            object-fit: cover;
            background-repeat: no-repeat;
            background-position: center;
            background-size: 100%;
        }
        .catalog{
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 10px;
            >*{
                cursor: pointer;
            }
            .token{
                color: #707070;
                font-size: 20px;
                font-weight: bold;
            }

            .catalogPics{
                display: flex;
                .catalogPic{
                    $picHeight: 50px;
                    @include fixedRetangle($picHeight*1.3, $picHeight);
                    background-repeat: no-repeat;
                    background-position: center;
                    background-size: 100% 100%;
                    margin: 0px 5px;
                    .full{
                        @include fullInParent();
                        &.mock{
                            background-color: rgba($color: #000000, $alpha: .3);
                        }
                    }
                }
            }
        }
    }
</style>