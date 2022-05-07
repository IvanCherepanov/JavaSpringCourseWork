package com.example.demo.model.dao;

import com.example.demo.model.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends IAbstractRepository<User>{
    User findUserByUsername(String name);
}
