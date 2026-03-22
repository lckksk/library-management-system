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
