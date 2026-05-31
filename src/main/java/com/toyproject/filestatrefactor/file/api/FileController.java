package com.toyproject.filestatrefactor.file.api;

import com.toyproject.filestatrefactor.file.application.FileService;
import com.toyproject.filestatrefactor.file.application.request.FileDeleteRequest;
import com.toyproject.filestatrefactor.file.application.request.FileMoveRequest;
import com.toyproject.filestatrefactor.file.application.request.FileUploadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/files/{fileId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long fileId,
            @RequestBody FileDeleteRequest fileDeleteRequest) {
        fileService.delete(fileId, fileDeleteRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/files/{fileId}/trash")
    public ResponseEntity<Void> moveToTrash(
            @PathVariable Long fileId,
            @RequestBody FileMoveRequest fileMoveRequest) {
        fileService.moveToTrash(fileId, fileMoveRequest);
        return ResponseEntity.ok().build();
    }
}
