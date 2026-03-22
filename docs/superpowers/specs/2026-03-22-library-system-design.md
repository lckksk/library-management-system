# 图书管理系统设计文档

**创建时间**：2026-03-22
**状态**：已批准
**技术栈**：Vue 3 + Spring Boot + MySQL

---

## 1. 项目概述

一个功能完善的图书管理系统，支持读者和管理员两种角色，提供图书管理、借阅管理、用户管理等核心功能。

### 1.1 技术栈

- **前端**：Vue 3 + Vite + Vue Router + Pinia + Axios + Element Plus
- **后端**：Spring Boot 3 + MyBatis + MySQL 8
- **构建工具**：Maven（后端）、Vite（前端）
- **认证**：JWT Token

### 1.2 项目结构

```
library/
├── backend/          # Spring Boot 后端
│   ├── src/main/java/
│   └── pom.xml
├── frontend/         # Vue 3 前端
│   ├── src/
│   └── package.json
└── README.md
```

---

## 2. 用户角色与功能

### 2.1 读者（READER）

**核心功能**：
- 用户注册/登录
- 搜索图书（支持关键词、分类筛选）
- 查看图书详情
- 借阅图书
- 归还图书
- 查看借阅记录
- 个人信息管理

### 2.2 管理员（ADMIN）

**核心功能**：
- 图书管理（增删改查）
- 分类管理
- 用户管理（查看、启用/禁用）
- 借阅管理（查看所有借阅记录）
- 借阅统计（图表展示）

---

## 3. 数据库设计

### 3.1 用户表（users）

```sql
CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100),
  phone VARCHAR(20),
  role ENUM('READER', 'ADMIN') DEFAULT 'READER',
  status TINYINT DEFAULT 1,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

### 3.2 图书分类表（categories）

```sql
CREATE TABLE categories (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(500),
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

### 3.3 图书表（books）

```sql
CREATE TABLE books (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  isbn VARCHAR(20) UNIQUE,
  title VARCHAR(200) NOT NULL,
  author VARCHAR(100) NOT NULL,
  publisher VARCHAR(100),
  category_id BIGINT,
  total_count INT DEFAULT 0,
  available_count INT DEFAULT 0,
  description TEXT,
  cover_image VARCHAR(500),
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (category_id) REFERENCES categories(id)
);
```

### 3.4 借阅记录表（borrow_records）

```sql
CREATE TABLE borrow_records (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  book_id BIGINT NOT NULL,
  borrow_date DATE NOT NULL,
  due_date DATE NOT NULL,
  return_date DATE,
  status ENUM('BORROWED', 'RETURNED', 'OVERDUE') DEFAULT 'BORROWED',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (book_id) REFERENCES books(id)
);
```

---

## 4. 后端API设计

### 4.1 认证相关

| 方法 | 端点 | 描述 |
|------|------|------|
| POST | /api/auth/login | 用户登录 |
| POST | /api/auth/register | 用户注册 |
| POST | /api/auth/logout | 用户登出 |

### 4.2 用户相关

| 方法 | 端点 | 描述 | 权限 |
|------|------|------|------|
| GET | /api/users/profile | 获取当前用户信息 | 登录用户 |
| PUT | /api/users/profile | 更新用户信息 | 登录用户 |
| GET | /api/users | 获取所有用户 | 管理员 |
| PUT | /api/users/{id}/status | 启用/禁用用户 | 管理员 |

### 4.3 图书相关

| 方法 | 端点 | 描述 | 权限 |
|------|------|------|------|
| GET | /api/books | 搜索图书 | 公开 |
| GET | /api/books/{id} | 获取图书详情 | 公开 |
| POST | /api/books | 添加图书 | 管理员 |
| PUT | /api/books/{id} | 更新图书 | 管理员 |
| DELETE | /api/books/{id} | 删除图书 | 管理员 |

### 4.4 分类相关

| 方法 | 端点 | 描述 | 权限 |
|------|------|------|------|
| GET | /api/categories | 获取所有分类 | 公开 |
| POST | /api/categories | 添加分类 | 管理员 |
| PUT | /api/categories/{id} | 更新分类 | 管理员 |
| DELETE | /api/categories/{id} | 删除分类 | 管理员 |

### 4.5 借阅相关

| 方法 | 端点 | 描述 | 权限 |
|------|------|------|------|
| POST | /api/borrows | 借阅图书 | 读者 |
| PUT | /api/borrows/{id}/return | 归还图书 | 读者 |
| GET | /api/borrows/my | 查看我的借阅记录 | 读者 |
| GET | /api/borrows | 查看所有借阅记录 | 管理员 |
| GET | /api/borrows/statistics | 借阅统计 | 管理员 |

---

## 5. 前端页面设计

### 5.1 页面结构

```
公共页面：
├── 登录页        # /login
├── 注册页        # /register
└── 首页          # / (图书列表、搜索)

读者页面：
├── 图书详情      # /books/:id
├── 我的借阅      # /my/borrows
└── 个人中心      # /profile

管理员页面：
├── 管理后台      # /admin
├── 图书管理      # /admin/books
├── 用户管理      # /admin/users
├── 借阅管理      # /admin/borrows
└── 借阅统计      # /admin/statistics
```

### 5.2 页面功能

1. **首页**：图书列表（分页）、搜索框、分类筛选
2. **图书详情**：图书信息、借阅按钮、库存状态
3. **我的借阅**：借阅记录、归还按钮
4. **管理后台**：图书增删改查、用户管理、借阅统计图表

### 5.3 UI组件

- Element Plus 组件库
- 响应式布局
- 深色/浅色主题切换

---

## 6. 安全设计

1. **密码加密**：使用BCrypt加密存储密码
2. **JWT认证**：使用JWT Token进行用户认证
3. **权限控制**：基于角色的访问控制（RBAC）
4. **输入验证**：前后端双重验证用户输入
5. **SQL注入防护**：使用MyBatis参数化查询

---

## 7. 开发计划

1. **后端开发**
   - 数据库初始化
   - 实体类和Mapper
   - Service层实现
   - Controller层实现
   - JWT认证实现

2. **前端开发**
   - 项目初始化
   - 路由配置
   - 状态管理
   - 页面组件开发
   - API对接

3. **测试与部署**
   - 单元测试
   - 接口测试
   - 部署文档

---

## 8. 业务规则

### 8.1 借阅规则

- **借阅期限**：默认30天，可续借一次（延长30天）
- **最大借阅数量**：每人最多同时借阅5本图书
- **超期处理**：超期后无法借阅新图书，需归还超期图书后恢复借阅权限
- **库存检查**：借阅时检查图书available_count > 0

### 8.2 删除约束

- **分类删除**：如果分类下有关联图书，拒绝删除，提示"该分类下存在图书，请先移除或转移图书"
- **图书删除**：如果图书有未归还的借阅记录，拒绝删除，提示"该图书有未归还的借阅记录"
- **用户删除**：不删除用户，仅通过status字段禁用用户

---

## 9. 响应格式规范

### 9.1 统一响应格式

```json
{
  "code": 200,
  "message": "success",
  "data": {}
}
```

### 9.2 分页响应格式

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "list": [],
    "total": 100,
    "page": 1,
    "size": 10
  }
}
```

### 9.3 分页请求参数

- `page`：页码，默认1
- `size`：每页数量，默认10
- `keyword`：搜索关键词（可选）
- `categoryId`：分类ID筛选（可选）

### 9.4 错误码定义

| code | 含义 |
|------|------|
| 200 | 成功 |
| 400 | 请求参数错误 |
| 401 | 未认证 |
| 403 | 无权限 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

---

## 10. 借阅统计

管理员可查看以下统计图表：

1. **按月借阅趋势图**：展示最近12个月的借阅量变化
2. **热门图书Top10**：借阅次数最多的10本图书
3. **用户借阅排行**：借阅次数最多的10位用户

---

## 11. 成功标准

1. 读者可以正常注册、登录、借阅、归还图书
2. 管理员可以管理图书、用户、查看统计
3. 界面美观、响应式设计
4. 数据安全、权限控制有效
5. 代码规范、文档完整
