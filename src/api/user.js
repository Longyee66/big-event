//导入请求工具
import request from '@/utils/request.js'

//注册接口函数
export const userRegisterService = (registerData) => {
    //传递多个参数URLSearchParams
    const params = new URLSearchParams()
    for (let key in registerData) {
        params.append(key, registerData[key])
    }
    return request.post('/user/register', params);
}

//登录接口函数
export const userLoginService=(loginData)=>{
    const params=new URLSearchParams()
    for(let key in loginData){
        params.append(key,loginData[key])
    }
    return request.post('/user/login',params);
}

//获取用户详细信息
export const userInfoService=()=>{
    return request.get('user/userInfo')
}

//修改用户基本信息
export const userInfoUpdateService=(userInfoData)=>{
    return request.put('user/update',userInfoData)
}

//更换头像
export const userAvatarUploadService=(avatarUrl)=>{
    // const params=new URLSearchParams();
    // params.append('avatarUrl',avatarUrl)
    return request.patch('/user/updateAvatar?avatarUrl='+avatarUrl)
}

//修改密码
export const userPasswordUpdateService=(passwordData)=>{
    return request.patch('/user/updatePwd',passwordData)
}
