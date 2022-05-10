package com.example.demo.controllers;

import com.example.demo.model.entity.User;
import com.example.demo.model.enumerations.RoleType;
import com.example.demo.services.IUserService;
import com.example.demo.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController extends AbstractController<User, IUserService>{
    private final IUserService iUserService;

    @Autowired
    protected AuthController(IUserService service) {
        super(service);
        this.iUserService = service;
    }

    @GetMapping("/login-error")
    public String login(Authentication authentication,
                        HttpServletRequest request,
                        Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("userRole", iUserService.getUserRole(authentication));
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
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
            return "registration";
        }
        else {
            ((UserServiceImpl) service).create(email, username, password, RoleType.USER.name());
            authWithHttpServletRequest(request, username, password);
            return "redirect:/home";
        }
    }

}
