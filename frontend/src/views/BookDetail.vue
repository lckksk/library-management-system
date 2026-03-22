<template>
  <div class="book-detail">
    <el-card>
      <el-row :gutter="40">
        <el-col :span="8">
          <el-image :src="book.coverImage || defaultCover" fit="cover" style="width: 100%;" />
        </el-col>
        <el-col :span="16">
          <h1>{{ book.title }}</h1>
          <p><strong>作者：</strong>{{ book.author }}</p>
          <p><strong>出版社：</strong>{{ book.publisher }}</p>
          <p><strong>ISBN：</strong>{{ book.isbn }}</p>
          <p><strong>分类：</strong>{{ book.categoryName }}</p>
          <p><strong>库存：</strong>{{ book.availableCount }} / {{ book.totalCount }}</p>
          <p><strong>简介：</strong>{{ book.description }}</p>
          <el-button 
            type="primary" 
            :disabled="book.availableCount <= 0"
            @click="handleBorrow"
          >
            {{ book.availableCount > 0 ? '借阅' : '已借完' }}
          </el-button>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useUserStore } from '../stores/user'
import { getBook } from '../api/book'
import { borrowBook } from '../api/borrow'
import { ElMessage } from 'element-plus'

const route = useRoute()
const router = useRouter()
const userStore = useUserStore()
const book = ref({})
const defaultCover = 'https://via.placeholder.com/300x400?text=No+Cover'

const loadBook = async () => {
  const { data } = await getBook(route.params.id)
  if (data.code === 200) {
    book.value = data.data
  }
}

const handleBorrow = async () => {
  if (!userStore.token) {
    router.push('/login')
    return
  }
  const { data } = await borrowBook(book.value.id)
  if (data.code === 200) {
    ElMessage.success('借阅成功')
    loadBook()
  } else {
    ElMessage.error(data.message)
  }
}

onMounted(() => {
  loadBook()
})
</script>

<style scoped>
.book-detail {
  padding: 20px;
}
h1 {
  margin-bottom: 20px;
}
p {
  margin-bottom: 10px;
}
</style>
