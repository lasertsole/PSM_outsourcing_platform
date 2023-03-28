<template>
    <ul class="tabBarArr">
        <li v-for="(item, index) in tabBarArr">
            <router-link class="tabbarChildItem" active-class="active" :to="item.linkTo">{{item.text}}</router-link>
        </li>
    </ul>
</template>

<script lang="ts" setup>
    import { defineProps} from 'vue';
    const props = defineProps({tabBarArr:Object});
</script>

<style lang="scss" scoped>
    .tabBarArr{
        display: flex;
        flex-direction: row;
        font-size: 18px;
        align-items: center;
        li{
            width: 80px;
            display: flex;
            justify-content: center;
            align-items: center;
            a{
                color: #707070;
            }
        }
    }
</style>