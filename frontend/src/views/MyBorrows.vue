<template>
  <div class="my-borrows">
    <el-card>
      <template #header>
        <h2>我的借阅</h2>
      </template>
      <el-table :data="borrows" style="width: 100%">
        <el-table-column prop="bookTitle" label="图书名称" />
        <el-table-column prop="borrowDate" label="借阅日期" />
        <el-table-column prop="dueDate" label="应还日期" />
        <el-table-column prop="returnDate" label="归还日期" />
        <el-table-column prop="status" label="状态">
          <template #default="{ row }">
            <el-tag :type="getStatusType(row.status)">{{ getStatusText(row.status) }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button 
              v-if="row.status === 'BORROWED' || row.status === 'OVERDUE'"
              type="primary" 
              size="small"
              @click="handleReturn(row.id)"
            >
              归还
            </el-button>
            <el-button 
              v-if="row.status === 'BORROWED'"
              type="warning" 
              size="small"
              @click="handleRenew(row.id)"
            >
              续借
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getMyBorrows, returnBook, renewBook } from '../api/borrow'
import { ElMessage } from 'element-plus'

const borrows = ref([])

const loadBorrows = async () => {
  const { data } = await getMyBorrows()
  if (data.code === 200) {
    borrows.value = data.data
  }
}

const handleReturn = async (id) => {
  const { data } = await returnBook(id)
  if (data.code === 200) {
    ElMessage.success('归还成功')
    loadBorrows()
  } else {
    ElMessage.error(data.message)
  }
}

const handleRenew = async (id) => {
  const { data } = await renewBook(id)
  if (data.code === 200) {
    ElMessage.success('续借成功')
    loadBorrows()
  } else {
    ElMessage.error(data.message)
  }
}

const getStatusType = (status) => {
  const map = { BORROWED: 'primary', RETURNED: 'success', OVERDUE: 'danger' }
  return map[status] || 'info'
}

const getStatusText = (status) => {
  const map = { BORROWED: '借阅中', RETURNED: '已归还', OVERDUE: '已超期' }
  return map[status] || status
}

onMounted(() => {
  loadBorrows()
})
</script>

<style scoped>
.my-borrows {
  padding: 20px;
}
</style>
