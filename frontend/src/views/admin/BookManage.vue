<template>
  <div class="book-manage">
    <el-card>
      <template #header>
        <h2>图书管理</h2>
      </template>
      <el-table :data="books" style="width: 100%">
        <el-table-column prop="title" label="书名" />
        <el-table-column prop="author" label="作者" />
        <el-table-column prop="categoryName" label="分类" />
        <el-table-column prop="totalCount" label="总数" />
        <el-table-column prop="availableCount" label="可借" />
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button type="primary" size="small" @click="handleEdit(row)">编辑</el-button>
            <el-button type="danger" size="small" @click="handleDelete(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getBooks, deleteBook } from '../../api/book'
import { ElMessage } from 'element-plus'

const books = ref([])

const loadBooks = async () => {
  const { data } = await getBooks({ page: 1, size: 100 })
  if (data.code === 200) {
    books.value = data.data.list
  }
}

const handleEdit = (book) => {
  ElMessage.info('编辑功能开发中')
}

const handleDelete = async (id) => {
  const { data } = await deleteBook(id)
  if (data.code === 200) {
    ElMessage.success('删除成功')
    loadBooks()
  } else {
    ElMessage.error(data.message)
  }
}

onMounted(() => {
  loadBooks()
})
</script>

<style scoped>
.book-manage {
  padding: 20px;
}
</style>
