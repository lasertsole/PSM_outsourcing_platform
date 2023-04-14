<template>
    <div class="main">
        <HeaderComponent></HeaderComponent>
        <div class="wrapper">
            <router-view></router-view>
            <session></session>
        </div>
        <LARModel></LARModel>
    </div>
</template>

<script setup lang="ts">
    import session from "@/components/session.vue";
    import LARModel from "@/components/LARModel.vue";
    import HeaderComponent from "@/components/HeaderComponent.vue";
</script>

<style lang="scss" scoped>
    .main{
        overflow: hidden;
        position: relative;
        z-index: 1;
        display: flex;
        flex-direction: column;
        width: 100%;
        height: 100%;
        box-sizing: border-box;
        .wrapper{
            width: 100%;
            height: 100%;
            display: flex;
            flex-direction: row;
            flex-grow: 1;
            overflow: auto;
        }
    }
</style>
