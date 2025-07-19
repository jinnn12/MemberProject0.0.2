package com.cash.memberProject002.Post.service;

import com.cash.memberProject002.Post.repository.PostRepository;
import com.cash.memberProject002.author.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public

}
