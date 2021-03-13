package com.app.conation.requestdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class CertificatePhoneNumberReq {

    @NotBlank
    private String phoneNumber;
}
