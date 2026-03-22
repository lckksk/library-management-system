<template>
  <div class="dashboard">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card>
          <el-statistic title="总图书数" :value="stats.bookCount" />
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <el-statistic title="总用户数" :value="stats.userCount" />
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <el-statistic title="借阅次数" :value="stats.borrowCount" />
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <el-statistic title="待归还" :value="stats.pendingCount" />
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const stats = ref({
  bookCount: 100,
  userCount: 50,
  borrowCount: 200,
  pendingCount: 30
})
</script>

<style scoped>
.dashboard {
  padding: 20px;
}
</style>
