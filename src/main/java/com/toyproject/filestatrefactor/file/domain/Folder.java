package com.toyproject.filestatrefactor.file.domain;

import com.toyproject.filestatrefactor.global.jpa.BaseTimeEntity;
import com.toyproject.filestatrefactor.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "folder")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Folder extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "folder_name")
    private String folderName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
