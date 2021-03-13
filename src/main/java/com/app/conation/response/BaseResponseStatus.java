package com.app.conation.response;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    PLEASE_CHECK_REQUEST(false, 2000, "입력값을 확인해주세요."),
    NOT_FOUND_ADVERTISEMENT(false, 3000, "존재하지 않는 광고입니다."),
    NOT_FOUND_REGION(false, 3100, "존재하지 않는 지역입니다."),
    NOT_FOUND_DONATION_STATUS(false, 3200, "존재하지 않는 지역 기부 현황입니다."),


    // 회원가입, 로그인 관련 에러코드
    DATA_VALIDATION_ERROR(false, 2001, "값을 입력하셔야 합니다."),
    NOT_EXIST_REGION_ERROR(false, 2002, "존재하지 않는 지역입니다."),
    INVALID_PASSWORD_ERROR(false, 2003, "비밀번호가 유효하지 않습니다."),
    INVALID_JWT_ERROR(false, 2004, "Jwt 토큰이 유효하지 않습니다."),
    NOT_EXIST_USERID_ERROR(false, 2005, "존재하지 않는 아이디입니다."),
    PASSWORDS_HAVE_TO_SAME_ERROR(false, 2006, "비밀번호를 같게 입력해야합니다."),
    ALREADY_EXIST_USERID_ERROR(false, 2007, "이미 존재하는 아이디입니다."),
    NOT_EXIST_CERTIFICATION_ERROR(false, 2008, "존재하지 않는 인증입니다."),

    NOT_EXIST_USER_ERROR(false, 2500, "존재하지 않는 회원입니다."),
    MESSAGE_SEND_FAIL_ERROR(false, 2600, "메세지 전송에 실패하였습니다."),
    NOT_ENOUGH_POINT_ERROR(false, 2700, "포인트가 부족합니다.")
    ;


    private boolean isSuccess;
    private int code;
    private String message;

    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
