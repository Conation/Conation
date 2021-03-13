package com.app.conation.advice;

import com.app.conation.advice.exceptions.*;
import com.app.conation.response.BaseResponse;
import com.app.conation.response.BaseResponseStatus;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class SignInAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected BaseResponse<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        return new BaseResponse<>(BaseResponseStatus.DATA_VALIDATION_ERROR);
    }

    @ExceptionHandler(AlreadyExistIdException.class)
    public BaseResponse<Object> alreadyExistIdExceptionHandler(AlreadyExistIdException ex) {
        return new BaseResponse<>(BaseResponseStatus.ALREADY_EXIST_USERID_ERROR);
    }

    @ExceptionHandler(RegionNotFoundException.class)
    public BaseResponse<Object> regionNotFoundExceptionHandler(RegionNotFoundException ex) {
        return new BaseResponse<>(BaseResponseStatus.NOT_EXIST_REGION_ERROR);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public BaseResponse<Object> invalidPasswordExceptionHandler(InvalidPasswordException ex) {
        return new BaseResponse<>(BaseResponseStatus.INVALID_PASSWORD_ERROR);
    }

    @ExceptionHandler(JWTDecodeException.class)
    public BaseResponse<Object> jwtDecodeExceptionHandler(JWTDecodeException ex) {
        return new BaseResponse<>(BaseResponseStatus.INVALID_JWT_ERROR);
    }

    @ExceptionHandler(NotRegisteredIdException.class)
    public BaseResponse<Object> notRegisteredIdExceptionHandler(NotRegisteredIdException ex) {
        return new BaseResponse<>(BaseResponseStatus.NOT_EXIST_USERID_ERROR);
    }

    @ExceptionHandler(PasswordsNotEqualException.class)
    public BaseResponse<Object> passwordsNotEqualExceptionHandler(PasswordsNotEqualException ex) {
        return new BaseResponse<>(BaseResponseStatus.PASSWORDS_HAVE_TO_SAME_ERROR);
    }

    @ExceptionHandler(NotExistUserException.class)
    public BaseResponse<Object> passwordsNotEqualExceptionHandler(NotExistUserException ex) {
        return new BaseResponse<>(BaseResponseStatus.NOT_EXIST_USER_ERROR);
    }

    @ExceptionHandler(MessageSendFailException.class)
    public BaseResponse<Object> passwordsNotEqualExceptionHandler(MessageSendFailException ex) {
        return new BaseResponse<>(BaseResponseStatus.MESSAGE_SEND_FAIL_ERROR);
    }
}
