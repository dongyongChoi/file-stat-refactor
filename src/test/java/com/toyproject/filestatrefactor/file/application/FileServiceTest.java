package com.toyproject.filestatrefactor.file.application;

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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

    @InjectMocks
    FileService fileService;

    @Mock
    FileRepository fileRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    FolderRepository folderRepository;

    @Mock
    FileHistRepository fileHistRepository;

    @Captor
    ArgumentCaptor<File> fileArgumentCaptor;

    @Captor
    ArgumentCaptor<FileHist> fileHistArgumentCaptor;

    @Test
    @DisplayName("파일 업로드를 수행한다")
    void fileUploadTest() {
        // given
        Long userId = 999L;
        Long folderId = 888L;
        String fileName = "테스트파일명";
        String fileExt = "docx";
        Long fileSize = 1024L;

        FileUploadRequest fileUploadRequest = new FileUploadRequest(
                userId,
                fileName,
                fileExt,
                fileSize,
                folderId
        );

        User user = new User();
        user.setId(userId);
        user.setUserName("홍길동");

        Folder folder = new Folder();
        folder.setId(folderId);
        folder.setFolderName("내폴더");
        folder.setUser(user);

        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        given(folderRepository.findById(folderId)).willReturn(Optional.of(folder));
        given(fileRepository.save(any())).willAnswer(invocation -> {
            File file = invocation.getArgument(0);
            file.setId(1L);
            return file;
        });
        given(fileHistRepository.save(any())).willAnswer(invocation -> {
            FileHist fileHist = invocation.getArgument(0);
            fileHist.setId(1L);
            return fileHist;
        });

        // when
        Long uploadFileId = fileService.upload(fileUploadRequest);

        // then
        then(fileRepository).should().save(fileArgumentCaptor.capture());
        then(fileHistRepository).should().save(fileHistArgumentCaptor.capture());

        File savedFile = fileArgumentCaptor.getValue();
        assertThat(savedFile.getFileStatus()).isEqualTo(FileStatus.ACTIVE);
        assertThat(savedFile.getUser()).isEqualTo(user);
        assertThat(savedFile.getFolder()).isEqualTo(folder);
        assertThat(savedFile.getFileName()).isEqualTo(fileName);
        assertThat(savedFile.getFileExt()).isEqualTo(fileExt);

        FileHist savedFileHist = fileHistArgumentCaptor.getValue();
        assertThat(savedFileHist.getId()).isNotNull();
        assertThat(savedFileHist.getFileSize()).isEqualTo(fileSize);

        assertThat(uploadFileId).isNotNull();
        assertThat(uploadFileId).isEqualTo(1L);
    }
}