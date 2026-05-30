package com.toyproject.filestatrefactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FileStatRefactorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileStatRefactorApplication.class, args);
    }

}
