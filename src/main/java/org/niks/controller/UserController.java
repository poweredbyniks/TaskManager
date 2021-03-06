package org.niks.controller;

import org.niks.entity.User;
import org.niks.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-management")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public User userVerify(@RequestHeader(value = "userName") String userName,
                           @RequestHeader(value = "password") String password) {
        return userService.userVerify(userName, password);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody User user) {
        userService.create(user);
    }

    @GetMapping("/users/account/info")
    public User userInfo() {
        return userService.userInfo();
    }

    @PostMapping("/users/account/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void passwordEdit(@RequestBody String newPassword) {
        userService.passwordEdit(newPassword);
    }
}
