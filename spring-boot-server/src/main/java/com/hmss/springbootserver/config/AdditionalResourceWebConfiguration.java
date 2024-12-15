package com.hmss.springbootserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {
    
    @Value("${file.system.path}")
    private String fileSystemPath;

    @Value("${file.profile-images.path}")
    private String profileImagesPath;

    @Value("${file.background-images.path}")
    private String backgroundImagesPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        createFileSystemDirectories();
        registry.addResourceHandler(profileImagesPath + "**")
                .addResourceLocations("file:" + fileSystemPath + profileImagesPath);
        registry.addResourceHandler(backgroundImagesPath + "**")
                .addResourceLocations("file:" + fileSystemPath + backgroundImagesPath);
    }

    // Method to create files directory if it doesn't exist
    private void createFileSystemDirectories() {
        Path filesDir = Paths.get(fileSystemPath);
        if (!Files.exists(filesDir)) {
            try {
                Files.createDirectories(filesDir);
            } catch (IOException e) {
                throw new RuntimeException("Could not create file system directory!", e);
            }
        }

        // Only attempt to create subdirectories if the main directory exists
        if (Files.exists(filesDir)) {
            createFileSystemSubdirectories(filesDir,profileImagesPath);
            createFileSystemSubdirectories(filesDir,backgroundImagesPath);
        }
    }

    private void createFileSystemSubdirectories(Path filesDir,String subdirectoryPath) {
        Path subDir = filesDir.resolve(subdirectoryPath);
        if (!Files.exists(subDir)) {
            try {
                Files.createDirectories(subDir);
            } catch (IOException e) {
                throw new RuntimeException("Could not create '" + subdirectoryPath + "' directory!", e);
            }
        }
    }
}
