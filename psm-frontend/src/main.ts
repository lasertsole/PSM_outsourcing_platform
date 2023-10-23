import { createApp } from 'vue';
import '@/style.css';
import App from '@/App.vue';

//引入router
import router from '@/router';

//引入pinia临时存储和持久化存储
import store from '@/stores';
import { accountInfoStore, initAccountInfo }  from "@/stores/accountInfoStore";
import { showcaseInfoStore, initShowcaseInfo }  from "@/stores/showcaseInfoStore";


//引入Element-Plus
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import zhCn from 'element-plus/dist/locale/zh-cn.mjs';
import { ElMessage } from 'element-plus';

//引入apollo-client
import ApolloClient from 'apollo-boost';

//引入事件总线mitt
import mitt from 'mitt';

const app = createApp(App);

app.use(router);
app.use(store);
app.use(ElementPlus,{locale: zhCn,});//使用中国版element-plus

//apollo-client配置
export const apolloClient = new ApolloClient({
	uri: 'api/graphql',

	request:(operation:any)=>{
		if(accountInfo.token){
			operation.setContext({
				headers:{
					Token:accountInfo.token
				}
			})
		}
	},

	onError:({ graphQLErrors, networkError })=>{
		if (graphQLErrors) {
			graphQLErrors.forEach((item:{message:string}) => {
				ElMessage.error(item.message);
			});
		}
		if (networkError) {
			ElMessage.error("网络状况不佳,请检查网络连接");
		}
	}
})

//apollo-client信息全局化
app.config.globalProperties.apolloClient = apolloClient;

//服务器地址全局化
app.config.globalProperties.serverUrl = "http://"+import.meta.env.VITE_URL;
app.config.globalProperties.serverWS = "ws://"+import.meta.env.VITE_URL;

//pinia信息全局化
let accountInfo = accountInfoStore();
app.config.globalProperties.accountInfo = accountInfo;

let showcaseInfo = showcaseInfoStore();
app.config.globalProperties.showcaseInfo = showcaseInfo;

//事件总线全局化
let Bus = mitt();
app.config.globalProperties.Bus =Bus;

//给pinia传全局变量
initAccountInfo(app.config.globalProperties);
initShowcaseInfo(app.config.globalProperties);

//给路由守卫传用户信息变量
router.initRouterGuard(accountInfo);

//虚拟节点挂载到app节点
app.mount('#app')