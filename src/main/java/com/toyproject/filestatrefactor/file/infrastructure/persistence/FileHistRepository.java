package com.toyproject.filestatrefactor.file.infrastructure.persistence;

import com.toyproject.filestatrefactor.file.domain.FileHist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileHistRepository extends JpaRepository<FileHist, Long> {
}
