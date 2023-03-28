<template>
    <div class="main">
    <HeaderComponent></HeaderComponent>
    <div class="wrapper">
        <router-view></router-view>
    </div>
  </div>
</template>

<script setup lang="ts">
    import HeaderComponent from "/src/components/HeaderComponent.vue"
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
            display: flex;
            flex-direction: row;
            flex-grow: 1;
            overflow: hidden;
        }
    }
</style>
