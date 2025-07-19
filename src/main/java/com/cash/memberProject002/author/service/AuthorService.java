package com.cash.memberProject002.author.service;

import com.cash.memberProject002.author.dto.AuthorCreateDto;
import com.cash.memberProject002.author.dto.AuthorDetailDto;
import com.cash.memberProject002.author.dto.AuthorListDto;
import com.cash.memberProject002.author.dto.AuthorUpdatePwDto;
import com.cash.memberProject002.author.domain.Author;
import com.cash.memberProject002.author.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class AuthorService {
    private final AuthorRepository authorRepository;

//    회원가입
    public Author save(AuthorCreateDto authorCreateDto) {
        if (authorRepository.findByEmail(authorCreateDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("중복 이메일이 있습니다.");
        }
        return authorRepository.save(authorCreateDto.toEntity());
    }

//    회원전체조회
    public List<AuthorListDto> findAll() {
        List<Author> authorList = authorRepository.findAll();
        return authorList.stream().map(author -> AuthorListDto.fromEntity(author)).collect(Collectors.toList());
    }

//    회원단건조회 : id
    public AuthorDetailDto findById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("아이디가 없습니다."));
        return AuthorDetailDto.fromEntity(author);
    }

    //    회원비밀번호변경 : email을 통해 변경
    public void updatePassword(AuthorUpdatePwDto authorUpdatePwDto) {
        Author author = authorRepository.findByEmail(authorUpdatePwDto.getEmail()).orElseThrow(() -> new EntityNotFoundException("이메일이 없습니다."));
        author.updatePassword(authorUpdatePwDto.getPassword());
    }

    //    회원탈퇴 : id로 탈퇴
    public void delete(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("삭제하고자 하는 아이디가 없습니다."));
        authorRepository.delete(author);
    }
}
