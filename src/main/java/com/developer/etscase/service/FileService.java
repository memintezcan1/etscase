package com.developer.etscase.service;

import com.developer.etscase.dto.request.FileRequest;
import com.developer.etscase.dto.response.FileResponse;
import com.developer.etscase.entity.FileEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface FileService {
    List<FileEntity> findAllFile(Long id, Boolean aktif);
    Optional<FileEntity> findById(Long id);
    ResponseEntity saveFile(FileRequest fileRequest);
    FileResponse updateFile(FileRequest fileRequest);
    void deleteFile(Long id);

}
