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