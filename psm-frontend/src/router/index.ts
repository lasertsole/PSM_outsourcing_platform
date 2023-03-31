import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
/*一级路由*/
import indexPage from "@/pages/indexPage.vue"

const routes:RouteRecordRaw[] = [
    //首页
    {
        path: "/",
        name: "home",
        redirect:'/index',
    },
    {
        path: '/index',
        name: 'index',
        component: indexPage,
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router