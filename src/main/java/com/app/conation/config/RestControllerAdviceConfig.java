package com.app.conation.config;

import com.app.conation.advice.exceptions.BaseException;
import com.app.conation.response.BaseResponse;
import com.app.conation.response.BaseResponseStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestControllerAdviceConfig {

    @ExceptionHandler(BaseException.class)
    public BaseResponse baseExceptionHandler(BaseException e) {
        return new BaseResponse(e.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<List<String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<String> errorMessages = new ArrayList<>();
        e.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errorMessages.add(fieldError.getDefaultMessage()));
        return new BaseResponse(BaseResponseStatus.PLEASE_CHECK_REQUEST, errorMessages);
    }
}
