package com.developer.etscase.controller;


import com.developer.etscase.dto.mapper.UserMapper;
import com.developer.etscase.dto.request.FileRequest;
import com.developer.etscase.entity.FileEntity;
import com.developer.etscase.entity.UserEntity;
import com.developer.etscase.service.FileService;
import com.developer.etscase.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "http://localhost:8080")
public class FileController {

    private final FileService fileService;
    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping("/find-all-file")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<FileEntity> findAllFile(@RequestParam("userId") Long userId,
                                        @RequestParam("aktif") Boolean aktif) {
        return fileService.findAllFile(userId, aktif);
    }

    @GetMapping("/get-file")
    @CrossOrigin(origins = "http://localhost:3000")
    public Optional<FileEntity> findFileById(@RequestParam("id") Long id) {
        return fileService.findById(id);
    }

    @PostMapping("/save-file")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity saveFile(@RequestBody MultipartFile file, @RequestParam("userId") Long userId) throws IOException {
        if(file.getSize() > 5000000){
            throw new RuntimeException("Yüklemek İstediğiniz Dosya Boyutu Maks. Kabul Edilen(5MB) Dosya Boyutunu Aşmaktadır");
        }
        FileRequest fileRequest = new FileRequest();
        fileRequest.setFileName(file.getOriginalFilename());
        fileRequest.setData(file.getBytes());
        fileRequest.setAktif(true);
        UserEntity userEntity = userService.findById(userId).orElse(null);
        fileRequest.setUserId(UserMapper.MAPPER.fromEntityToResponse(userEntity));
        fileService.saveFile(fileRequest);
        return ResponseEntity.ok("Başarılı");
    }

    @DeleteMapping("/delete-file")
    @CrossOrigin(origins = "http://localhost:3000")
    public void deleteFile(@RequestParam("id") Long id) {
        fileService.deleteFile(id);
    }

}
