<template>
    <div class="indexPage">
        <div class="top">
            <div class="info">
                <div class="title">找好切片man，完成需求，一举两得</div>
                <div class="select">
                    <div class="button require">我是需求方</div>
                    <div class="button create">我是创作者</div>
                </div>
            </div>
            <div class="help">联系在线客服</div>
        </div>
        <div class="bottom"><carousel></carousel></div>
    </div>
</template>

<script lang="ts" setup>
    import carousel from "/src/components/indexPage/carousel.vue"
</script>

<style lang="scss" scoped>
    @mixin fullContain{
        width: 100%;
        height: 100%;
    }
    .indexPage{
        @include fullContain;
        position: relative;
        .top{
            @include fullContain;
            position: absolute;
            z-index: 1;
            background-color: rgba($color: #000000, $alpha: .2);
            display: flex;
            flex-direction: column;
            justify-content: center;
            .info{
                height: 370px;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                align-items: center;
                .title{
                    font-size: 34px;
                    color: white;
                    font-weight: bold;
                }
                .select{
                    display: flex;
                    flex-direction: row;
                    font-size: 20px;
                    .button{
                        box-sizing: border-box;
                        margin: 0px 50px;
                        width: 260px;
                        padding: 15px 80px;
                        border-radius: 37px;
                        color: white;
                    }
                    .require{
                        background-color: #fb7299
                    }
                    .create{
                        background-color: #00a8e9;
                    }
                }
            }
            .help{
                position: absolute;
                left: 30px;
                bottom: 40px;
                font-size: 16px;
                background-color: #00a8e9;
                color: white;
                padding: 5px 20px;
                border-radius: 37px;
            }
        }
        .bottom{
            @include fullContain;
            position: relative;
            z-index: 0;
        }
    }
</style>