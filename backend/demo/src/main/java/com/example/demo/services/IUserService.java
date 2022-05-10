package com.example.demo.services;

import com.example.demo.model.entity.User;
import org.springframework.security.core.Authentication;

public interface IUserService extends IAbstractService<User> {
    void create(String email,String username,String password, String role);
    String getUserRole(Authentication authentication);
    Long getUserId(Authentication authentication);
}
