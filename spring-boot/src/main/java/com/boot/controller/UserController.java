package com.boot.controller;

import com.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.Action;

@Controller
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public String getAllUsers(Model model){
        model.addAttribute("users",userService.getAllProducts());
        return "users_page";
    }
}
