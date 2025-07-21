package com.cash.memberProject002.Post.service;

import com.cash.memberProject002.Post.domain.Post;
import com.cash.memberProject002.Post.dto.PostCreateDto;
import com.cash.memberProject002.Post.dto.PostDetailDto;
import com.cash.memberProject002.Post.dto.PostListDto;
import com.cash.memberProject002.Post.repository.PostRepository;
import com.cash.memberProject002.author.domain.Author;
import com.cash.memberProject002.author.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional

public class PostService {
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    @Autowired
    public PostService(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

//    게시글 등록
    public void save(PostCreateDto postCreateDto) {
        Author author = authorRepository.findById(postCreateDto.getAuthorId()).orElseThrow(() -> new EntityNotFoundException("없는 아이디입니다."));
        postCreateDto.toEntity(author);
    }

////    게시글 전체 조회 (일반적)
//    public List<PostListDto> findAll(PostListDto postListDto) {
//        List<Post> postList = postRepository.findAll();
//        return postList.stream().map(post -> postListDto.fromEntity(post)).collect(Collectors.toList());
//    }

//    게시글 전체 조회 (Pageable 사용)
    public Page<PostListDto> findAll(Pageable pageable) {
        Page<Post> postList = postRepository.findAllByDelYn(pageable, "N");
        return postList.map(post -> PostListDto.fromEntity(post));
    }

    //    게시글 단건 조회 : 작성자 아이디로
    public PostDetailDto findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("없는 아이디 입니다."));
        return PostDetailDto.fromEntity(post);
    }

}
