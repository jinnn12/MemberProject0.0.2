package com.cash.memberProject002.author.dto;

import com.cash.memberProject002.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AuthorDetailDto {
    private String name;
    private String email;
    private String password;

    public static AuthorDetailDto fromEntity(Author author) {
        return AuthorDetailDto.builder()
                .name(author.getName())
                .email(author.getEmail())
                .password(author.getPassword())
                .build();
    }
}
