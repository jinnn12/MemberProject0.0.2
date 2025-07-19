package com.cash.memberProject002.Post.repository;

import com.cash.memberProject002.Post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
