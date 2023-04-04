<template>
    <router-link to="index" class="logo">
        <div class="logo-image"></div>
        <div class="logo-text">
            <h2>喵字幕</h2>
            <h5>miaozimu.com</h5>
        </div>
    </router-link>
</template>

<script lang="ts" setup>

</script>

<style lang="scss">
    @mixin square($edgeSize){
        width: $edgeSize;
        height: $edgeSize;
    }
    .logo{
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
            @include square(60px);
            background-image: url(images/psmlogo.png);
            background-repeat: no-repeat;
            background-position: center;
            background-size: 100%;
            margin-right: 5px;
        }
        .logo-info{
            h2{
                font-size: 24px;
            }

            h5{
                font-size: 13.5px;
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
                @include square(25px);
            }
            .logo-text{
                h2{
                    font-size: 17px;
                }

                h5{
                    font-size: 7.5px;
                }
            }
        }
    }
</style>