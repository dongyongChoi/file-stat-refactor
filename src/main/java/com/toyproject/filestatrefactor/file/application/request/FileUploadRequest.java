package com.toyproject.filestatrefactor.file.application.request;

public record FileUploadRequest(
        Long userId,
        String fileName,
        String fileExt,
        Long fileSize,
        Long folderId
) {
}
