<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'


const categorys = ref([
    {
        "id": 3,
        "categoryName": "美食",
        "categoryAlias": "my",
        "createTime": "2023-09-02 12:06:59",
        "updateTime": "2023-09-02 12:06:59"
    },
    {
        "id": 4,
        "categoryName": "娱乐",
        "categoryAlias": "yl",
        "createTime": "2023-09-02 12:08:16",
        "updateTime": "2023-09-02 12:08:16"
    },
    {
        "id": 5,
        "categoryName": "军事",
        "categoryAlias": "js",
        "createTime": "2023-09-02 12:08:33",
        "updateTime": "2023-09-02 12:08:33"
    }
])

//声明异步函数
import { articleCategoryListService, articleCategoryAddService, articleCategoryUpdateService,articleCategoryDeleteService } from '@/api/article.js'

const articleCategoryList = async () => {
    let result = await articleCategoryListService();
    categorys.value = result.data;
}
//调用方法
articleCategoryList();

//控制添加弹窗
const dialogVisible = ref(false)
//分类数据模型
const categoryModel = ref({
    id:'',
    categoryName: '',
    categoryAlias: ''  //别名
})
//分类表单的参数校验
const rules = {
    categoryName: [
        { required: true, message: '请输入分类名称', trigger: 'blur' },
    ],
    categoryAlias: [
        { required: true, message: '请输入分类别名', trigger: 'blur' },
    ]
}
//添加分类
import { ElMessage ,ElMessageBox} from 'element-plus';

const addCategory = async () => {
    let result = await articleCategoryAddService(categoryModel.value)
    if (result.code === 0) {
        ElMessage.success(result.message ? result.message : '添加成功')
    }
    //刷新数据函数，调用获取文章分类数据函数
    articleCategoryList();
    //关闭窗口
    dialogVisible.value = false;
}
//窗口提示名称
const title = ref({})
//窗口显示、
//数据回显
const showCategory = (row) => {
    title.value = '修改分类'
    dialogVisible.value = true
    //将row中的数据赋值给categoryModel
    categoryModel.value.categoryName=row.categoryName
    categoryModel.value.categoryAlias=row.categoryAlias
    //修改的时候必须传递分类的id，所以扩展一个id属性
    categoryModel.value.id=row.id
}

//编辑分类
const updateCategory = async () => {
    let result = await articleCategoryUpdateService(categoryModel.value)
    if (result.code === 0) {
        ElMessage.success(result.message ? result.message : '修改成功')
    }
    //重新获取分类数据
    articleCategoryList();
    dialogVisible.value = false;
}
const clearData = () => {
    categoryModel.value.categoryName = '';
    categoryModel.value.categoryAlias = ''
}

//删除分类提示框
const deleteCategory =(row)=> {
  ElMessageBox.confirm(
    '是否确定删除该分类',
    '温馨提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(async() => {
    //调用接口
    let result=await articleCategoryDeleteService(row.id);
    if(result.code===0){
      ElMessage({
        type: 'success',
        message: '删除成功',
      })
      //刷新数据
      articleCategoryList();
    }
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '取消删除',
      })
    })
}


</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>文章分类</span>
                <div class="extra">
                    <el-button type="primary"
                        @click="dialogVisible = true; title = '添加分类'; clearData()">添加分类</el-button>
                </div>
            </div>
        </template>
        <el-table :data="categorys" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="分类名称" prop="categoryName"></el-table-column>
            <el-table-column label="分类别名" prop="categoryAlias"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="showCategory(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteCategory(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>
    </el-card>
    <!-- 添加分类弹窗 -->
    <el-dialog v-model="dialogVisible" :title="title" width="30%">
        <el-form :model="categoryModel" :rules="rules" label-width="100px" style="padding-right: 30px">
            <el-form-item label="分类名称" prop="categoryName">
                <el-input v-model="categoryModel.categoryName" minlength="1" maxlength="10"></el-input>
            </el-form-item>
            <el-form-item label="分类别名" prop="categoryAlias">
                <el-input v-model="categoryModel.categoryAlias" minlength="1" maxlength="15"></el-input>
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="title == '添加分类' ? addCategory() : updateCategory()"> 确认 </el-button>
            </span>
        </template>
    </el-dialog>
</template>

<style lang="scss" scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
</style>