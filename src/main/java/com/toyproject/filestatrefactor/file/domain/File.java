package com.toyproject.filestatrefactor.file.domain;

import com.toyproject.filestatrefactor.global.jpa.BaseTimeEntity;
import com.toyproject.filestatrefactor.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "files")
@NoArgsConstructor
@Getter @Setter
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

    public static File create(User user, String fileName, String fileExt, Folder folder) {
        File file = new File();
        file.user = user;
        file.fileStatus = FileStatus.ACTIVE;
        file.fileName = fileName;
        file.fileExt = fileExt;
        file.folder = folder;
        return file;
    }
}
