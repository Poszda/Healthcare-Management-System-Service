package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/files")
public class FileServingController {

    private final FileService fileService;

    @Autowired
    public FileServingController(FileService fileService) {
        this.fileService = fileService;
    }

//    @GetMapping("/{filename:.+}")
//    public ResponseEntity<InputStreamResource> serveFile(){
//        return this.fileService.serveFile();
//    }
}
