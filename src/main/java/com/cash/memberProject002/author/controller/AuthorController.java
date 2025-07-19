package com.cash.memberProject002.author.controller;

import com.cash.memberProject002.author.domain.Author;
import com.cash.memberProject002.author.dto.AuthorCreateDto;
import com.cash.memberProject002.author.dto.AuthorUpdatePwDto;
import com.cash.memberProject002.common.CommonDto;
import com.cash.memberProject002.author.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor

public class AuthorController {
    private final AuthorService authorService;

    //    회원가입
    @PostMapping("/create")
    public ResponseEntity<?> save(@Valid @RequestBody AuthorCreateDto authorCreateDto) {
        authorService.save(authorCreateDto);
        return new ResponseEntity<>(new CommonDto("가입된 아이디 : ", HttpStatus.CREATED.value(), "가입 완료"), HttpStatus.CREATED);
    }

    //    회원전체조회
    @GetMapping("/list")
    public ResponseEntity<?> findAll() {
        authorService.findAll();
        return new ResponseEntity<>(new CommonDto("회원 리스트 입니다.", HttpStatus.CREATED.value(), "문구"), HttpStatus.CREATED);
    }

    //    회원단건조회 : 아이디
    @GetMapping("/detail/{inputId}")
    public ResponseEntity<?> findById(@PathVariable Long inputId) {
        authorService.findById(inputId);
        return new ResponseEntity<>(new CommonDto("상세 조회 완료", HttpStatus.CREATED.value(), "찾기 완료"), HttpStatus.CREATED);
    }

    //    회원비밀번호수정
    @PatchMapping("/updatepassword")
    public void updatePassword(@RequestBody AuthorUpdatePwDto authorUpdatePwDto) {
        authorService.updatePassword(authorUpdatePwDto);
    }

//    회원탈퇴
    @DeleteMapping("/delete/{inputId}")
    public void delete(@PathVariable Long inputId) {
        authorService.delete(inputId);
    }
}
