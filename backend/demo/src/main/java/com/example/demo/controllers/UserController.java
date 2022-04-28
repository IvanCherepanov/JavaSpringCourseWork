package com.example.demo.controllers;

import com.example.demo.model.entity.User;
import com.example.demo.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
public class UserController extends AbstractController<User, IUserService>{
    @Autowired
    protected UserController(IUserService service) {
        super(service);
    }
}
