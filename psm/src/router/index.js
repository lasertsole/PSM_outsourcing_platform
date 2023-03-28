import { createRouter, createWebHashHistory } from 'vue-router'
/*一级路由*/
import indexPage from "/src/pages/indexPage.vue"

const routes = [
    //首页
    {
        path: "/",
        name: "home",
        redirect:'/index',
    },
    {
        path: '/index',
        name: 'index',
        component: indexPage
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router