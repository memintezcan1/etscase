package com.developer.etscase.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class FileDTO {

    private MultipartFile file;
    private Long userId;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
