<template>
    <div class="indexPage">
        <div class="top">
            <div class="info">
                <div class="title">找好切片MAN，完成需求，一举两得</div>
                <template v-if="!isOnline">
                    <div class="select">
                        <div class="button require" @click="LARModel.showFloat()">我是需求方</div>
                        <div class="button create" @click="LARModel.showFloat()">我是创作者</div>
                    </div>
                </template>
            </div>
        </div>
        <div class="bottom">
            <carousel
                :carouselProcessArr="carouselProcessArr"
            >
            </carousel>
        </div>
    </div>
</template>

<script lang="ts" setup>
    import { ref, computed } from "vue";
    import axios from "axios";
    import useGlobal from "global";
    import { storeToRefs } from "pinia";
    import carousel from "components/indexPage/carousel.vue"

    const global = useGlobal();
    const UserInfo = global?.UserInfo;
    const LARModel = global?.LARFloat;

    /**********当登录时两个大按钮消失*********/
    const { isOnline } = storeToRefs(UserInfo);

    const carouselArr = ref<string[]>([]);
    (async function getFrontCover():Promise<void>{//首页获取设置轮播图
        let result = await axios.get("api/getFrontCover");
        carouselArr.value= result.data.src;
    })();//setup时自调用一次，然后因为keepalive不会再次调用

    const carouselProcessArr = computed(()=>{
        return carouselArr.value.map((item)=>{
            return "http://"+import.meta.env.VITE_URL+item;
        });
    });
</script>

<style lang="scss" scoped>
    @mixin fullContain{
        width: 100%;
        height: 100%;
    }
    .indexPage{
        @include fullContain;
        position: relative;
        z-index: 1;
        .top{
            @include fullContain;
            position: absolute;
            z-index: 1;
            background-color: rgba($color: #000000, $alpha: .2);
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            .info{
                max-width: 700px;
                margin-top: 100px;
                height: 370px;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                align-items: center;
                .title{
                    font-size: 34px;
                    color: white;
                    font-weight: bold;
                    padding: 0px 20px;
                }
                .select{
                    display: flex;
                    flex-direction: row;
                    justify-content: space-between;
                    font-size: 20px;
                    width: 100%;
                    .button{
                        box-sizing: border-box;
                        width: 260px;
                        padding: 15px 80px;
                        border-radius: 37px;
                        color: white;
                        cursor: pointer;
                    }
                    .require{
                        background-color: #fb7299
                    }
                    .create{
                        background-color: #00a8e9;
                    }
                }
            }
        }
        .bottom{
            @include fullContain;
            position: relative;
            z-index: 0;
        }
    }
</style>