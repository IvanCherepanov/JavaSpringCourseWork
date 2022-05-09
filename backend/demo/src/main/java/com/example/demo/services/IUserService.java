package com.example.demo.services;

import com.example.demo.model.entity.User;

public interface IUserService extends IAbstractService<User> {
    public void create(String email,String username,String password, String role);
}
