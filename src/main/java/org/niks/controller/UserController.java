package org.niks.controller;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.User;
import org.niks.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users-management")
public class UserController {

    IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")

    public User userVerify(@RequestBody User user) {
        return userService.userVerify(user);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody User user) {
        userService.create(user);
    }

    @GetMapping("/users/account/info")
    @NotNull
    public User findUser() {
        return userService.userInfo();
    }

    @PostMapping("/users/account/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void passwordEdit(@RequestBody String newPassword) {
        userService.passwordEdit(newPassword);
    }

}
