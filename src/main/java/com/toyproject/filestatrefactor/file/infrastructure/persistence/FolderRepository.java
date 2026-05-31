package com.toyproject.filestatrefactor.file.infrastructure.persistence;

import com.toyproject.filestatrefactor.file.domain.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
