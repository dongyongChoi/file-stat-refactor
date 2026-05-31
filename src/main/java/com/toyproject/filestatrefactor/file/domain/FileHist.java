package com.toyproject.filestatrefactor.file.domain;

import com.toyproject.filestatrefactor.global.jpa.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "file_hist")
@NoArgsConstructor
@Getter @Setter
public class FileHist extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    public static FileHist create(File file, Long fileSize) {
        FileHist fileHist = new FileHist();
        fileHist.file = file;
        fileHist.fileSize = fileSize;
        return fileHist;
    }
}
