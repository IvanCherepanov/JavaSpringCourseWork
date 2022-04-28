package com.example.demo.controllers;

import com.example.demo.model.entity.User;
import com.example.demo.services.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
public class UserController extends AbstractController<User, IUserService>{
    protected UserController(IUserService service) {
        super(service);
    }
}
