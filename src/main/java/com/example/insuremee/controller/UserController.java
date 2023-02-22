package com.example.insuremee.controller;

import com.example.insuremee.domains.User;
import com.example.insuremee.dto.UserInfoDTO;
import com.example.insuremee.exception.DuplicateEntityException;
import com.example.insuremee.exception.NoAccessException;
import com.example.insuremee.exception.NotFoundException;
import com.example.insuremee.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping( path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @PostMapping(path = "/")
    public void addUser(@RequestBody User user) throws DuplicateEntityException {
        userService.addNewUser(user);
    }

    @GetMapping(path = "/{id}")
    public UserInfoDTO getUser(@PathVariable("id") Long id) throws NotFoundException, NoAccessException {
        return userService.renderUser(id);
    }

    @PutMapping(path = "/{id}")
    public void updateUser(@PathVariable("id") Long id, @RequestBody UserInfoDTO userInfoDTO) throws NotFoundException, NoAccessException {
        userService.updateUser(id, userInfoDTO);
    }

}
