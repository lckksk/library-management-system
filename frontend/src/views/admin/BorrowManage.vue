<template>
  <div class="borrow-manage">
    <el-card>
      <template #header>
        <h2>借阅管理</h2>
      </template>
      <el-table :data="borrows" style="width: 100%">
        <el-table-column prop="userName" label="用户" />
        <el-table-column prop="bookTitle" label="图书" />
        <el-table-column prop="borrowDate" label="借阅日期" />
        <el-table-column prop="dueDate" label="应还日期" />
        <el-table-column prop="returnDate" label="归还日期" />
        <el-table-column prop="status" label="状态">
          <template #default="{ row }">
            <el-tag :type="getStatusType(row.status)">{{ getStatusText(row.status) }}</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'

const borrows = ref([])

const loadBorrows = async () => {
  ElMessage.info('借阅管理功能开发中')
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
.borrow-manage {
  padding: 20px;
}
</style>
