package com.toyproject.filestatrefactor.file.infrastructure.persistence;

import com.toyproject.filestatrefactor.file.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
