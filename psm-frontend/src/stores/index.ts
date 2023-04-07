import { createPinia,Pinia } from "pinia"
import { createPersistedState } from "pinia-plugin-persistedstate"

const store:Pinia = createPinia();
// 状态持久化-插件配置
store.use(createPersistedState({
    serializer:{   
        // 指定参数序列化器
        serialize: JSON.stringify,
        deserialize: JSON.parse
    }
}))

export default store