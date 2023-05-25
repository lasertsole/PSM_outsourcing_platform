import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
/*一级路由*/
import indexPageVue from "@/pages/indexPage/indexPage.vue"//首页
import loginAndResigterVue from '@/pages/loginAndResigter/loginAndResigter.vue'//登录注册页
import showcaseVue from '@/pages/showcase/showcase.vue'//橱窗页
import planningVue from '@/pages/planning/planning.vue'//企划页
import worksVue from '@/pages/works/works.vue'//作品页
import personSpaceVue from '@/pages/personSpace/personSpace.vue'//个人空间页面
import personInfo from "@/pages/personInfo/personInfo.vue"//个人信息页面

/*二级路由*/
//橱窗页面
import montageVue from '@/pages/showcase/montage.vue'
import artVue from '@/pages/showcase/art.vue'
import translateVue from "@/pages/showcase/translate.vue"
import captionsVue from '@/pages/showcase/captions.vue'
//企划页面
import personVue from "@/pages/planning/person.vue"
import commerceVue from "@/pages/planning/commerce.vue"
//协议页面
import contractVue from "@/pages/contract/contract.vue"

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
        component: indexPageVue,
    },
    {
        path: '/loginAndRegister',
        name: 'loginAndRegister',
        component: loginAndResigterVue,
    },
    //橱窗页
    {
        path: '/showcase',
        name: 'showcase',
        component: showcaseVue,
        redirect:'/showcase/montage',
        children:[
            //剪辑类
            {
                path: 'montage',
                name: "montage",
                component: montageVue
            },
            {
                path: "/",
                redirect:'montage',
            },
            //美工类
            {
                path: 'art',
                name: "art",
                component: artVue
            },
            //翻译类
            {
                path: 'translate',
                name: "translate",
                component: translateVue
            },
            //字幕类
            {
                path: 'captions',
                name: "captions",
                component: captionsVue
            },
        ]
    },
    //企划页
    {
        path: '/planning',
        name: 'planning',
        component: planningVue,
        redirect:'/planning/person',
        children:[
            //个人
            {
                path: 'person',
                name: "person",
                component: personVue
            },
            {
                path: "/",
                redirect:'person',
            },
            //商业
            {
                path: 'commerce',
                name: "commerce",
                component: commerceVue
            },
        ]
    },
    //作品页
    {
        path: '/works',
        name: 'works',
        component: worksVue,
    },
    //合同页
    {
        path: '/contract',
        name: 'contract',
        component: contractVue,
    },
    //个人空间页
    {
        path: '/personSpace',
        name: 'personSpace',
        component: personSpaceVue,
    },
    //个人信息页
    {
        path: '/personInfo',
        name: 'personInfo',
        component: personInfo,
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router