package com.toyproject.filestatrefactor.file.application;

import com.toyproject.filestatrefactor.file.application.request.FileDeleteRequest;
import com.toyproject.filestatrefactor.file.application.request.FileMoveRequest;
import com.toyproject.filestatrefactor.file.application.request.FileUploadRequest;
import com.toyproject.filestatrefactor.file.domain.File;
import com.toyproject.filestatrefactor.file.domain.FileHist;
import com.toyproject.filestatrefactor.file.domain.FileStatus;
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
    public void delete(Long fileId, FileDeleteRequest fileDeleteRequest) {
        File foundFile = fileRepository.findById(fileId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 파일입니다."));

        // 이미 삭제된 상태값인 경우
        if (FileStatus.DELETED.equals(foundFile.getFileStatus())) {
            throw new IllegalArgumentException("존재하지 않는 파일입니다.");
        }

        foundFile.setFileStatus(FileStatus.DELETED);
    }

    @Transactional
    public void moveToTrash(Long fileId, FileMoveRequest fileMoveRequest) {
        File foundFile = fileRepository.findById(fileId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 파일입니다."));

        // ACTIVE 상태만 휴지통으로 옮길 수 있다.
        if (!FileStatus.ACTIVE.equals(foundFile.getFileStatus())) {
            throw new IllegalArgumentException("휴지통으로 옮길 수 없는 파일입니다.");
        }

        foundFile.setFileStatus(FileStatus.TRASHED);
    }
}
