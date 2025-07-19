package com.cash.memberProject002.author.dto;

import com.cash.memberProject002.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AuthorCreateDto {
    private String name;
    private String email;
    private String password;

    public Author toEntity() {
        return Author.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
    }

}
