import { createRouter, createWebHashHistory, RouteRecordRaw, Router } from 'vue-router'
/*一级路由*/
import indexPageVue from "@/pages/indexPage/indexPage.vue"//首页
import loginAndResigterVue from '@/pages/loginAndResigter/loginAndResigter.vue'//登录注册页
import showcaseVue from '@/pages/showcase/showcase.vue'//橱窗页
import showcaseDetailVue from "@/pages/showcaseDetail/showcaseDetail.vue"//橱窗详情页
import planningVue from '@/pages/planning/planning.vue'//企划页
import planningDetailVue from "@/pages/planningDetail/planningDetail.vue"//橱窗详情页
import worksVue from '@/pages/works/works.vue'//作品页
import personSpaceVue from '@/pages/personSpace/personSpace.vue'//个人空间页面
import personInfo from "@/pages/personInfo/personInfo.vue"//个人信息页面
import accountModify from "@/pages/accountModify/accountModify.vue"//修改个人信息页

/*二级路由*/
//登录注册页
import loginVue from '@/pages/loginAndResigter/login.vue'//登录页
import registerVue from '@/pages/loginAndResigter/register.vue'//注册页
import myShowcase from '@/pages/personInfo/myShowcase.vue'//personInfo的企划页
import myPlanning from '@/pages/personInfo/myPlanning.vue'//personInfo的企划页

//橱窗详情页
import showcaseDetailInfo from "@/pages/showcaseDetail/showcaseDetailInfo.vue"
import createPhase from "@/pages/showcaseDetail/createPhase.vue"
import commendOfShowcase from "@/pages/showcaseDetail/commendOfShowcase.vue"
//企划页面
import personVue from "@/pages/planning/person.vue"
import commerceVue from "@/pages/planning/commerce.vue"
//作品页面
import recommend from "@/pages/works/recommend.vue"
import hot from "@/pages/works/hot.vue"
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
        children:[
            //登录
            {
                path: 'login',
                name: "login",
                component: loginVue
            },
            {
                path: "/",
                redirect:'login',
            },
            //注册
            {
                path: 'register',
                name: "register",
                component: registerVue
            },
        ]
    },
    //橱窗页
    {
        path: '/showcase',
        name: 'showcase',
        component: showcaseVue,
    },
    //橱窗页详情页
    {
        path: '/showcaseDetail',
        name: 'showcaseDetail',
        component: showcaseDetailVue,
        redirect:'/showcaseDetail/showcaseDetailInfo',

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
    //企划详情页
    {
        path: '/planningDetail',
        name: 'planningDetail',
        component: planningDetailVue,
    },
    //作品页
    {
        path: '/works',
        name: 'works',
        component: worksVue,
        redirect:'/works/recommend',
        children:[
            //个人
            {
                path: 'recommend',
                name: "recommend",
                component: recommend
            },
            {
                path: "/",
                redirect:'recommend',
            },
            //商业
            {
                path: 'hot',
                name: "hot",
                component: hot
            },
        ]
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
        redirect:'/personInfo/myShowcase',
        children:[
            //个人需求页
            {
                path: 'myShowcase',
                name: "myShowcase",
                component: myShowcase
            },
            {
                path: "/",
                redirect:'myShowcase',
            },
            //个人企划页
            {
                path: 'myPlanning',
                name: "myPlanning",
                component: myPlanning
            },
        ]
    },
    //个人信息修改页
    {
        path: '/accountModify',
        name: 'accountModify',
        component: accountModify,
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

const logOutAccessPageArr:any[]=["index", "login", "register"];//不登录能访问的权限页面
const loginNotAccessPageArr:any[]=["index", "login", "register"];//登录后不能访问的权限页面

router.beforeResolve((to, from, next) => {
    
    const {isOnline} = storeToRefs(UserInfo);

    if(logOutAccessPageArr.indexOf(to.name)==-1&&!isOnline.value){//未登录用户访问未授权页面
        next({
            path: '/index',
            query: {}
        });
    }
    else if(loginNotAccessPageArr.indexOf(to.name)!=-1&&isOnline.value){//登录用户访问未授权页面
        next({
            path: '/personInfo',
            query: {}
        });
    }
    else{
        next();
    }
})

export default router