package com.developer.etscase.service;

import com.developer.etscase.dto.request.UserRequest;
import com.developer.etscase.dto.response.UserResponse;
import com.developer.etscase.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> findAllUser();
    Optional<UserEntity> findById(Long id);
    UserResponse getUser(String username, String password);
    ResponseEntity<UserEntity> saveUser(UserEntity userEntity) throws Exception;
    UserEntity updateUser(UserEntity userEntity);
    void deleteUser(Long id);

//    Using Request for Save and Update Employee
    UserResponse saveUser(UserRequest userRequest);
    UserResponse updateUser(UserRequest userRequest, Long id);


}
