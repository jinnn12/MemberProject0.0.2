package com.cash.memberProject002.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CommonErrorDto {
    private int statusCode;
    private String statusMessage;

}
