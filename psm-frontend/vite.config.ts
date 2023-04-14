import { defineConfig, optimizeDeps } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path' //引入path模块

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      vue: "vue/dist/vue.esm-bundler.js",
      '@': path.resolve(__dirname, 'src'), //配置@路径
    }
  },
  //在预构建中强制排除某依赖项
  optimizeDeps:{
    exclude:[]
  }
})