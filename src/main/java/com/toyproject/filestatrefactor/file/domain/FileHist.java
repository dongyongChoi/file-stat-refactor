package com.toyproject.filestatrefactor.file.domain;

import com.toyproject.filestatrefactor.global.jpa.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "file_hist")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileHist extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

    @Column(name = "file_size", nullable = false)
    private Long fileSize;
}
