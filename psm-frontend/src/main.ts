import { createApp } from 'vue'
import '@/style.css'
import App from '@/App.vue'

//引入router
import router from '@/router'

//引入pinia临时存储和持久化存储
import store from '@/stores'
import { useMainStore, useWS, initGlobal }  from "@/stores/main.js"

//引入Element-Plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import { ElMessage } from 'element-plus';

//引入axios
import axios from 'axios'

//引入事件总线mitt
import mitt from 'mitt'

const app = createApp(App);

app.use(router);
app.use(store);
app.use(ElementPlus,{locale: zhCn,});//使用中国版element-plus

//服务器地址全局化
app.config.globalProperties.serverUrl = "http://"+import.meta.env.VITE_URL;
app.config.globalProperties.serverWS = "ws://"+import.meta.env.VITE_URL;

//用户信息全局化
let mainStore = useMainStore();
app.config.globalProperties.UserInfo = mainStore;

//websocket全局化
let WS = useWS();
app.config.globalProperties.WS = WS;

//事件总线全局化
let Bus = mitt();
app.config.globalProperties.Bus =Bus;

//给pinia传全局变量
initGlobal(app.config.globalProperties);

//给路由守卫传用户信息变量
router.initRouterGuard(mainStore);

//axios请求拦截器
axios.interceptors.request.use((config)=>{
	if(mainStore.token){
		config.headers['token'] = mainStore.token;
	}
	return config;
});

//axios响应拦截器
axios.interceptors.response.use((res)=>{
	if(res.data.code!="200"){
		ElMessage({
		showClose: true,
		message: res.data.msg,
		type: 'error'
	  });
	  return Promise.reject(res.data);
	}
	return res;
});

//虚拟节点挂载到app节点
app.mount('#app')