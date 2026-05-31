package com.toyproject.filestatrefactor.file.application.request;

public record FileMoveRequest(
        Long userId,
        Long folderId) {
}
