package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.FileMetadata;
import com.hmss.springbootserver.entities.User;
import com.hmss.springbootserver.enums.AppFileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FileMetadataRepository  extends JpaRepository<FileMetadata,Long> {
    List<FileMetadata> findAllByUserIdAndType(Long userId, AppFileType type);

    Optional<FileMetadata> findByUserIdAndType(Long userId, AppFileType type);
}
