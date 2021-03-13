package com.app.conation.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignInReq {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;
}
