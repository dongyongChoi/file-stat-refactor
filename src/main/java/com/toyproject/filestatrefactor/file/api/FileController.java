package com.toyproject.filestatrefactor.file.api;

import com.toyproject.filestatrefactor.file.application.FileService;
import com.toyproject.filestatrefactor.file.application.request.FileUploadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/files")
    public ResponseEntity<?> upload(@RequestBody FileUploadRequest fileUploadRequest) {
        Long fileId = fileService.upload(fileUploadRequest);
        return ResponseEntity
                .created(URI.create("/files/" + fileId))
                .body(Map.of("fileId", fileId));
    }
}
