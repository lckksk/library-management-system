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
