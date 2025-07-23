package com.cash.memberProject002.Post.repository;

import com.cash.memberProject002.Post.domain.Post;
import com.cash.memberProject002.author.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByDelYn(Pageable pageable, String delYn);

    List<Post> findByAuthor(Author author);

    @Query("select p from Post p inner join fetch p.author")
    List<Post> findAllFetchJoin();
}
