package com.toyproject.filestatrefactor.file.domain;

import com.toyproject.filestatrefactor.global.jpa.BaseTimeEntity;
import com.toyproject.filestatrefactor.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "files")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "file_status", nullable = false, length = 10)
    private FileStatus fileStatus;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_ext", nullable = false)
    private String fileExt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id", nullable = false)
    private Folder folder;
}
