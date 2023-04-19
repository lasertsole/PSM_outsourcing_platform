import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path' //引入path模块

export default defineConfig(({command, mode}) => {
  const env = loadEnv(mode, './');//获取环境变量
  return {
    base:"./",//更改根目录,打包后根目录会变化
    plugins: [vue()],
    resolve: {
      alias: {
        vue: "vue/dist/vue.esm-bundler.js",
        '@': path.resolve(__dirname, 'src'), //配置@路径
      }
    },
    optimizeDeps:{
      exclude:[]//在预构建中强制排除某依赖项
    },
    server:{
      open: false,//项目启动时是否打开页面
      host: '127.0.0.1',//主机地址
      port: 3456,//启动端口
      proxy: {//跨域代理
        "^/api/": {//拦截以/api/开头的请求路径，拦截后进行修改，再重新发送
          target: env.VITE_API_URL,// 跨域的域名(不需要写路径)
          ws: false,//是否启用websocket
          changeOrigin: true, /*允许跨域*/
          rewrite: (path) => path.replace(/^\/api/, ''),//路径重写把/api变为空字符
        }
      }
    }
  }
})