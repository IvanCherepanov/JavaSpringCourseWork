package com.example.demo.services.impl;

import com.example.demo.model.dao.IUserRepository;
import com.example.demo.model.entity.User;
import com.example.demo.services.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends AbstractServiceImpl<User, IUserRepository> implements IUserService {
    protected UserServiceImpl(IUserRepository defaultDao) {
        super(defaultDao);
    }
}
