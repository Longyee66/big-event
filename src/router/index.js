//导入vue-router
import { createRouter, createWebHistory } from "vue-router";
//导入组建
import LoginVue from '@/views/Login.vue'
import LayoutVue from "@/views/Layout.vue"
import ArticleCategoryVue from "@/views/article/ArticleCategory.vue"
import ArticleManageVue from "@/views/article/ArticleManage.vue";
import UserAvatarVue from "@/views/user/UserAvatar.vue";
import UserInfoVue from "@/views/user/UserInfo.vue";
import UserResetPasswordVue from "@/views/user/UserResetPassword.vue";
//自定义路由
const routes = [
    {
        path: '/login', component: LoginVue
    },

    {
        path: '/', component: LayoutVue,
        redirect: '/article/manage',//重定向，默认首页位置
        children: [
            { path: '/article/category', component: ArticleCategoryVue },
            { path: '/article/manage', component: ArticleManageVue },
            { path: '/user/info', component: UserInfoVue },
            { path: '/user/avatar', component: UserAvatarVue },
            { path: '/user/resetPassword', component: UserResetPasswordVue }
        ]
    }


]

//创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes: routes
});

export default router;