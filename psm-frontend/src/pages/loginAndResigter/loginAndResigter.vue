<template>
    <div class="loginAndResigter">
        <div class="box">
            <router-link to="/index" class="logo">
                <div class="logo-image"></div>
                <div class="logo-text">
                    <h2>喵字幕</h2>
                    <h5>miaozimu.com</h5>
                </div>
            </router-link>

            <router-view v-slot="{ Component }">
                <transition mode="out-in">
                    <keep-alive>
                        <component 
                            :is="Component"
                        ></component>
                    </keep-alive>
                </transition>
            </router-view>
        </div>
    </div>
</template>

<script setup lang="ts">
    import useGlobal from "global";
    import { onMounted, onUnmounted } from "vue";
    import { useRouter } from "vue-router";
    import { storeToRefs } from "pinia";

     /**********获取全局变量*********/
    const global = useGlobal();
    const Bus = global?.Bus;
    // const { isOnline } = storeToRefs(global?.UserInfo);
    // console.log(isOnline);

    /**********挂载触发*********/
    const router = useRouter();
    onMounted(()=>{
        Bus.on("login",()=>{ 
            router.replace({ name: 'home'});
        });
    })
    /**********卸载触发*********/
    onUnmounted(()=>{
        Bus.off("login");
    })
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";
    .loginAndResigter{
        width: 100%;
        height: 100%;
        overflow-y: auto;
        background-color: rgb(230,234,238);
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;
        .box{
            box-sizing: border-box;
            padding: 60px 50px;
            background-color: white;
            margin-top: 60px;
            width: 450px;
            border-radius: 8px;
            overflow: hidden;
            display: flex;
            flex-direction: column;
            align-items: center;
            .logo{
                margin-bottom: 50px;
                display: flex;
                flex-direction: row;
                align-items: center;
                &-image{
                    @include fixedSquare(60px);
                    background-image: url(images/psmlogo.png);
                    background-size: 100%;
                }
                
                &-text{
                    h2{
                        font-size: 22px;
                        color: #fb7299;
                    }

                    h5{
                        font-size: 11.5px;
                        color: #fb7299;
                    }
                }
            }
        }
    }
    //transition出入场动画
    .v-enter-from{
        opacity: 0;
    }
    .v-enter-active{
        transition: opacity .7s ease;
    }
    .v-enter-to{
        opacity: 1;
    }
    .v-leave-from{
        opacity: 1;
    }
    .v-leave-active{
        transition: opacity .7s ease;
    }
    .v-leave-to{
        opacity: 0;
    }
</style>
<style lang="scss">
    .loginAndResigter{
        .box{
            .login{
                .el-input{
                   margin-bottom: 15px;
                }

                .el-button{
                    width: 100%;
                    box-sizing: border-box;
                    height: 34px;
                    margin-bottom: 15px;
                }
            }
            .register{
                .el-input{
                   margin-bottom: 15px;
                }

                .el-button{
                    width: 100%;
                    box-sizing: border-box;
                    height: 34px;
                    margin-bottom: 15px;
                }
            }
        }
    }
</style>