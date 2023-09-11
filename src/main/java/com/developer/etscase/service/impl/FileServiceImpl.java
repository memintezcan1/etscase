package com.developer.etscase.service.impl;

import com.developer.etscase.dto.mapper.FileMapper;
import com.developer.etscase.dto.request.FileRequest;
import com.developer.etscase.dto.response.FileResponse;
import com.developer.etscase.entity.FileEntity;
import com.developer.etscase.repository.FileRepository;
import com.developer.etscase.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public List<FileEntity> findAllFile(Long id, Boolean aktif) {
        return fileRepository.getAllFileByUserId(id, aktif);
    }

    @Override
    public Optional<FileEntity> findById(Long id) {
        return fileRepository.findById(id);
    }

    @Override
    public ResponseEntity saveFile(FileRequest fileRequest) {
        FileEntity fileEntity = FileMapper.MAPPER.fromRequestToEntity(fileRequest);
        fileRepository.save(fileEntity);
        return ResponseEntity.ok("Başarılı");
    }

    @Override
    public FileResponse updateFile(FileRequest fileRequest) {
        return null;
    }

    @Override
    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }
}
