package com.cash.memberProject002.author.controller;

import com.cash.memberProject002.author.dto.AuthorCreateDto;
import com.cash.memberProject002.author.dto.AuthorUpdatePwDto;
import com.cash.memberProject002.author.service.AuthorService;
import com.cash.memberProject002.common.CommonDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")

public class AuthorController {
    private final AuthorService authorService;

    //    회원가입
    @PostMapping("/create")
    public ResponseEntity<?> save(@Valid @RequestBody AuthorCreateDto authorCreateDto) {
        authorService.create(authorCreateDto);
        return new ResponseEntity<>(CommonDto.builder()
                .result("OK")
                .statusCode(HttpStatus.CREATED.value())
                .statusMessage("회원가입 완료!")
                .build(), HttpStatus.CREATED);
    }

    //    회원 전체 조회
    @GetMapping("/list")
    public ResponseEntity<?> findAll() {
        authorService.findAll();
        return new ResponseEntity<>(CommonDto.builder()
                .result("목록조회")
                .statusCode(HttpStatus.FOUND.value())
                .statusMessage("회원 전체 리스트입니다.")
                .build(), HttpStatus.FOUND);
    }

    //    회원 단건 조회
    @GetMapping("/list/{inputId}")
    public ResponseEntity<?> findById(@PathVariable Long inputId) {
        authorService.findById(inputId);
        return new ResponseEntity<>(CommonDto.builder()
                .result("{inputId} 아이디 회원 조회")
                .statusCode(HttpStatus.FOUND.value())
                .statusMessage("{inputId} 회원 정보입니다.")
                .build(), HttpStatus.FOUND);
    }


    //    이메일로 비밀번호 수정
    @PatchMapping("/updatepw")
    public ResponseEntity<?> updatePassword(AuthorUpdatePwDto authorUpdatePwDto) {
        authorService.updatePassword(authorUpdatePwDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonDto.builder()
                        .result("OK")
                        .statusCode(HttpStatus.OK.value())
                        .statusMessage("비밀번호 수정 완료")
                        .build());
    }

    //    아이디로 회원 탈퇴
    @DeleteMapping("/delete/{inputId}")
    public ResponseEntity<?> delete(@PathVariable Long inputId) {
        authorService.delete(inputId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonDto.builder()
                        .result("OK")
                        .statusCode(HttpStatus.OK.value())
                        .statusMessage("삭제 완료")
                        .build());
    }

}
