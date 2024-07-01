import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'node:path'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue(),
    ],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    }
    ,

    //用来解决跨域问题
    server: {
        proxy: {
            '/api': {//获取路径中包含了/api请求
                target: 'http://localhost:7070/admin',//后台服务器
                changeOrigin: true,//修改源
                rewrite: (path) => path.replace(/^\/api/, '')//api替换为''
            }
        }
    }
})
