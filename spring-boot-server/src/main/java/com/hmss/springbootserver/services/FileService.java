package com.hmss.springbootserver.services;

import com.hmss.springbootserver.entities.FileMetadata;
import com.hmss.springbootserver.entities.User;
import com.hmss.springbootserver.enums.AppFileType;
import com.hmss.springbootserver.repositories.FileMetadataRepository;
import com.hmss.springbootserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Value("${file.system.path}")
    private String fileSystemPath;

    @Value("${file.profile-images.path}")
    private String profileImagesPath;

    @Value("${server.address}")
    private String serverAddress;

    @Value("${server.port}")
    private int serverPort;
    private final FileMetadataRepository fileMetadataRepository;

    @Autowired
    public FileService(FileMetadataRepository fileMetadataRepository, UserRepository userRepository) {
        this.fileMetadataRepository = fileMetadataRepository;
    }

    public void saveFileAndMetadata(MultipartFile file, User user, AppFileType type,boolean isUnique) throws IOException{
        //find the right path
        String uploadDirPath;
        String fileDirPath;
        switch (type){
            case PROFILE_IMAGE:
                uploadDirPath = fileSystemPath + profileImagesPath;
                fileDirPath = profileImagesPath;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        // save to the filesystem
        String path = this.saveFileToFileSystem(file,uploadDirPath);
        String subPath = fileDirPath + extractFileName(path);

        //save to db
        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setPath(subPath);
        fileMetadata.setName(file.getOriginalFilename());
        fileMetadata.setType(type);
        fileMetadata.setCreatedAt(LocalDateTime.now());
        fileMetadata.setUser(user);
        fileMetadataRepository.save(fileMetadata);

        //delete old records if needed
        if(isUnique){
            List<FileMetadata> fileMetadataForDeletion = fileMetadataRepository.findAllByUserIdAndType(user.getId(),type).stream().filter(el->el.getId() != fileMetadata.getId()).collect(Collectors.toList());
            fileMetadataForDeletion.forEach(el->{
                try {
                    this.deleteFileFromFileSystem(fileSystemPath + el.getPath());
                    fileMetadataRepository.delete(el);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
    }

    public String saveFileToFileSystem(MultipartFile file, String path) throws IOException {
        // Check if the file is not empty
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        try {
            // Save the file to the uploads directory
            File uploadDir = new File(path);
            File destFile = new File(uploadDir.getAbsolutePath() + File.separator + System.currentTimeMillis() + file.getOriginalFilename().replaceAll("\\s", ""));
            file.transferTo(destFile);
            return destFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Failed to upload the file", e);
        }
    }

    public void deleteFileFromFileSystem(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (Files.exists(path)){
            Files.delete(path);
        }
    }

    public String extractFileName(String filePath) {
        Path path = Paths.get(filePath);
        return path.getFileName().toString();
    }

    public String getFullPath(String filePath){
        if(filePath == null) return null;
        return "http://" + serverAddress + ":" + serverPort + "/" + filePath;
    }
}
