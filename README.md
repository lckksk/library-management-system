# 图书管理系统

一个基于 Vue 3 + Spring Boot + MySQL 的前后端分离图书管理系统，支持读者和管理员两种角色。

## 技术栈

### 前端
- Vue 3
- Vite
- Vue Router 4
- Pinia (状态管理)
- Axios (HTTP客户端)
- Element Plus (UI组件库)

### 后端
- Spring Boot 3.2.0
- MyBatis (ORM框架)
- Spring Security (安全框架)
- JWT (认证授权)
- MySQL 8

## 项目结构

```
library/
├── backend/                      # 后端项目
│   ├── src/main/java/com/library/
│   │   ├── LibraryApplication.java    # 启动类
│   │   ├── config/                    # 配置类
│   │   │   └── CorsConfig.java        # 跨域配置
│   │   ├── controller/                # 控制器
│   │   │   ├── AuthController.java    # 认证控制器
│   │   │   ├── UserController.java    # 用户控制器
│   │   │   ├── BookController.java    # 图书控制器
│   │   │   ├── CategoryController.java # 分类控制器
│   │   │   └── BorrowController.java  # 借阅控制器
│   │   ├── dto/                       # 数据传输对象
│   │   │   ├── ApiResponse.java       # 统一响应格式
│   │   │   ├── LoginRequest.java      # 登录请求
│   │   │   └── RegisterRequest.java   # 注册请求
│   │   ├── entity/                    # 实体类
│   │   │   ├── User.java              # 用户
│   │   │   ├── Book.java              # 图书
│   │   │   ├── Category.java          # 分类
│   │   │   └── BorrowRecord.java      # 借阅记录
│   │   ├── mapper/                    # MyBatis Mapper接口
│   │   ├── service/                   # 业务逻辑层
│   │   │   ├── UserService.java
│   │   │   ├── BookService.java
│   │   │   ├── CategoryService.java
│   │   │   └── BorrowService.java
│   │   └── util/                      # 工具类
│   │       └── JwtUtil.java           # JWT工具
│   └── src/main/resources/
│       ├── application.yml            # 应用配置
│       ├── schema.sql                 # 数据库初始化脚本
│       └── mapper/                    # MyBatis XML映射文件
├── frontend/                     # 前端项目
│   ├── src/
│   │   ├── main.js                    # 入口文件
│   │   ├── App.vue                    # 根组件
│   │   ├── router/index.js            # 路由配置
│   │   ├── stores/user.js             # 用户状态管理
│   │   ├── api/                       # API模块
│   │   │   ├── request.js             # Axios封装
│   │   │   ├── auth.js                # 认证API
│   │   │   ├── book.js                # 图书API
│   │   │   ├── category.js            # 分类API
│   │   │   └── borrow.js              # 借阅API
│   │   └── views/                     # 页面组件
│   │       ├── Login.vue              # 登录页
│   │       ├── Register.vue           # 注册页
│   │       ├── Home.vue               # 首页(图书列表)
│   │       ├── BookDetail.vue         # 图书详情
│   │       ├── MyBorrows.vue          # 我的借阅
│   │       ├── Profile.vue            # 个人中心
│   │       └── admin/                 # 管理员页面
│   │           ├── Dashboard.vue      # 控制台
│   │           ├── BookManage.vue     # 图书管理
│   │           ├── UserManage.vue     # 用户管理
│   │           ├── BorrowManage.vue   # 借阅管理
│   │           └── Statistics.vue     # 借阅统计
│   ├── package.json
│   └── vite.config.js
└── docs/                         # 文档
    ├── superpowers/specs/        # 设计文档
    └── superpowers/plans/        # 实现计划
```

## 功能特性

### 读者功能
- 用户注册/登录
- 搜索图书（支持关键词、分类筛选）
- 查看图书详情
- 借阅图书
- 归还图书
- 续借图书
- 查看借阅记录
- 个人信息管理

### 管理员功能
- 图书管理（增删改查）
- 分类管理
- 用户管理（查看、启用/禁用）
- 借阅管理（查看所有借阅记录）
- 借阅统计（按月趋势、热门图书、用户排行）

## 业务规则

### 借阅规则
- **借阅期限**：默认30天
- **最大借阅数量**：每人最多同时借阅5本图书
- **续借**：可续借一次，延长30天
- **超期处理**：超期后无法借阅新图书，需归还超期图书后恢复借阅权限
- **库存检查**：借阅时检查图书可用库存

### 删除约束
- 分类下有关联图书时，拒绝删除分类
- 图书有未归还的借阅记录时，拒绝删除图书
- 用户不删除，仅通过状态字段禁用

## API接口

### 认证相关
| 方法 | 端点 | 描述 |
|------|------|------|
| POST | /api/auth/login | 用户登录 |
| POST | /api/auth/register | 用户注册 |

### 用户相关
| 方法 | 端点 | 描述 | 权限 |
|------|------|------|------|
| GET | /api/users/profile | 获取当前用户信息 | 登录用户 |
| PUT | /api/users/profile | 更新用户信息 | 登录用户 |
| GET | /api/users | 获取所有用户 | 管理员 |
| PUT | /api/users/{id}/status | 启用/禁用用户 | 管理员 |

### 图书相关
| 方法 | 端点 | 描述 | 权限 |
|------|------|------|------|
| GET | /api/books | 搜索图书 | 公开 |
| GET | /api/books/{id} | 获取图书详情 | 公开 |
| POST | /api/books | 添加图书 | 管理员 |
| PUT | /api/books/{id} | 更新图书 | 管理员 |
| DELETE | /api/books/{id} | 删除图书 | 管理员 |

### 分类相关
| 方法 | 端点 | 描述 | 权限 |
|------|------|------|------|
| GET | /api/categories | 获取所有分类 | 公开 |
| POST | /api/categories | 添加分类 | 管理员 |
| PUT | /api/categories/{id} | 更新分类 | 管理员 |
| DELETE | /api/categories/{id} | 删除分类 | 管理员 |

### 借阅相关
| 方法 | 端点 | 描述 | 权限 |
|------|------|------|------|
| POST | /api/borrows | 借阅图书 | 读者 |
| PUT | /api/borrows/{id}/return | 归还图书 | 读者 |
| PUT | /api/borrows/{id}/renew | 续借图书 | 读者 |
| GET | /api/borrows/my | 查看我的借阅记录 | 读者 |
| GET | /api/borrows | 查看所有借阅记录 | 管理员 |
| GET | /api/borrows/statistics | 借阅统计 | 管理员 |

## 响应格式

### 成功响应
```json
{
  "code": 200,
  "message": "success",
  "data": {}
}
```

### 分页响应
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

### 错误响应
```json
{
  "code": 400,
  "message": "错误信息",
  "data": null
}
```

## 安装部署

### 环境要求
- JDK 17+
- Maven 3.6+
- Node.js 18+
- MySQL 8.0+

### 1. 克隆项目
```bash
git clone <repository-url>
cd library
```

### 2. 初始化数据库
```bash
mysql -u root -p < backend/src/main/resources/schema.sql
```

### 3. 配置后端
编辑 `backend/src/main/resources/application.yml`：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC&characterEncoding=utf8
    username: your_username
    password: your_password
```

### 4. 启动后端
```bash
cd backend
mvn spring-boot:run
```

后端服务启动在 http://localhost:8080

### 5. 启动前端
```bash
cd frontend
npm install
npm run dev
```

前端服务启动在 http://localhost:5173

### 6. 访问系统
打开浏览器访问 http://localhost:5173

## 默认账号

### 管理员账号
- 用户名：admin
- 密码：admin123

### 测试数据
系统初始化时会插入以下测试数据：
- 4个图书分类：文学、计算机、历史、科学
- 3本示例图书：红楼梦、Java编程思想、史记

## 页面路由

| 路径 | 页面 | 权限 |
|------|------|------|
| / | 首页（图书列表） | 公开 |
| /login | 登录页 | 公开 |
| /register | 注册页 | 公开 |
| /books/:id | 图书详情 | 公开 |
| /my/borrows | 我的借阅 | 登录用户 |
| /profile | 个人中心 | 登录用户 |
| /admin | 管理后台 | 管理员 |
| /admin/books | 图书管理 | 管理员 |
| /admin/users | 用户管理 | 管理员 |
| /admin/borrows | 借阅管理 | 管理员 |
| /admin/statistics | 借阅统计 | 管理员 |

## 数据库设计

### 用户表 (users)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT | 主键 |
| username | VARCHAR(50) | 用户名（唯一） |
| password | VARCHAR(255) | 密码（BCrypt加密） |
| name | VARCHAR(100) | 姓名 |
| email | VARCHAR(100) | 邮箱 |
| phone | VARCHAR(20) | 手机号 |
| role | ENUM | 角色（READER/ADMIN） |
| status | TINYINT | 状态（1正常/0禁用） |
| created_at | DATETIME | 创建时间 |
| updated_at | DATETIME | 更新时间 |

### 图书分类表 (categories)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT | 主键 |
| name | VARCHAR(100) | 分类名称 |
| description | VARCHAR(500) | 分类描述 |
| created_at | DATETIME | 创建时间 |

### 图书表 (books)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT | 主键 |
| isbn | VARCHAR(20) | ISBN号 |
| title | VARCHAR(200) | 书名 |
| author | VARCHAR(100) | 作者 |
| publisher | VARCHAR(100) | 出版社 |
| category_id | BIGINT | 分类ID |
| total_count | INT | 总数量 |
| available_count | INT | 可借数量 |
| description | TEXT | 图书简介 |
| cover_image | VARCHAR(500) | 封面图片URL |
| created_at | DATETIME | 创建时间 |
| updated_at | DATETIME | 更新时间 |

### 借阅记录表 (borrow_records)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT | 主键 |
| user_id | BIGINT | 用户ID |
| book_id | BIGINT | 图书ID |
| borrow_date | DATE | 借阅日期 |
| due_date | DATE | 应还日期 |
| return_date | DATE | 归还日期 |
| status | ENUM | 状态（BORROWED/RETURNED/OVERDUE） |
| created_at | DATETIME | 创建时间 |

## 开发说明

### 后端开发
- 使用 Maven 构建
- MyBatis 配置在 `application.yml`
- Mapper XML 文件在 `src/main/resources/mapper/`

### 前端开发
- 使用 Vite 构建
- API 请求封装在 `src/api/request.js`
- 路由配置在 `src/router/index.js`
- 状态管理在 `src/stores/user.js`

## 许可证

MIT License
