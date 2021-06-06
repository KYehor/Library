package com.library.controllers;

import com.library.entities.User;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    RegistrationController (UserService userService){
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user){
        User userExists = userService.findByUserName(user.getUsername());
        if(userExists != null){
            return "redirect:/registration?username";
        }
        if(user.getPassword() == null){
            return "redirect:/registration??password";
        }
        userService.save(user);
        return "redirect:/login";
    }

}
