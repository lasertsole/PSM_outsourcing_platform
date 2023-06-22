import { createRouter, createWebHashHistory, RouteRecordRaw, Router } from 'vue-router'
/*一级路由*/
import indexPageVue from "@/pages/indexPage/indexPage.vue"//首页
import loginAndResigterVue from '@/pages/loginAndResigter/loginAndResigter.vue'//登录注册页
import showcaseVue from '@/pages/showcase/showcase.vue'//橱窗页
import showcaseDetailVue from "@/pages/showcaseDetail/showcaseDetail.vue"//橱窗详情页
import planningVue from '@/pages/planning/planning.vue'//企划页
import planningDetailVue from "@/pages/showcase/showcaseDetail.vue"//橱窗详情页
import worksVue from '@/pages/works/works.vue'//作品页
import personSpaceVue from '@/pages/personSpace/personSpace.vue'//个人空间页面
import personInfo from "@/pages/personInfo/personInfo.vue"//个人信息页面

/*二级路由*/
//橱窗页面
import montageVue from '@/pages/showcase/montage.vue'
import artVue from '@/pages/showcase/art.vue'
import translateVue from "@/pages/showcase/translate.vue"
import captionsVue from '@/pages/showcase/captions.vue'
//橱窗详情页
import showcaseDetailInfo from "@/pages/showcaseDetail/showcaseDetailInfo.vue"
import createPhase from "@/pages/showcaseDetail/createPhase.vue"
import commendOfShowcase from "@/pages/showcaseDetail/commendOfShowcase.vue"
//企划页面
import personVue from "@/pages/planning/person.vue"
import commerceVue from "@/pages/planning/commerce.vue"
//协议页面
import contractVue from "@/pages/contract/contract.vue"

/*路由守卫*/
import { storeToRefs } from 'pinia'
import { ElMessage } from 'element-plus';

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
    //橱窗页详情页
    {
        path: '/showcaseDetail',
        name: 'showcaseDetail',
        component: showcaseDetailVue,
        children:[
            //橱窗详情
            {
                path: 'showcaseDetailInfo',
                name: "showcaseDetailInfo",
                component: showcaseDetailInfo
            },
            //创作阶段
            {
                path: "createPhase",
                name: "createPhase",
                component: createPhase
            },
            //橱窗评价
            {
                path: 'commendOfShowcase',
                name: "commendOfShowcase",
                component: commendOfShowcase
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

/*路由守卫*/
let UserInfo:any = null;
router.initRouterGuard = function(passUserInfo:any):void{
    UserInfo=passUserInfo;
}

const powerArr:any[]=[];//登录后才能访问的权限页面
const hadPowerCantArr:any[]=["loginAndRegister"];//只有登录后不能访问的权限页面

router.beforeResolve((to, from, next) => {
    
    const {isOnline} = storeToRefs(UserInfo);

    if(hadPowerCantArr.indexOf(to.name)!=-1&&isOnline.value){//判断用户访问的页面是否在权限页面
        next({
            path: '/home',
            query: {}
        });
    }
    else{
        next();
    }
})

export default router