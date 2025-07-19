package com.cash.memberProject002.author.repository;

import com.cash.memberProject002.author.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    public Optional<Author> findByEmail(String email);
}
