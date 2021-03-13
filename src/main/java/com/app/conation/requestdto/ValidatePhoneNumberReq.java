package com.app.conation.requestdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class ValidatePhoneNumberReq {

    @NotNull
    private Long certificationId;

    @NotBlank
    private String certificationNumber;
}
