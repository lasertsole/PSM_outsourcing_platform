import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
/*一级路由*/
import indexPage from "@/pages/indexPage.vue"
import loginAndResigterVue from '@/pages/loginAndResigter.vue'
import showcaseVue from '@/pages/showcase.vue'
import planningVue from '@/pages/planning.vue'
import worksVue from '@/pages/works.vue'

/*二级路由*/
//橱窗下
import montageVue from '@/pages/showcase/montage.vue'
import artVue from '@/pages/showcase/art.vue'
import translateVue from "@/pages/showcase/translate.vue"
import captionsVue from '@/pages/showcase/captions.vue'

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
        redirect:'/showcase/montage',
        children:[
            //新作品
            {
                path: 'montage',
                name: "montage",
                component: montageVue
            },
            {
                path: "/",
                redirect:'montage',
            },
            {
                path: 'art',
                name: "art",
                component: artVue
            },
            {
                path: 'translate',
                name: "translate",
                component: translateVue
            },
            {
                path: 'captions',
                name: "captions",
                component: captionsVue
            },
        ]
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