package com.cash.memberProject002.author.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AuthorUpdatePwDto {
    private String email;
    private String password;
}
