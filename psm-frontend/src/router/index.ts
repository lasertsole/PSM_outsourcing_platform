import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
/*一级路由*/
import indexPage from "@/pages/indexPage.vue"
import loginAndResigterVue from '@/pages/loginAndResigter.vue'
import showcaseVue from '@/pages/showcase.vue'
import planningVue from '@/pages/planning.vue'
import worksVue from '@/pages/works.vue'

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
    },
    {
        path: '/loginAndRegister',
        name: 'loginAndRegister',
        component: loginAndResigterVue,
    },
    {
        path: '/showcase',
        name: 'showcase',
        component: showcaseVue,
    },
    {
        path: '/planning',
        name: 'planning',
        component: planningVue,
    },
    {
        path: '/works',
        name: 'works',
        component: worksVue,
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router