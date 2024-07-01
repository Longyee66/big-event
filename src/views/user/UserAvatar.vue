<script setup>
import { Plus } from '@element-plus/icons-vue'
import { ref } from 'vue'
import avatar from '@/assets/default.png'
const uploadRef = ref()

//回调用户头像
import { userInfoStore } from '@/stores/userInfo'
const infoStore = userInfoStore();
//用户头像地址
const imgUrl = ref(infoStore.userInfo.userPic)

import { useTokenStore } from '@/stores/token'
const tokenStore = useTokenStore();

//回调函数
const uploadSuccess = (result) => {
    imgUrl.value = result.data;
}

//上传头像
import { userAvatarUploadService } from '@/api/user';
import { ElMessage } from 'element-plus';
const uploadAvatar=async ()=>{
    let result=await userAvatarUploadService(imgUrl.value);
    if(result.code===0){
        ElMessage.success(result.message?result.message:'上传成功')
        infoStore.userInfo.userPic=imgUrl.value
    }
    

}

</script>

<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>更换头像</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <!-- auto-upload:设置是否自动上传
                        action:设置服务器接口路径
                        name:设置上传的文件字段名
                        headers:设置上传的请求头
                        on-success:上传成功的回调函数 -->
                <el-upload ref="uploadRef" class="avatar-uploader" :auto-upload="true" :show-file-list="false"
                    action="/api/upload" name="file" :headers="{ 'Authorization': tokenStore.token }"
                    :on-success="uploadSuccess">
                    <img v-if="imgUrl" :src="imgUrl" class="avatar" />
                    <img v-else :src="avatar" width="278" />
                </el-upload>
                <br />
                <el-button type="primary" :icon="Plus" size="large"
                    @click="uploadRef.$el.querySelector('input').click()">
                    选择图片
                </el-button>
                <el-button type="success" :icon="Upload" size="large" @click="uploadAvatar">
                    上传头像
                </el-button>
            </el-col>
        </el-row>
    </el-card>
</template>

<style lang="scss" scoped>
.avatar-uploader {
    :deep() {
        .avatar {
            width: 278px;
            height: 278px;
            display: block;
        }

        .el-upload {
            border: 1px dashed var(--el-border-color);
            border-radius: 6px;
            cursor: pointer;
            position: relative;
            overflow: hidden;
            transition: var(--el-transition-duration-fast);
        }

        .el-upload:hover {
            border-color: var(--el-color-primary);
        }

        .el-icon.avatar-uploader-icon {
            font-size: 28px;
            color: #8c939d;
            width: 278px;
            height: 278px;
            text-align: center;
        }
    }
}
</style>