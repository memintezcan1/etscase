package com.developer.etscase.dto.response;

import java.io.Serializable;

public class FileResponse implements Serializable {

    private Long id;
    private String fileName;
    private byte[] data;
    private Boolean aktif;
    private UserResponse userId;

    public FileResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Boolean getAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    public UserResponse getUserId() {
        return userId;
    }

    public void setUserId(UserResponse userId) {
        this.userId = userId;
    }
}
