import request from '@/utils/request.js'
import { useTokenStore } from '@/stores/token'
import { resultProps } from 'element-plus'
//文章分类列表查询
export const articleCategoryListService = () => {
    // const tokenStore=useTokenStore();
    /* 
    let token=tokenStore.token;
    return request.get('/category',{headers:{'Authorization':token}}) */
    return request.get('/category')
}

//添加文章分类
export const articleCategoryAddService = (categoryDate) => {
    return request.post('/category', categoryDate)
}
//修改文章分类
export const articleCategoryUpdateService = (categoryDate) => {
    return request.put('/category',categoryDate)
}
//删除文章分类
export const articleCategoryDeleteService=(id)=>{
    return request.delete('/category?id='+id)
}

//获取文章列表
export const articleListService=(params)=>{
    return request.get('/article',{params:params})
}

//添加文章
export const articleAddService=(articleData)=>{
    return request.post("/article",articleData)
}

//删除文章
export const articleDeleteService=(id)=>{
    return request.delete('article?id='+id)
}
//修改文章
export const articleUpdateService=(articleData)=>{
    return request.put('/article',articleData)
}