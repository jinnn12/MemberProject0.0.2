package com.cash.memberProject002.Post.controller;

import com.cash.memberProject002.Post.dto.PostCreateDto;
import com.cash.memberProject002.Post.service.PostService;
import com.cash.memberProject002.common.CommonDto;
import com.sun.net.httpserver.HttpsServer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")


public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //    게시글 등록
    @PostMapping("/create")
    public ResponseEntity<?> save(@Valid @RequestBody PostCreateDto postCreateDto) {
        postService.save(postCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommonDto.builder()
                        .result("OK")
                        .statusCode(HttpStatus.CREATED.value())
                        .statusMessage("게시글 등록 완료!")
                        .build());
    }

    //    게시글 전체 조회
    @GetMapping("/list")
    public ResponseEntity<?> findAll(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        postService.findAll(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonDto.builder()
                        .result("OK")
                        .statusCode(HttpStatus.OK.value())
                        .statusMessage("게시글 목록 입니다.")
                        .build());
    }

    //    게시글 상세 조회
    @GetMapping("list/{inputId}")
    public ResponseEntity<?> findById(@PathVariable Long inputId) {
        postService.findById(inputId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonDto.builder()
                        .result("OK")
                        .statusCode(HttpStatus.OK.value())
                        .statusMessage("{inputId}번의 게시글 정보 입니다.")
                        .build());
    }
}
