package com.example.managementlibrary.controller;


import com.example.managementlibrary.common.FileInfo;

import com.example.managementlibrary.exception.ApiError;
import com.example.managementlibrary.service.FilesStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.activation.MimetypesFileTypeMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/auth/files")
public class FilesController {
    @Autowired
    FilesStorageService storageService;

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            System.out.println(file.getContentType());
            storageService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return new ResponseEntity<>(message,HttpStatus.OK);

        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return new ResponseEntity<>(new ApiError(HttpStatus.CONFLICT, message, e),HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
            String filename = path.getFileName().toString();
            String contentType=fileTypeMap.getContentType(filename);
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url,contentType);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(fileInfos);
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
        String contentType=fileTypeMap.getContentType(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE,contentType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
