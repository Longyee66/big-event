//定义store
import { defineStore } from "pinia";
import { ref } from 'vue'

/* 
第一个参数：名字，唯一
第二个参数：函数
返回值：函数
*/

export const useTokenStore = defineStore('token', () => {
    //定义状态

    //1.响应变量
    const token = ref('')
    //2.定义一个函数用来接收token值
    const setToken = (newToken) => {
        token.value = newToken
    }
    //3.移除token
    const removeToken = () => {
        token.value = ''
    }
    return {
        token, setToken, removeToken
    }
}
,
    { persist: true }

)
