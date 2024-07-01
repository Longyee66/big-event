//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';
//定义一个变量,记录公共的前缀  ,  baseURL
const baseURL = '/api';
const instance = axios.create({ baseURL })
import router from '@/router';
import { ElMessage } from 'element-plus'
//添加响应拦截器
instance.interceptors.response.use(
    result => {
        return result.data;
    },
    err => {
        // alert("服务器异常");
        if (err.response.status == 401) {
            ElMessage.error("请先登录")
            router.push('/login')
        } else {
            ElMessage.error('服务器异常')
            return Promise.reject(err);//异步的状态转化成失败的状态
        }
    }
)
export default instance;
//添加请求拦截器
import { useTokenStore } from '@/stores/token';

instance.interceptors.request.use(
    (config) => {
        // 请求前的回调
        //添加token
        const tokenStore = useTokenStore();
        //判断请求中token
        if (tokenStore.token) {
            config.headers.Authorization = tokenStore.token
        }
        return config;
    },
    (err) => {
        //请求失败回调
        Promise.reject(err)
    }
)

