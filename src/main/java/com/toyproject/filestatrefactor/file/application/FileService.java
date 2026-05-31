package com.toyproject.filestatrefactor.file.application;

import com.toyproject.filestatrefactor.file.application.request.FileDeleteRequest;
import com.toyproject.filestatrefactor.file.application.request.FileMoveRequest;
import com.toyproject.filestatrefactor.file.application.request.FileUploadRequest;
import com.toyproject.filestatrefactor.file.domain.File;
import com.toyproject.filestatrefactor.file.domain.FileHist;
import com.toyproject.filestatrefactor.file.domain.Folder;
import com.toyproject.filestatrefactor.file.infrastructure.persistence.FileHistRepository;
import com.toyproject.filestatrefactor.file.infrastructure.persistence.FileRepository;
import com.toyproject.filestatrefactor.file.infrastructure.persistence.FolderRepository;
import com.toyproject.filestatrefactor.user.domain.User;
import com.toyproject.filestatrefactor.user.infrastructure.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileService {

    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final FolderRepository folderRepository;
    private final FileHistRepository fileHistRepository;

    @Transactional
    public Long upload(FileUploadRequest fileUploadRequest) {
        User user = userRepository.findById(fileUploadRequest.userId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Folder folder = folderRepository.findById(fileUploadRequest.folderId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 폴더입니다."));

        File uploadFile = File.create(user, fileUploadRequest.fileName(), fileUploadRequest.fileExt(), folder);

        Long fileId = fileRepository.save(uploadFile).getId();

        fileHistRepository.save(FileHist.create(uploadFile, fileUploadRequest.fileSize()));

        return fileId;
    }

    @Transactional
    public void delete(FileDeleteRequest fileDeleteRequest) {
        throw new UnsupportedOperationException();
    }

    @Transactional
    public void moveToTrash(FileMoveRequest fileMoveRequest) {
        throw new UnsupportedOperationException();
    }
}
