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
