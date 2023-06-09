import { createPinia,Pinia } from "pinia"
import piniaPersist from 'pinia-plugin-persist'

const store:Pinia = createPinia();
// 状态持久化-插件配置
store.use(piniaPersist);

export default store