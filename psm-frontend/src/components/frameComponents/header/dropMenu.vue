<template>
    <div :class="{selectList:true, show:drawer}">
        <router-link v-for="item in tabBarArr" active-class="active" :to="<RouteLocationRaw>item.linkTo">{{item.text}}</router-link>
        <hr>
        <router-link to="/loginAndRegister" href="#">登录</router-link>
        <router-link to="/loginAndRegister" href="#">注册</router-link>
    </div>
</template>

<script lang="ts" setup>
    import {defineProps, onMounted, onUnmounted, defineEmits} from "vue"
    import {RouteLocationRaw} from "vue-router"
    const props = defineProps({tabBarArr:Object, drawer:Boolean});
    const emits = defineEmits(['closeDropMenu']);

    function closeDropMenu():void{
        console.log(window.innerWidth);
        if(window.innerWidth>600){
            emits('closeDropMenu');
        }
    }
    onMounted(()=>{
        window.addEventListener("resize",closeDropMenu,false);//绑定监听屏幕大小变化事件
    })

    onUnmounted(()=>{
        window.removeEventListener("resize",closingDropMenu);//解绑监听屏幕大小变化事件
    })
</script>

<style lang="scss">
    @keyframes selectListMove{
        0%{
            transform: translateY(15px);
        }
        100%{
            transform: translateY(0px);
        }
    }
    .selectList{
        display: none;
        background-color: white;
        transition: all .3s ease-in-out;
        box-sizing: border-box;
        width: 100%;
        height: 0px;
        position: absolute;
        top: 50px;
        left: 0px;
        z-index: 2;
        overflow: hidden;
        padding: 0px 15px;
        &.show{
            height: 290px;
        }

        a{
            padding: 15px 10px;
            color: #4a4a4a;
            font-size: 14px;
            display: block;
            transition: background-color .3s ease-in-out;
            border-radius: 5px;
            &:hover{
                background-color: #cccccc;
            }
        }
        hr{
            height: 1px;
            margin: 5px 0px;
        }

        @media screen and (max-width: 600px) {
            display: block;
            &.show{
                padding: 10px 15px;
            }
            animation: selectListMove .3s linear;
        }
    }
</style>