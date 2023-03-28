import { createApp } from 'vue'
import '/src/style.css'
import App from '/src/App.vue'

//引入router
import router from '/src/router'

//引入Element-Plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const app = createApp(App);

app.use(router);
app.use(ElementPlus);

app.mount('#app')
