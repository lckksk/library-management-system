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
