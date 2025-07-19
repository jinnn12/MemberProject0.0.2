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

public class AuthorListDto {
    private String name;
    private String email;


    public static AuthorListDto fromEntity(Author author) {
        return AuthorListDto.builder()
                .name(author.getName())
                .email(author.getEmail())
                .build();
    }
}
