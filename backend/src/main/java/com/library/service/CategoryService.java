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
