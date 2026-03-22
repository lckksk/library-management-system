<template>
  <div class="home">
    <el-card>
      <el-row :gutter="20">
        <el-col :span="16">
          <el-input v-model="keyword" placeholder="搜索图书" clearable @keyup.enter="handleSearch">
            <template #append>
              <el-button :icon="Search" @click="handleSearch" />
            </template>
          </el-input>
        </el-col>
        <el-col :span="8">
          <el-select v-model="categoryId" placeholder="选择分类" clearable @change="handleSearch">
            <el-option v-for="cat in categories" :key="cat.id" :label="cat.name" :value="cat.id" />
          </el-select>
        </el-col>
      </el-row>
    </el-card>
    
    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="6" v-for="book in books" :key="book.id">
        <el-card :body-style="{ padding: '0px' }" @click="goToDetail(book.id)">
          <div class="book-cover">
            <el-image :src="book.coverImage || defaultCover" fit="cover" />
          </div>
          <div style="padding: 14px;">
            <h3>{{ book.title }}</h3>
            <p class="author">{{ book.author }}</p>
            <p class="status" :class="{ available: book.availableCount > 0 }">
              {{ book.availableCount > 0 ? '可借阅' : '已借完' }}
            </p>
          </div>
        </el-card>
      </el-col>
    </el-row>
    
    <el-pagination
      v-model:current-page="page"
      v-model:page-size="size"
      :total="total"
      :page-sizes="[10, 20, 50]"
      layout="total, sizes, prev, pager, next, jumper"
      @current-change="loadBooks"
      @size-change="loadBooks"
      style="margin-top: 20px; justify-content: center;"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { Search } from '@element-plus/icons-vue'
import { getBooks } from '../api/book'
import { getCategories } from '../api/category'

const router = useRouter()
const books = ref([])
const categories = ref([])
const keyword = ref('')
const categoryId = ref(null)
const page = ref(1)
const size = ref(10)
const total = ref(0)
const defaultCover = 'https://via.placeholder.com/200x280?text=No+Cover'

const loadBooks = async () => {
  const { data } = await getBooks({
    keyword: keyword.value,
    categoryId: categoryId.value,
    page: page.value,
    size: size.value
  })
  if (data.code === 200) {
    books.value = data.data.list
    total.value = data.data.total
  }
}

const loadCategories = async () => {
  const { data } = await getCategories()
  if (data.code === 200) {
    categories.value = data.data
  }
}

const handleSearch = () => {
  page.value = 1
  loadBooks()
}

const goToDetail = (id) => {
  router.push(`/books/${id}`)
}

onMounted(() => {
  loadBooks()
  loadCategories()
})
</script>

<style scoped>
.home {
  padding: 20px;
}
.book-cover {
  height: 200px;
  overflow: hidden;
}
.book-cover .el-image {
  width: 100%;
  height: 100%;
}
h3 {
  font-size: 16px;
  margin-bottom: 8px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.author {
  color: #666;
  font-size: 14px;
  margin-bottom: 8px;
}
.status {
  color: #999;
  font-size: 12px;
}
.status.available {
  color: #67c23a;
}
</style>
