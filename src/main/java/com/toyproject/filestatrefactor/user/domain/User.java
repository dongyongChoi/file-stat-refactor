package com.toyproject.filestatrefactor.user.domain;

import com.toyproject.filestatrefactor.global.jpa.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String userName;
}
