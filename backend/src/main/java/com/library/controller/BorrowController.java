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
