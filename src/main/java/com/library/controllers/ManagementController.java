package com.library.controllers;

import com.library.entities.UserRole;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
@PreAuthorize("hasAuthority('LIBRARIAN')")
public class ManagementController {

    private String users = "users";
    private final UserService userService;

    @Autowired
    ManagementController (UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public String userList(Model model){
        model.addAttribute("users",userService.findAll());
        model.addAttribute("roles", UserRole.values());
        return users;
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        userService.deleteById(id);
        return "redirect:/library";
    }

}
