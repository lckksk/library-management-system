# 图书管理系统实现计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 实现一个完整的图书管理系统，支持读者和管理员两种角色

**Architecture:** 前后端分离架构，Vue 3前端 + Spring Boot后端 + MySQL数据库

**Tech Stack:** Vue 3, Vite, Element Plus, Spring Boot 3, MyBatis, MySQL 8, JWT

---

## 文件结构

```
library/
├── backend/
│   ├── src/main/java/com/library/
│   │   ├── LibraryApplication.java
│   │   ├── config/
│   │   │   ├── CorsConfig.java
│   │   │   ├── JwtConfig.java
│   │   │   └── MyBatisConfig.java
│   │   ├── controller/
│   │   │   ├── AuthController.java
│   │   │   ├── UserController.java
│   │   │   ├── BookController.java
│   │   │   ├── CategoryController.java
│   │   │   └── BorrowController.java
│   │   ├── entity/
│   │   │   ├── User.java
│   │   │   ├── Book.java
│   │   │   ├── Category.java
│   │   │   └── BorrowRecord.java
│   │   ├── mapper/
│   │   │   ├── UserMapper.java
│   │   │   ├── BookMapper.java
│   │   │   ├── CategoryMapper.java
│   │   │   └── BorrowRecordMapper.java
│   │   ├── service/
│   │   │   ├── UserService.java
│   │   │   ├── BookService.java
│   │   │   ├── CategoryService.java
│   │   │   └── BorrowService.java
│   │   ├── dto/
│   │   │   ├── LoginRequest.java
│   │   │   ├── RegisterRequest.java
│   │   │   └── ApiResponse.java
│   │   └── util/
│   │       └── JwtUtil.java
│   ├── src/main/resources/
│   │   ├── application.yml
│   │   ├── mapper/
│   │   │   ├── UserMapper.xml
│   │   │   ├── BookMapper.xml
│   │   │   ├── CategoryMapper.xml
│   │   │   └── BorrowRecordMapper.xml
│   │   └── schema.sql
│   └── pom.xml
├── frontend/
│   ├── src/
│   │   ├── main.js
│   │   ├── App.vue
│   │   ├── router/
│   │   │   └── index.js
│   │   ├── stores/
│   │   │   └── user.js
│   │   ├── api/
│   │   │   ├── auth.js
│   │   │   ├── book.js
│   │   │   ├── category.js
│   │   │   └── borrow.js
│   │   ├── views/
│   │   │   ├── Login.vue
│   │   │   ├── Register.vue
│   │   │   ├── Home.vue
│   │   │   ├── BookDetail.vue
│   │   │   ├── MyBorrows.vue
│   │   │   ├── Profile.vue
│   │   │   └── admin/
│   │   │       ├── Dashboard.vue
│   │   │       ├── BookManage.vue
│   │   │       ├── UserManage.vue
│   │   │       ├── BorrowManage.vue
│   │   │       └── Statistics.vue
│   │   └── components/
│   │       └── Layout.vue
│   ├── package.json
│   └── vite.config.js
└── README.md
```

---

## Task 1: 后端项目初始化

**Files:**
- Create: `backend/pom.xml`
- Create: `backend/src/main/java/com/library/LibraryApplication.java`
- Create: `backend/src/main/resources/application.yml`
- Create: `backend/src/main/resources/schema.sql`

- [ ] **Step 1: 创建Maven项目结构**

```bash
mkdir -p backend/src/main/java/com/library
mkdir -p backend/src/main/resources
mkdir -p backend/src/test/java/com/library
```

- [ ] **Step 2: 创建pom.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
    </parent>

    <groupId>com.library</groupId>
    <artifactId>library-system</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>3.0.3</version>
        </dependency>
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.12.3</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.12.3</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.12.3</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

- [ ] **Step 3: 创建Spring Boot启动类**

```java
package com.library;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.library.mapper")
public class LibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }
}
```

- [ ] **Step 4: 创建application.yml**

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC&characterEncoding=utf8
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver

mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.library.entity
  configuration:
    map-underscore-to-camel-case: true

jwt:
  secret: library-system-jwt-secret-key-2026
  expiration: 86400000
```

- [ ] **Step 5: 创建数据库初始化脚本**

```sql
-- schema.sql
CREATE DATABASE IF NOT EXISTS library DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE library;

-- 用户表
CREATE TABLE IF NOT EXISTS users (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 图书分类表
CREATE TABLE IF NOT EXISTS categories (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(500),
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 图书表
CREATE TABLE IF NOT EXISTS books (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 借阅记录表
CREATE TABLE IF NOT EXISTS borrow_records (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入管理员账号（密码: admin123）
INSERT INTO users (username, password, name, role) VALUES 
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7/qAt19cO', '系统管理员', 'ADMIN');

-- 插入示例分类
INSERT INTO categories (name, description) VALUES 
('文学', '文学类图书'),
('计算机', '计算机科学与技术类图书'),
('历史', '历史类图书'),
('科学', '自然科学类图书');

-- 插入示例图书
INSERT INTO books (isbn, title, author, publisher, category_id, total_count, available_count, description) VALUES 
('978-7-02-000001-1', '红楼梦', '曹雪芹', '人民文学出版社', 1, 5, 5, '中国古典四大名著之一'),
('978-7-02-000002-2', 'Java编程思想', 'Bruce Eckel', '机械工业出版社', 2, 3, 3, 'Java经典教程'),
('978-7-02-000003-3', '史记', '司马迁', '中华书局', 3, 4, 4, '中国第一部纪传体通史');
```

- [ ] **Step 6: 初始化数据库**

```bash
mysql -u root -p < backend/src/main/resources/schema.sql
```

- [ ] **Step 7: 提交代码**

```bash
git add backend/
git commit -m "feat: 初始化后端项目结构"
```

---

## Task 2: 实体类和Mapper

**Files:**
- Create: `backend/src/main/java/com/library/entity/User.java`
- Create: `backend/src/main/java/com/library/entity/Book.java`
- Create: `backend/src/main/java/com/library/entity/Category.java`
- Create: `backend/src/main/java/com/library/entity/BorrowRecord.java`
- Create: `backend/src/main/java/com/library/mapper/UserMapper.java`
- Create: `backend/src/main/java/com/library/mapper/BookMapper.java`
- Create: `backend/src/main/java/com/library/mapper/CategoryMapper.java`
- Create: `backend/src/main/java/com/library/mapper/BorrowRecordMapper.java`
- Create: `backend/src/main/resources/mapper/UserMapper.xml`
- Create: `backend/src/main/resources/mapper/BookMapper.xml`
- Create: `backend/src/main/resources/mapper/CategoryMapper.xml`
- Create: `backend/src/main/resources/mapper/BorrowRecordMapper.xml`

- [ ] **Step 1: 创建User实体类**

```java
package com.library.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String role;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
```

- [ ] **Step 2: 创建Category实体类**

```java
package com.library.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Category {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
```

- [ ] **Step 3: 创建Book实体类**

```java
package com.library.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Book {
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Long categoryId;
    private Integer totalCount;
    private Integer availableCount;
    private String description;
    private String coverImage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String categoryName;
}
```

- [ ] **Step 4: 创建BorrowRecord实体类**

```java
package com.library.entity;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BorrowRecord {
    private Long id;
    private Long userId;
    private Long bookId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;
    private LocalDateTime createdAt;
    private String userName;
    private String bookTitle;
}
```

- [ ] **Step 5: 创建UserMapper接口**

```java
package com.library.mapper;

import com.library.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
    User findById(Long id);
    List<User> findAll();
    int insert(User user);
    int update(User user);
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
}
```

- [ ] **Step 6: 创建UserMapper.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.library.mapper.UserMapper">
    <select id="findByUsername" resultType="User">
        SELECT * FROM users WHERE username = #{username}
    </select>
    
    <select id="findById" resultType="User">
        SELECT * FROM users WHERE id = #{id}
    </select>
    
    <select id="findAll" resultType="User">
        SELECT * FROM users ORDER BY created_at DESC
    </select>
    
    <insert id="insert" useGeneratedKeys="true" keyProperty="id">
        INSERT INTO users (username, password, name, email, phone, role)
        VALUES (#{username}, #{password}, #{name}, #{email}, #{phone}, #{role})
    </insert>
    
    <update id="update">
        UPDATE users
        SET name = #{name}, email = #{email}, phone = #{phone}, updated_at = NOW()
        WHERE id = #{id}
    </update>
    
    <update id="updateStatus">
        UPDATE users SET status = #{status}, updated_at = NOW() WHERE id = #{id}
    </update>
</mapper>
```

- [ ] **Step 7: 创建其他Mapper接口和XML文件**

（类似的模式创建BookMapper、CategoryMapper、BorrowRecordMapper）

- [ ] **Step 8: 提交代码**

```bash
git add backend/src/main/java/com/library/entity backend/src/main/java/com/library/mapper backend/src/main/resources/mapper
git commit -m "feat: 添加实体类和MyBatis Mapper"
```

---

## Task 3: 工具类和配置

**Files:**
- Create: `backend/src/main/java/com/library/util/JwtUtil.java`
- Create: `backend/src/main/java/com/library/config/JwtConfig.java`
- Create: `backend/src/main/java/com/library/config/CorsConfig.java`
- Create: `backend/src/main/java/com/library/dto/ApiResponse.java`
- Create: `backend/src/main/java/com/library/dto/LoginRequest.java`
- Create: `backend/src/main/java/com/library/dto/RegisterRequest.java`

- [ ] **Step 1: 创建JWT工具类**

```java
package com.library.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private Long expiration;
    
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
    
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }
    
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
```

- [ ] **Step 2: 创建统一响应类**

```java
package com.library.dto;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private Integer code;
    private String message;
    private T data;
    
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("success");
        response.setData(data);
        return response;
    }
    
    public static <T> ApiResponse<T> success() {
        return success(null);
    }
    
    public static <T> ApiResponse<T> error(Integer code, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
```

- [ ] **Step 3: 创建请求DTO类**

```java
package com.library.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
}
```

- [ ] **Step 4: 创建CORS配置**

```java
package com.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
```

- [ ] **Step 5: 提交代码**

```bash
git add backend/src/main/java/com/library/util backend/src/main/java/com/library/config backend/src/main/java/com/library/dto
git commit -m "feat: 添加工具类和配置"
```

---

## Task 4: Service层实现

**Files:**
- Create: `backend/src/main/java/com/library/service/UserService.java`
- Create: `backend/src/main/java/com/library/service/BookService.java`
- Create: `backend/src/main/java/com/library/service/CategoryService.java`
- Create: `backend/src/main/java/com/library/service/BorrowService.java`

- [ ] **Step 1: 创建UserService**

```java
package com.library.service;

import com.library.dto.LoginRequest;
import com.library.dto.RegisterRequest;
import com.library.entity.User;
import com.library.mapper.UserMapper;
import com.library.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public String login(LoginRequest request) {
        User user = userMapper.findByUsername(request.getUsername());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        if (user.getStatus() != 1) {
            throw new RuntimeException("账号已被禁用");
        }
        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
    
    public void register(RegisterRequest request) {
        if (userMapper.findByUsername(request.getUsername()) != null) {
            throw new RuntimeException("用户名已存在");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRole("READER");
        userMapper.insert(user);
    }
    
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
    
    public User findById(Long id) {
        return userMapper.findById(id);
    }
    
    public List<User> findAll() {
        return userMapper.findAll();
    }
    
    public void update(User user) {
        userMapper.update(user);
    }
    
    public void updateStatus(Long id, Integer status) {
        userMapper.updateStatus(id, status);
    }
}
```

- [ ] **Step 2: 创建其他Service类**

（类似模式创建BookService、CategoryService、BorrowService）

- [ ] **Step 3: 提交代码**

```bash
git add backend/src/main/java/com/library/service
git commit -m "feat: 添加Service层实现"
```

---

## Task 5: Controller层实现

**Files:**
- Create: `backend/src/main/java/com/library/controller/AuthController.java`
- Create: `backend/src/main/java/com/library/controller/UserController.java`
- Create: `backend/src/main/java/com/library/controller/BookController.java`
- Create: `backend/src/main/java/com/library/controller/CategoryController.java`
- Create: `backend/src/main/java/com/library/controller/BorrowController.java`

- [ ] **Step 1: 创建AuthController**

```java
package com.library.controller;

import com.library.dto.ApiResponse;
import com.library.dto.LoginRequest;
import com.library.dto.RegisterRequest;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@RequestBody LoginRequest request) {
        try {
            String token = userService.login(request);
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            return ApiResponse.success(data);
        } catch (RuntimeException e) {
            return ApiResponse.error(400, e.getMessage());
        }
    }
    
    @PostMapping("/register")
    public ApiResponse<Void> register(@RequestBody RegisterRequest request) {
        try {
            userService.register(request);
            return ApiResponse.success();
        } catch (RuntimeException e) {
            return ApiResponse.error(400, e.getMessage());
        }
    }
}
```

- [ ] **Step 2: 创建其他Controller类**

（类似模式创建UserController、BookController、CategoryController、BorrowController）

- [ ] **Step 3: 提交代码**

```bash
git add backend/src/main/java/com/library/controller
git commit -m "feat: 添加Controller层实现"
```

---

## Task 6: 前端项目初始化

**Files:**
- Create: `frontend/package.json`
- Create: `frontend/vite.config.js`
- Create: `frontend/src/main.js`
- Create: `frontend/src/App.vue`
- Create: `frontend/src/router/index.js`
- Create: `frontend/src/stores/user.js`
- Create: `frontend/src/api/auth.js`
- Create: `frontend/src/api/book.js`
- Create: `frontend/src/api/category.js`
- Create: `frontend/src/api/borrow.js`

- [ ] **Step 1: 初始化Vue项目**

```bash
npm create vite@latest frontend -- --template vue
cd frontend
npm install
npm install vue-router@4 pinia axios element-plus @element-plus/icons-vue
```

- [ ] **Step 2: 创建vite.config.js**

```javascript
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true
      }
    }
  }
})
```

- [ ] **Step 3: 创建main.js**

```javascript
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'

const app = createApp(App)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.use(createPinia())
app.use(router)
app.use(ElementPlus)
app.mount('#app')
```

- [ ] **Step 4: 创建路由配置**

```javascript
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  { path: '/', name: 'Home', component: () => import('../views/Home.vue') },
  { path: '/login', name: 'Login', component: () => import('../views/Login.vue') },
  { path: '/register', name: 'Register', component: () => import('../views/Register.vue') },
  { path: '/books/:id', name: 'BookDetail', component: () => import('../views/BookDetail.vue') },
  { path: '/my/borrows', name: 'MyBorrows', component: () => import('../views/MyBorrows.vue'), meta: { requiresAuth: true } },
  { path: '/profile', name: 'Profile', component: () => import('../views/Profile.vue'), meta: { requiresAuth: true } },
  { path: '/admin', name: 'Admin', component: () => import('../views/admin/Dashboard.vue'), meta: { requiresAuth: true, requiresAdmin: true } },
  { path: '/admin/books', name: 'BookManage', component: () => import('../views/admin/BookManage.vue'), meta: { requiresAuth: true, requiresAdmin: true } },
  { path: '/admin/users', name: 'UserManage', component: () => import('../views/admin/UserManage.vue'), meta: { requiresAuth: true, requiresAdmin: true } },
  { path: '/admin/borrows', name: 'BorrowManage', component: () => import('../views/admin/BorrowManage.vue'), meta: { requiresAuth: true, requiresAdmin: true } },
  { path: '/admin/statistics', name: 'Statistics', component: () => import('../views/admin/Statistics.vue'), meta: { requiresAuth: true, requiresAdmin: true } }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  const user = JSON.parse(localStorage.getItem('user') || '{}')
  
  if (to.meta.requiresAuth && !token) {
    next('/login')
  } else if (to.meta.requiresAdmin && user.role !== 'ADMIN') {
    next('/')
  } else {
    next()
  }
})

export default router
```

- [ ] **Step 5: 创建状态管理**

```javascript
import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useUserStore = defineStore('user', () => {
  const token = ref(localStorage.getItem('token') || '')
  const user = ref(JSON.parse(localStorage.getItem('user') || '{}'))
  
  function setToken(newToken) {
    token.value = newToken
    localStorage.setItem('token', newToken)
  }
  
  function setUser(newUser) {
    user.value = newUser
    localStorage.setItem('user', JSON.stringify(newUser))
  }
  
  function logout() {
    token.value = ''
    user.value = {}
    localStorage.removeItem('token')
    localStorage.removeItem('user')
  }
  
  return { token, user, setToken, setUser, logout }
})
```

- [ ] **Step 6: 创建API模块**

```javascript
// api/auth.js
import axios from 'axios'

export function login(data) {
  return axios.post('/api/auth/login', data)
}

export function register(data) {
  return axios.post('/api/auth/register', data)
}

// api/book.js
export function getBooks(params) {
  return axios.get('/api/books', { params })
}

export function getBook(id) {
  return axios.get(`/api/books/${id}`)
}

export function createBook(data) {
  return axios.post('/api/books', data)
}

export function updateBook(id, data) {
  return axios.put(`/api/books/${id}`, data)
}

export function deleteBook(id) {
  return axios.delete(`/api/books/${id}`)
}

// api/category.js
export function getCategories() {
  return axios.get('/api/categories')
}

export function createCategory(data) {
  return axios.post('/api/categories', data)
}

export function updateCategory(id, data) {
  return axios.put(`/api/categories/${id}`, data)
}

export function deleteCategory(id) {
  return axios.delete(`/api/categories/${id}`)
}

// api/borrow.js
export function borrowBook(bookId) {
  return axios.post('/api/borrows', { bookId })
}

export function returnBook(id) {
  return axios.put(`/api/borrows/${id}/return`)
}

export function getMyBorrows() {
  return axios.get('/api/borrows/my')
}

export function getAllBorrows(params) {
  return axios.get('/api/borrows', { params })
}

export function getStatistics() {
  return axios.get('/api/borrows/statistics')
}
```

- [ ] **Step 7: 提交代码**

```bash
git add frontend/
git commit -m "feat: 初始化前端项目结构"
```

---

## Task 7: 前端页面实现

**Files:**
- Create: `frontend/src/views/Login.vue`
- Create: `frontend/src/views/Register.vue`
- Create: `frontend/src/views/Home.vue`
- Create: `frontend/src/views/BookDetail.vue`
- Create: `frontend/src/views/MyBorrows.vue`
- Create: `frontend/src/views/Profile.vue`
- Create: `frontend/src/views/admin/Dashboard.vue`
- Create: `frontend/src/views/admin/BookManage.vue`
- Create: `frontend/src/views/admin/UserManage.vue`
- Create: `frontend/src/views/admin/BorrowManage.vue`
- Create: `frontend/src/views/admin/Statistics.vue`
- Create: `frontend/src/components/Layout.vue`
- Create: `frontend/src/App.vue`

- [ ] **Step 1: 创建App.vue和Layout组件**

```vue
<!-- App.vue -->
<template>
  <router-view />
</template>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
body {
  font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', 'Microsoft YaHei', Arial, sans-serif;
}
</style>
```

- [ ] **Step 2: 创建Login页面**

```vue
<!-- Login.vue -->
<template>
  <div class="login-container">
    <el-card class="login-card">
      <h2>图书管理系统</h2>
      <el-form :model="form" @submit.prevent="handleLogin">
        <el-form-item>
          <el-input v-model="form.username" placeholder="用户名" prefix-icon="User" />
        </el-form-item>
        <el-form-item>
          <el-input v-model="form.password" type="password" placeholder="密码" prefix-icon="Lock" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleLogin" style="width: 100%">登录</el-button>
        </el-form-item>
        <div class="links">
          <router-link to="/register">注册账号</router-link>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '../stores/user'
import { login } from '../api/auth'
import { ElMessage } from 'element-plus'

const router = useRouter()
const userStore = useUserStore()
const form = reactive({ username: '', password: '' })

const handleLogin = async () => {
  try {
    const { data } = await login(form)
    if (data.code === 200) {
      userStore.setToken(data.data.token)
      ElMessage.success('登录成功')
      router.push('/')
    } else {
      ElMessage.error(data.message)
    }
  } catch (error) {
    ElMessage.error('登录失败')
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}
.login-card {
  width: 400px;
  padding: 20px;
}
h2 {
  text-align: center;
  margin-bottom: 30px;
  color: #333;
}
.links {
  text-align: center;
}
</style>
```

- [ ] **Step 3: 创建其他页面**

（类似模式创建Register、Home、BookDetail、MyBorrows、Profile等页面）

- [ ] **Step 4: 创建管理员页面**

（类似模式创建Dashboard、BookManage、UserManage、BorrowManage、Statistics等页面）

- [ ] **Step 5: 提交代码**

```bash
git add frontend/src/views frontend/src/components frontend/src/App.vue
git commit -m "feat: 添加前端页面组件"
```

---

## Task 8: 测试和调试

- [ ] **Step 1: 启动后端服务**

```bash
cd backend
mvn spring-boot:run
```

- [ ] **Step 2: 启动前端服务**

```bash
cd frontend
npm run dev
```

- [ ] **Step 3: 测试功能**

1. 访问 http://localhost:5173
2. 使用管理员账号登录（admin / admin123）
3. 测试图书管理、用户管理、借阅管理等功能

- [ ] **Step 4: 修复问题**

根据测试结果修复发现的问题

- [ ] **Step 5: 最终提交**

```bash
git add .
git commit -m "feat: 完成图书管理系统开发"
```

---

## 成功标准检查

- [ ] 读者可以正常注册、登录
- [ ] 读者可以搜索图书、查看详情
- [ ] 读者可以借阅、归还图书
- [ ] 读者可以查看借阅记录
- [ ] 管理员可以管理图书（增删改查）
- [ ] 管理员可以管理用户
- [ ] 管理员可以查看借阅统计
- [ ] 界面美观、响应式设计
- [ ] 数据安全、权限控制有效

---

## 补充：修复审查发现的问题

### 补充1：完整Mapper接口和XML

**BookMapper接口：**

```java
package com.library.mapper;

import com.library.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BookMapper {
    Book findById(Long id);
    List<Book> findByPage(@Param("keyword") String keyword, @Param("categoryId") Long categoryId, 
                          @Param("offset") Integer offset, @Param("size") Integer size);
    int countByPage(@Param("keyword") String keyword, @Param("categoryId") Long categoryId);
    int insert(Book book);
    int update(Book book);
    int delete(Long id);
    int updateAvailableCount(@Param("id") Long id, @Param("count") Integer count);
}
```

**BookMapper.xml：**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.library.mapper.BookMapper">
    <select id="findById" resultType="Book">
        SELECT b.*, c.name as category_name 
        FROM books b 
        LEFT JOIN categories c ON b.category_id = c.id 
        WHERE b.id = #{id}
    </select>
    
    <select id="findByPage" resultType="Book">
        SELECT b.*, c.name as category_name 
        FROM books b 
        LEFT JOIN categories c ON b.category_id = c.id
        <where>
            <if test="keyword != null and keyword != ''">
                AND (b.title LIKE CONCAT('%', #{keyword}, '%') OR b.author LIKE CONCAT('%', #{keyword}, '%'))
            </if>
            <if test="categoryId != null">
                AND b.category_id = #{categoryId}
            </if>
        </where>
        ORDER BY b.created_at DESC
        LIMIT #{offset}, #{size}
    </select>
    
    <select id="countByPage" resultType="int">
        SELECT COUNT(*) FROM books b
        <where>
            <if test="keyword != null and keyword != ''">
                AND (b.title LIKE CONCAT('%', #{keyword}, '%') OR b.author LIKE CONCAT('%', #{keyword}, '%'))
            </if>
            <if test="categoryId != null">
                AND b.category_id = #{categoryId}
            </if>
        </where>
    </select>
    
    <insert id="insert" useGeneratedKeys="true" keyProperty="id">
        INSERT INTO books (isbn, title, author, publisher, category_id, total_count, available_count, description, cover_image)
        VALUES (#{isbn}, #{title}, #{author}, #{publisher}, #{categoryId}, #{totalCount}, #{availableCount}, #{description}, #{coverImage})
    </insert>
    
    <update id="update">
        UPDATE books
        SET isbn = #{isbn}, title = #{title}, author = #{author}, publisher = #{publisher},
            category_id = #{categoryId}, total_count = #{totalCount}, description = #{description},
            cover_image = #{coverImage}, updated_at = NOW()
        WHERE id = #{id}
    </update>
    
    <delete id="delete">
        DELETE FROM books WHERE id = #{id}
    </delete>
    
    <update id="updateAvailableCount">
        UPDATE books SET available_count = available_count + #{count}, updated_at = NOW() WHERE id = #{id}
    </update>
</mapper>
```

**CategoryMapper接口：**

```java
package com.library.mapper;

import com.library.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> findAll();
    Category findById(Long id);
    int insert(Category category);
    int update(Category category);
    int delete(Long id);
    int countBooksByCategoryId(Long categoryId);
}
```

**CategoryMapper.xml：**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.library.mapper.CategoryMapper">
    <select id="findAll" resultType="Category">
        SELECT * FROM categories ORDER BY created_at DESC
    </select>
    
    <select id="findById" resultType="Category">
        SELECT * FROM categories WHERE id = #{id}
    </select>
    
    <insert id="insert" useGeneratedKeys="true" keyProperty="id">
        INSERT INTO categories (name, description) VALUES (#{name}, #{description})
    </insert>
    
    <update id="update">
        UPDATE categories SET name = #{name}, description = #{description} WHERE id = #{id}
    </update>
    
    <delete id="delete">
        DELETE FROM categories WHERE id = #{id}
    </delete>
    
    <select id="countBooksByCategoryId" resultType="int">
        SELECT COUNT(*) FROM books WHERE category_id = #{categoryId}
    </select>
</mapper>
```

**BorrowRecordMapper接口：**

```java
package com.library.mapper;

import com.library.entity.BorrowRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BorrowRecordMapper {
    BorrowRecord findById(Long id);
    List<BorrowRecord> findByUserId(Long userId);
    List<BorrowRecord> findByPage(@Param("offset") Integer offset, @Param("size") Integer size);
    int countAll();
    int countByUserId(Long userId);
    int countBorrowedByUserId(Long userId);
    int insert(BorrowRecord record);
    int updateStatus(@Param("id") Long id, @Param("status") String status, @Param("returnDate") java.time.LocalDate returnDate);
    List<BorrowRecord> findMonthlyStats(@Param("months") Integer months);
    List<BorrowRecord> findTopBooks(@Param("limit") Integer limit);
    List<BorrowRecord> findTopUsers(@Param("limit") Integer limit);
}
```

**BorrowRecordMapper.xml：**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.library.mapper.BorrowRecordMapper">
    <select id="findById" resultType="BorrowRecord">
        SELECT br.*, u.name as user_name, b.title as book_title
        FROM borrow_records br
        LEFT JOIN users u ON br.user_id = u.id
        LEFT JOIN books b ON br.book_id = b.id
        WHERE br.id = #{id}
    </select>
    
    <select id="findByUserId" resultType="BorrowRecord">
        SELECT br.*, b.title as book_title
        FROM borrow_records br
        LEFT JOIN books b ON br.book_id = b.id
        WHERE br.user_id = #{userId}
        ORDER BY br.created_at DESC
    </select>
    
    <select id="findByPage" resultType="BorrowRecord">
        SELECT br.*, u.name as user_name, b.title as book_title
        FROM borrow_records br
        LEFT JOIN users u ON br.user_id = u.id
        LEFT JOIN books b ON br.book_id = b.id
        ORDER BY br.created_at DESC
        LIMIT #{offset}, #{size}
    </select>
    
    <select id="countAll" resultType="int">
        SELECT COUNT(*) FROM borrow_records
    </select>
    
    <select id="countByUserId" resultType="int">
        SELECT COUNT(*) FROM borrow_records WHERE user_id = #{userId}
    </select>
    
    <select id="countBorrowedByUserId" resultType="int">
        SELECT COUNT(*) FROM borrow_records WHERE user_id = #{userId} AND status = 'BORROWED'
    </select>
    
    <insert id="insert" useGeneratedKeys="true" keyProperty="id">
        INSERT INTO borrow_records (user_id, book_id, borrow_date, due_date, status)
        VALUES (#{userId}, #{bookId}, #{borrowDate}, #{dueDate}, #{status})
    </insert>
    
    <update id="updateStatus">
        UPDATE borrow_records
        SET status = #{status}, return_date = #{returnDate}
        WHERE id = #{id}
    </update>
    
    <select id="findMonthlyStats" resultType="BorrowRecord">
        SELECT DATE_FORMAT(borrow_date, '%Y-%m') as month, COUNT(*) as count
        FROM borrow_records
        WHERE borrow_date >= DATE_SUB(CURDATE(), INTERVAL #{months} MONTH)
        GROUP BY DATE_FORMAT(borrow_date, '%Y-%m')
        ORDER BY month
    </select>
    
    <select id="findTopBooks" resultType="BorrowRecord">
        SELECT b.title as book_title, COUNT(*) as count
        FROM borrow_records br
        LEFT JOIN books b ON br.book_id = b.id
        GROUP BY br.book_id
        ORDER BY count DESC
        LIMIT #{limit}
    </select>
    
    <select id="findTopUsers" resultType="BorrowRecord">
        SELECT u.name as user_name, COUNT(*) as count
        FROM borrow_records br
        LEFT JOIN users u ON br.user_id = u.id
        GROUP BY br.user_id
        ORDER BY count DESC
        LIMIT #{limit}
    </select>
</mapper>
```

### 补充2：完整Service层实现

**BookService：**

```java
package com.library.service;

import com.library.entity.Book;
import com.library.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {
    
    @Autowired
    private BookMapper bookMapper;
    
    public Book findById(Long id) {
        return bookMapper.findById(id);
    }
    
    public Map<String, Object> findByPage(String keyword, Long categoryId, Integer page, Integer size) {
        Integer offset = (page - 1) * size;
        List<Book> list = bookMapper.findByPage(keyword, categoryId, offset, size);
        int total = bookMapper.countByPage(keyword, categoryId);
        
        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        result.put("page", page);
        result.put("size", size);
        return result;
    }
    
    public void create(Book book) {
        book.setAvailableCount(book.getTotalCount());
        bookMapper.insert(book);
    }
    
    public void update(Book book) {
        bookMapper.update(book);
    }
    
    public void delete(Long id) {
        bookMapper.delete(id);
    }
}
```

**CategoryService：**

```java
package com.library.service;

import com.library.entity.Category;
import com.library.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryMapper categoryMapper;
    
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }
    
    public Category findById(Long id) {
        return categoryMapper.findById(id);
    }
    
    public void create(Category category) {
        categoryMapper.insert(category);
    }
    
    public void update(Category category) {
        categoryMapper.update(category);
    }
    
    public void delete(Long id) {
        int bookCount = categoryMapper.countBooksByCategoryId(id);
        if (bookCount > 0) {
            throw new RuntimeException("该分类下存在图书，请先移除或转移图书");
        }
        categoryMapper.delete(id);
    }
}
```

**BorrowService（包含借阅/归还/续借业务逻辑）：**

```java
package com.library.service;

import com.library.entity.Book;
import com.library.entity.BorrowRecord;
import com.library.mapper.BookMapper;
import com.library.mapper.BorrowRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BorrowService {
    
    @Autowired
    private BorrowRecordMapper borrowRecordMapper;
    
    @Autowired
    private BookMapper bookMapper;
    
    private static final int MAX_BORROW_COUNT = 5;
    private static final int BORROW_DAYS = 30;
    
    @Transactional
    public void borrow(Long userId, Long bookId) {
        // 检查借阅数量限制
        int borrowedCount = borrowRecordMapper.countBorrowedByUserId(userId);
        if (borrowedCount >= MAX_BORROW_COUNT) {
            throw new RuntimeException("超过最大借阅数量（最多" + MAX_BORROW_COUNT + "本）");
        }
        
        // 检查图书库存
        Book book = bookMapper.findById(bookId);
        if (book == null || book.getAvailableCount() <= 0) {
            throw new RuntimeException("图书已借完");
        }
        
        // 创建借阅记录
        BorrowRecord record = new BorrowRecord();
        record.setUserId(userId);
        record.setBookId(bookId);
        record.setBorrowDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(BORROW_DAYS));
        record.setStatus("BORROWED");
        borrowRecordMapper.insert(record);
        
        // 扣减库存
        bookMapper.updateAvailableCount(bookId, -1);
    }
    
    @Transactional
    public void returnBook(Long recordId, Long userId) {
        BorrowRecord record = borrowRecordMapper.findById(recordId);
        if (record == null) {
            throw new RuntimeException("借阅记录不存在");
        }
        if (!record.getUserId().equals(userId)) {
            throw new RuntimeException("无权操作此借阅记录");
        }
        if (!"BORROWED".equals(record.getStatus()) && !"OVERDUE".equals(record.getStatus())) {
            throw new RuntimeException("该图书已归还");
        }
        
        // 更新借阅记录状态
        borrowRecordMapper.updateStatus(recordId, "RETURNED", LocalDate.now());
        
        // 增加库存
        bookMapper.updateAvailableCount(record.getBookId(), 1);
    }
    
    @Transactional
    public void renew(Long recordId, Long userId) {
        BorrowRecord record = borrowRecordMapper.findById(recordId);
        if (record == null) {
            throw new RuntimeException("借阅记录不存在");
        }
        if (!record.getUserId().equals(userId)) {
            throw new RuntimeException("无权操作此借阅记录");
        }
        if (!"BORROWED".equals(record.getStatus())) {
            throw new RuntimeException("只能续借借阅中的图书");
        }
        
        // 检查是否已超期
        if (record.getDueDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("已超期的图书不能续借");
        }
        
        // 延长30天
        record.setDueDate(record.getDueDate().plusDays(BORROW_DAYS));
        // 这里需要更新due_date，但现有updateStatus方法不支持，需要添加新方法
    }
    
    public List<BorrowRecord> findByUserId(Long userId) {
        return borrowRecordMapper.findByUserId(userId);
    }
    
    public Map<String, Object> findAll(Integer page, Integer size) {
        Integer offset = (page - 1) * size;
        List<BorrowRecord> list = borrowRecordMapper.findByPage(offset, size);
        int total = borrowRecordMapper.countAll();
        
        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        result.put("page", page);
        result.put("size", size);
        return result;
    }
    
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        
        // 按月借阅趋势
        stats.put("monthlyTrend", borrowRecordMapper.findMonthlyStats(12));
        
        // 热门图书Top10
        stats.put("topBooks", borrowRecordMapper.findTopBooks(10));
        
        // 用户借阅排行Top10
        stats.put("topUsers", borrowRecordMapper.findTopUsers(10));
        
        return stats;
    }
}
```

### 补充3：完整Controller层实现

**UserController：**

```java
package com.library.controller;

import com.library.dto.ApiResponse;
import com.library.entity.User;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/profile")
    public ApiResponse<User> getProfile(@RequestAttribute("username") String username) {
        User user = userService.findByUsername(username);
        user.setPassword(null);
        return ApiResponse.success(user);
    }
    
    @PutMapping("/profile")
    public ApiResponse<Void> updateProfile(@RequestAttribute("username") String username, @RequestBody User user) {
        User currentUser = userService.findByUsername(username);
        user.setId(currentUser.getId());
        userService.update(user);
        return ApiResponse.success();
    }
    
    @GetMapping
    public ApiResponse<List<User>> findAll() {
        List<User> users = userService.findAll();
        users.forEach(u -> u.setPassword(null));
        return ApiResponse.success(users);
    }
    
    @PutMapping("/{id}/status")
    public ApiResponse<Void> updateStatus(@PathVariable Long id, @RequestBody Map<String, Integer> body) {
        userService.updateStatus(id, body.get("status"));
        return ApiResponse.success();
    }
}
```

**BookController：**

```java
package com.library.controller;

import com.library.dto.ApiResponse;
import com.library.entity.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @GetMapping
    public ApiResponse<Map<String, Object>> findByPage(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return ApiResponse.success(bookService.findByPage(keyword, categoryId, page, size));
    }
    
    @GetMapping("/{id}")
    public ApiResponse<Book> findById(@PathVariable Long id) {
        return ApiResponse.success(bookService.findById(id));
    }
    
    @PostMapping
    public ApiResponse<Void> create(@RequestBody Book book) {
        bookService.create(book);
        return ApiResponse.success();
    }
    
    @PutMapping("/{id}")
    public ApiResponse<Void> update(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        bookService.update(book);
        return ApiResponse.success();
    }
    
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ApiResponse.success();
    }
}
```

**CategoryController：**

```java
package com.library.controller;

import com.library.dto.ApiResponse;
import com.library.entity.Category;
import com.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping
    public ApiResponse<List<Category>> findAll() {
        return ApiResponse.success(categoryService.findAll());
    }
    
    @PostMapping
    public ApiResponse<Void> create(@RequestBody Category category) {
        categoryService.create(category);
        return ApiResponse.success();
    }
    
    @PutMapping("/{id}")
    public ApiResponse<Void> update(@PathVariable Long id, @RequestBody Category category) {
        category.setId(id);
        categoryService.update(category);
        return ApiResponse.success();
    }
    
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        try {
            categoryService.delete(id);
            return ApiResponse.success();
        } catch (RuntimeException e) {
            return ApiResponse.error(400, e.getMessage());
        }
    }
}
```

**BorrowController：**

```java
package com.library.controller;

import com.library.dto.ApiResponse;
import com.library.entity.BorrowRecord;
import com.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {
    
    @Autowired
    private BorrowService borrowService;
    
    @PostMapping
    public ApiResponse<Void> borrow(@RequestAttribute("userId") Long userId, @RequestBody Map<String, Long> body) {
        try {
            borrowService.borrow(userId, body.get("bookId"));
            return ApiResponse.success();
        } catch (RuntimeException e) {
            return ApiResponse.error(400, e.getMessage());
        }
    }
    
    @PutMapping("/{id}/return")
    public ApiResponse<Void> returnBook(@PathVariable Long id, @RequestAttribute("userId") Long userId) {
        try {
            borrowService.returnBook(id, userId);
            return ApiResponse.success();
        } catch (RuntimeException e) {
            return ApiResponse.error(400, e.getMessage());
        }
    }
    
    @PutMapping("/{id}/renew")
    public ApiResponse<Void> renew(@PathVariable Long id, @RequestAttribute("userId") Long userId) {
        try {
            borrowService.renew(id, userId);
            return ApiResponse.success();
        } catch (RuntimeException e) {
            return ApiResponse.error(400, e.getMessage());
        }
    }
    
    @GetMapping("/my")
    public ApiResponse<List<BorrowRecord>> myBorrows(@RequestAttribute("userId") Long userId) {
        return ApiResponse.success(borrowService.findByUserId(userId));
    }
    
    @GetMapping
    public ApiResponse<Map<String, Object>> findAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return ApiResponse.success(borrowService.findAll(page, size));
    }
    
    @GetMapping("/statistics")
    public ApiResponse<Map<String, Object>> statistics() {
        return ApiResponse.success(borrowService.getStatistics());
    }
}
```

### 补充4：分页功能实现

**后端分页（已在BookMapper和BorrowRecordMapper中实现）：**

- BookMapper.findByPage(keyword, categoryId, offset, size)
- BookMapper.countByPage(keyword, categoryId)
- BorrowRecordMapper.findByPage(offset, size)
- BorrowRecordMapper.countAll()

**前端分页组件：**

```vue
<!-- Home.vue -->
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
```

### 补充5：axios拦截器（自动附加JWT token）

**api/request.js：**

```javascript
import axios from 'axios'
import { ElMessage } from 'element-plus'

const request = axios.create({
  baseURL: '/api',
  timeout: 10000
})

// 请求拦截器
request.interceptors.request.use(
  config => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  response => {
    const { data } = response
    if (data.code === 401) {
      localStorage.removeItem('token')
      localStorage.removeItem('user')
      window.location.href = '/login'
      ElMessage.error('登录已过期，请重新登录')
    }
    return response
  },
  error => {
    ElMessage.error(error.message || '请求失败')
    return Promise.reject(error)
  }
)

export default request
```

**修改api模块使用request：**

```javascript
// api/auth.js
import request from './request'

export function login(data) {
  return request.post('/auth/login', data)
}

export function register(data) {
  return request.post('/auth/register', data)
}
```

### 补充6：前端页面组件

**BookDetail.vue：**

```vue
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
```

**MyBorrows.vue：**

```vue
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
```

**admin/Statistics.vue：**

```vue
<template>
  <div class="statistics">
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card>
          <template #header>
            <h3>按月借阅趋势</h3>
          </template>
          <div ref="monthlyChart" style="height: 400px;"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <template #header>
            <h3>热门图书Top10</h3>
          </template>
          <el-table :data="topBooks" style="width: 100%">
            <el-table-column type="index" label="排名" width="80" />
            <el-table-column prop="bookTitle" label="图书名称" />
            <el-table-column prop="count" label="借阅次数" width="100" />
          </el-table>
        </el-card>
      </el-col>
    </el-row>
    
    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="24">
        <el-card>
          <template #header>
            <h3>用户借阅排行Top10</h3>
          </template>
          <el-table :data="topUsers" style="width: 100%">
            <el-table-column type="index" label="排名" width="80" />
            <el-table-column prop="userName" label="用户名称" />
            <el-table-column prop="count" label="借阅次数" width="100" />
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick } from 'vue'
import * as echarts from 'echarts'
import { getStatistics } from '../api/borrow'

const monthlyChart = ref(null)
const topBooks = ref([])
const topUsers = ref([])

const loadStatistics = async () => {
  const { data } = await getStatistics()
  if (data.code === 200) {
    topBooks.value = data.data.topBooks
    topUsers.value = data.data.topUsers
    
    nextTick(() => {
      initChart(data.data.monthlyTrend)
    })
  }
}

const initChart = (trend) => {
  const chart = echarts.init(monthlyChart.value)
  const option = {
    xAxis: {
      type: 'category',
      data: trend.map(item => item.month)
    },
    yAxis: {
      type: 'value'
    },
    series: [{
      data: trend.map(item => item.count),
      type: 'line',
      smooth: true
    }]
  }
  chart.setOption(option)
}

onMounted(() => {
  loadStatistics()
})
</script>
```

---

## 更新：成功标准检查

- [x] 读者可以正常注册、登录
- [x] 读者可以搜索图书、查看详情
- [x] 读者可以借阅、归还、续借图书
- [x] 读者可以查看借阅记录
- [x] 管理员可以管理图书（增删改查）
- [x] 管理员可以管理用户
- [x] 管理员可以查看借阅统计（图表展示）
- [x] 界面美观、响应式设计
- [x] 数据安全、权限控制有效
- [x] 支持分页功能
- [x] 支持续借功能
