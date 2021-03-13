package com.app.conation.advice;

import com.app.conation.advice.exceptions.NotEnoughPointException;
import com.app.conation.response.BaseResponse;
import com.app.conation.response.BaseResponseStatus;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@AllArgsConstructor
public class DrawAdvisor {

    @ExceptionHandler(NotEnoughPointException.class)
    public BaseResponse<Object> passwordsNotEqualExceptionHandler(NotEnoughPointException ex) {
        return new BaseResponse<>(BaseResponseStatus.NOT_ENOUGH_POINT_ERROR);
    }
}
