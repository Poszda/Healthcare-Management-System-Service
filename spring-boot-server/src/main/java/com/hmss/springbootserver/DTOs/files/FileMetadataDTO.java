package com.hmss.springbootserver.DTOs.files;

import com.hmss.springbootserver.entities.User;
import com.hmss.springbootserver.enums.AppFileType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class FileMetadataDTO {
    private Long id;
    private String path;
    private String name;
    private AppFileType type;
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AppFileType getType() {
        return type;
    }

    public void setType(AppFileType type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
