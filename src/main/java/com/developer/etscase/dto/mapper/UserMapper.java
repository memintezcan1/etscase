package com.developer.etscase.dto.mapper;

import com.developer.etscase.dto.request.UserRequest;
import com.developer.etscase.dto.response.UserResponse;
import com.developer.etscase.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    UserEntity fromRequestToEntity(UserRequest userRequest);
    UserResponse fromEntityToResponse(UserEntity userEntity);
}
