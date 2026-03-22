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
