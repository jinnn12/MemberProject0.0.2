package com.cash.memberProject002.author.service;

import com.cash.memberProject002.author.domain.Author;
import com.cash.memberProject002.author.dto.AuthorCreateDto;
import com.cash.memberProject002.author.dto.AuthorDetailDto;
import com.cash.memberProject002.author.dto.AuthorListDto;
import com.cash.memberProject002.author.dto.AuthorUpdatePwDto;
import com.cash.memberProject002.author.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorService {
    private final AuthorRepository authorRepository;

//    public void create(AuthorCreateDto authorCreateDto) {
//        if (authorRepository.findByEmail(authorCreateDto.getEmail()).isPresent()) {
//            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
//        }
//        Author author = authorCreateDto.toEntity();
//        this.authorRepository.save(author);
//    }
//
//    public List<AuthorListDto> findAll() {
//        return authorRepository.findAll().stream().map(author -> AuthorListDto.fromEntity(author)).collect(Collectors.toList());
//    }
//
//    public AuthorDetailDto findById(Long id) {
//        Author author = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("아이디가 없습니다."));
//        return AuthorDetailDto.fromEntity(author);
//    }
//
//    public void updatePassword(AuthorUpdatePwDto authorUpdatePwDto) {
//        Author author = authorRepository.findByEmail(authorUpdatePwDto.getEmail()).orElseThrow(() -> new EntityNotFoundException("이메일이 없습니다."));
//        author.updatePassword(authorUpdatePwDto.getPassword());
//    }
//
//    public void delete(Long id) {
//        Author author = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("아이디가 없습니다."));
//        authorRepository.delete(author);
//    }


    public void create(AuthorCreateDto authorCreateDto) {
        if (authorRepository.findByEmail(authorCreateDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }
        Author author = authorCreateDto.toEntity();
        this.authorRepository.save(author);
    }

    public List<AuthorListDto> findAll() {
        return authorRepository.findAll().stream().map(author -> AuthorListDto.fromEntity(author)).collect(Collectors.toList());
    }

    public AuthorDetailDto findById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("존재하는 아이디 입니다."));
        return AuthorDetailDto.fromEntity(author);
    }

    public void updatePassword(AuthorUpdatePwDto authorUpdatePwDto) {
        Author author = authorRepository.findByEmail(authorUpdatePwDto.getEmail()).orElseThrow(() -> new EntityNotFoundException("없는 이메일 입니다."));
        author.updatePassword(authorUpdatePwDto.getPassword());
    }

    public void delete(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("아이디가 없습니다."));
        authorRepository.delete(author);
    }



}
