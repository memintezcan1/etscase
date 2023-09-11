package com.developer.etscase.controller;


import com.developer.etscase.dto.request.UserRequest;
import com.developer.etscase.dto.response.UserResponse;
import com.developer.etscase.entity.UserEntity;
import com.developer.etscase.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> findAllUser() {
        return userService.findAllUser();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> findUserById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping("/get-user")
    @CrossOrigin(origins = "http://localhost:3000")
    public UserResponse getUser(@RequestBody UserRequest userRequest) {
        return userService.getUser(userRequest.getUsername(), userRequest.getPassword());
    }

    @PostMapping("/save-user")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity userEntity) throws Exception {
        return userService.saveUser(userEntity);
    }

    @PutMapping
    public UserEntity updateEmployee(@RequestBody UserEntity userEntity) {
        return userService.updateUser(userEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

//    Using Request and Response with save and update employee

    @PostMapping("/res")
    public UserResponse saveEmpResponse(@RequestBody UserRequest userRequest) {
        return userService.saveUser(userRequest);
    }

    @PutMapping("/res/{id}")
    public UserResponse updateEmpResponse(@RequestBody UserRequest userRequest, @PathVariable("id") Long id) {
        return userService.updateUser(userRequest, id);
    }

}
