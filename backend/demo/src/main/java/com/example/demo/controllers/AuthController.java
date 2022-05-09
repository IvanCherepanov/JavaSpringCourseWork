package com.example.demo.controllers;

import com.example.demo.model.entity.User;
import com.example.demo.model.enumerations.RoleType;
import com.example.demo.services.IUserService;
import com.example.demo.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController extends AbstractController<User, IUserService>{
    private final IUserService iUserService;

    @Autowired
    protected AuthController(IUserService service) {
        super(service);
        this.iUserService = service;
    }

    private String getUserRole(Authentication authentication) {
        if (authentication == null)
            return "GUEST";
        else
            return ((User) ((UserServiceImpl) service).loadUserByUsername(authentication.getName())).getRole();
    }

    private Long getUserId(Authentication authentication) {
        if (authentication == null)
            return 0L;
        else
            return ((User) ((UserServiceImpl) service).loadUserByUsername(authentication.getName())).getId();
    }

    @GetMapping("/")
    public String getStart() {
        return "start";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @GetMapping("/sign")
    public String getRegistrationPage(@ModelAttribute("user") User user) {
        return "registration";
    }

    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) { }
    }

    @PostMapping("/sign")
    public String signCreate(HttpServletRequest request, @RequestParam String email, @RequestParam String username, @RequestParam String password, Model model) {
        if ((User) ((UserServiceImpl) service).loadUserByUsername(username) != null) {
            model.addAttribute("Status", "user_exists");
            System.out.println("gmdmgkndfmmfghjhnfgjhf");
            return "registration";
        }
        else {
            ((UserServiceImpl) service).create(email, username, password, RoleType.USER.name());
            System.out.println("fdnmgjdfgjdfnbjofznbjofnbndfjinbjidfnbijd");
            System.out.println(service.getAll());
            authWithHttpServletRequest(request, username, password);
            return "redirect:/home";
        }
    }

}
