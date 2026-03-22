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