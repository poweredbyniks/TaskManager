package org.niks.controller;

import org.niks.entity.User;
import org.niks.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-management")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public User userVerify(@RequestHeader(value = "userName") final String userName,
                           @RequestHeader(value = "password") final String password) {
        return userService.userVerify(userName, password);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final User user) {
        userService.create(user);
    }

    @GetMapping("/users/account/info/{userID}")
    public User userInfo(@PathVariable("userID") final long userID) {
        return userService.userInfo(userID);
    }

    @PostMapping("/users/account/password/{userID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void passwordEdit(@PathVariable("userID") final long userID, @RequestBody final String newPassword) {
        userService.passwordEdit(userID, newPassword);
    }
}
