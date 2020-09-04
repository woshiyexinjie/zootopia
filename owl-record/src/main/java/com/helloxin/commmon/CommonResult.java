package com.helloxin.commmon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private boolean success;
    private T data;

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult(Boolean.TRUE,data);
    }

    public static <T> CommonResult<T> fail(T data) {
        return new CommonResult(Boolean.FALSE,data);
    }
}
