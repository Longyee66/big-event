import { defineStore } from "pinia";
import { ref } from "vue";
//定义数据模型
const userInfo = ref({})

export const userInfoStore = defineStore("userInfo", () => {
    //定义数据模型
    const userInfo = ref({})
    //定义接收数据
    const setUserInfo = (newUserInfo) => {
        userInfo.value = newUserInfo
    }
    //移除数据
    const removeUserInfo = () => {
        info.value = {}
    }
    return { userInfo, setUserInfo, removeUserInfo }

},
    { persist: true }
)