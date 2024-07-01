<script setup>
import { ref } from 'vue'
import { User, Lock } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus';


const passwordData = ref({
    old_pwd: '',
    new_pwd: '',
    re_pwd: ''
})
// 校验密码函数
const checkRePassword = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次确认密码'))
    } else if (value !== passwordData.value.new_pwd) {
        callback(new Error('两次输入密码不一致'))
    } else {
        callback()
    }
}
//校验规则
const rules = {
    old_pwd: [
        { required: true, message: '请输入旧密码', trigger: 'blur' },
        { min: 5, max: 16, message: '5-16为非空字符', trigger: 'blur' }
    ],
    new_pwd: [
        { required: true, message: '请输入新密码', trigger: 'blur' },
        { min: 5, max: 16, message: '5-16为非空字符', trigger: 'blur' }
    ],
    re_pwd: [
        { validator: checkRePassword, trigger: 'blur' }
    ]
}
//确定修改
import { userPasswordUpdateService } from '@/api/user'
import { userInfoStore } from '@/stores/userInfo'
const infoStore = userInfoStore();
import { useTokenStore } from '@/stores/token'
const tokenStore = useTokenStore();
import router from '@/router';

const submitPassword = async () => {
    let result = await userPasswordUpdateService(passwordData.value)
    if (result.code === 0) {
        infoStore.userInfo = ''
        tokenStore.token = ''
        ElMessage.success(result.message ? result.message : '修改成功')
        router.push('/login')
    }
}
//清空密码
const clearData = () => {
    passwordData.value.old_pwd = '',
        passwordData.value.new_pwd = '',
        passwordData.value.re_pwd = ''
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>修改密码</span>
            </div>
        </template>
        <el-form ref="form" style="max-width: 300px" autocomplete="off" :model="passwordData" :rules="rules">
            <el-form-item label="旧密码" prop="old_pwd">
                <el-input :prefix-icon="Lock" type="password" placeholder="请输入旧密码"
                    v-model="passwordData.old_pwd"></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="new_pwd">
                <el-input :prefix-icon="Lock" type="password" placeholder="请输入新密码"
                    v-model="passwordData.new_pwd"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="re_pwd">
                <el-input :prefix-icon="Lock" type="password" placeholder="请再次输入密码"
                    v-model="passwordData.re_pwd"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitPassword">
                    提交
                </el-button>
                <el-button @click="clearData">重置</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>