package com.hexagonal.template.infrastructure.repository;

import com.hexagonal.template.infrastructure.entity.BookJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositoryJpa extends JpaRepository<BookJpa, Long> {
}
