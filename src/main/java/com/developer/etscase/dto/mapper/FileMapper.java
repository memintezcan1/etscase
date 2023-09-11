package com.developer.etscase.dto.mapper;

import com.developer.etscase.dto.request.FileRequest;
import com.developer.etscase.dto.response.FileResponse;
import com.developer.etscase.entity.FileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileMapper {
    FileMapper MAPPER = Mappers.getMapper(FileMapper.class);
    FileEntity fromRequestToEntity(FileRequest fileRequest);
    FileResponse fromEntityToResponse(FileEntity fileEntity);
}
