<template>
    <router-link to="/index" class="logo">
        <div class="logo-image"></div>
        <div class="logo-text">
            <h2>喵字幕</h2>
            <h5>MIAOZIMU.COM</h5>
        </div>
    </router-link>
</template>

<script lang="ts" setup>

</script>

<style lang="scss">
    @use "sass:math";
    @import "commonScss";
    .logo{
        height: 100%;
        display: flex;
        flex-direction: row;
        align-items: center;
        margin-right: 10px;
        color: #fb7299;
        cursor: pointer;
        &:hover{
            color: #fb7299
        }
        .logo-image{
            @include fixedSquare(80px);
            background-image: url(images/psmlogo.png);
            background-repeat: no-repeat;
            background-position: center 5px;
            background-size: 90px;
            margin-right: 5px;
        }
        .logo-text{
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            >*{
                display: flex;
                justify-content: center;
            }
            h2{
                margin-top: 10px;
                font-size: 28px;
                line-height: 26px;
                letter-spacing:8px;
                text-indent: 8px;
            }

            h5{
                font-size: 13.5px;
                letter-spacing:1px;
            }
        }
    }
    @media screen and (max-width: 800px) and (min-width: 600px) {
        .logo{
            display: none;
        }
    }
    @media screen and (max-width: 600px) {
        .logo{
            .logo-image{
                @include fixedSquare(50px);
                background-size: 56px;
                background-position: center 4px;
            }
            .logo-text{
                h2{
                    font-size: 17px;
                    letter-spacing:2.5px;
                    text-indent: 0px;
                    line-height: 20px;
                }

                h5{
                    font-size: 7.5px;
                    letter-spacing:0.5px;
                }
            }
        }
    }
</style>