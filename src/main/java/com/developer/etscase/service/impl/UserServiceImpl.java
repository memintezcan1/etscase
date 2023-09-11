package com.developer.etscase.service.impl;

import com.developer.etscase.dto.mapper.UserMapper;
import com.developer.etscase.dto.request.UserRequest;
import com.developer.etscase.dto.response.UserResponse;
import com.developer.etscase.entity.UserEntity;
import com.developer.etscase.repository.UserRepository;
import com.developer.etscase.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserResponse getUser(String username, String password){
        UserEntity userEntity = userRepository.getUser(username, password);

        return UserMapper.MAPPER.fromEntityToResponse(userEntity);
    }

    @Override
    public ResponseEntity<UserEntity> saveUser(UserEntity userEntity) throws Exception {
        UserEntity user = new UserEntity();
        if(userEntity.getUsername() != null){
           user = userRepository.findUserByUsername(userEntity.getUsername());
        }
        if(user == null){
           user = userRepository.save(userEntity);
        }
        else{
            throw new Exception("Kullanıcı Kaydı mevcut");
        }
        return ResponseEntity.ok(user);
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

//    Using Request and Response with save and update employee

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        UserEntity userEntity = UserMapper.MAPPER.fromRequestToEntity(userRequest);
        userRepository.save(userEntity);
        return UserMapper.MAPPER.fromEntityToResponse(userEntity);
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest, Long id) {

        Optional<UserEntity> checkExistingEmployee = findById(id);
        if (! checkExistingEmployee.isPresent())
            throw new RuntimeException("User ID "+ id + " Not Found!");

        UserEntity userEntity = UserMapper.MAPPER.fromRequestToEntity(userRequest);
        userEntity.setId(id);
        userRepository.save(userEntity);
        return UserMapper.MAPPER.fromEntityToResponse(userEntity);
    }

}
