package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.FileMetadata;
import com.hmss.springbootserver.entities.User;
import com.hmss.springbootserver.enums.AppFileType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileMetadataRepository  extends JpaRepository<FileMetadata,Long> {
    List<FileMetadata> findByUserIdAndType(Long userId, AppFileType type);
}
