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
