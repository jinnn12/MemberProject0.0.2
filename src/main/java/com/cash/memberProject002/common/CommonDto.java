package com.cash.memberProject002.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CommonDto {
    private Object result;
    private int statusCode;
    private String statusMessage;

}
