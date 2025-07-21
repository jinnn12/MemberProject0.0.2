package com.cash.memberProject002.author.dto;

import com.cash.memberProject002.author.domain.Author;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AuthorDetailDto {
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private Integer postCount;
    private LocalDateTime createdTime;


    public static AuthorDetailDto fromEntity(Author author) {
        return AuthorDetailDto.builder()
                .name(author.getName())
                .email(author.getEmail())
                .password(author.getPassword())
//                .postCount()
                .createdTime(author.getCreatedTime())
                .build();
    }
}
