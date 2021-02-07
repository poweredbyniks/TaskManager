package org.niks.controller;

import org.niks.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public boolean create(String userName, String password) throws IOException {
        return userService.create(userName, password);
    }

    @PostMapping("/nameEdit")
    public void userNameEdit(String newUserName) {
        userService.userNameEdit(newUserName);
    }

    @PostMapping("/passwordEdit")
    public void passwordEdit(String newPassword) {
        userService.passwordEdit(newPassword);
    }

}
