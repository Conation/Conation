package com.app.conation.advice;

import com.app.conation.advice.exceptions.NotFoundCertificationException;
import com.app.conation.response.BaseResponse;
import com.app.conation.response.BaseResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CertificateAdvisor {

    @ExceptionHandler(NotFoundCertificationException.class)
    public BaseResponse<Object> notFoundCertificationHandler() {
        return new BaseResponse<>(BaseResponseStatus.NOT_EXIST_CERTIFICATION_ERROR);
    }
}
