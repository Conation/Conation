package com.app.conation.controller;

import com.app.conation.requestdto.CertificatePhoneNumberReq;
import com.app.conation.requestdto.ValidatePhoneNumberReq;
import com.app.conation.response.BaseResponse;
import com.app.conation.response.BaseResponseStatus;
import com.app.conation.service.CertificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "전화번호 인증")
@RequiredArgsConstructor
@RestController
public class CertificationController {

    private final CertificationService certificationService;

    @ApiOperation(value = "유저 휴대폰 번호 인증 번호 요청")
    @PostMapping("/certificate-phone-number")
    public BaseResponse<Long> certificatePhoneNumber(@RequestBody @Valid CertificatePhoneNumberReq request) {
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, certificationService.sendCertificationMessage(request));
    }

    @ApiOperation(value = "인증번호 일치 여부 검증")
    @PostMapping("/validate-certification-number")
    public BaseResponse<Boolean> validatePhoneNumber(@RequestBody @Valid ValidatePhoneNumberReq request) {
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, certificationService.validateCertification(request));
    }
}
