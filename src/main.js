import { createApp } from 'vue'


import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import App from './App.vue'
//pinia存储导入
import { createPinia } from 'pinia'
//使用route
import router from '@/router'
//持久化插件
import { createPersistedState } from 'pinia-persistedstate-plugin'
//分页插件中文
import locale from 'element-plus/dist/locale/zh-cn.js'

const app = createApp(App)
const pinia=createPinia(App);
const persist=createPersistedState(App)


app.use(ElementPlus,{locale})
app.use(router)
app.use(pinia)
pinia.use(persist)

app.mount('#app')
