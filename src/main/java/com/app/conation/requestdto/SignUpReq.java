package com.app.conation.requestdto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "nickname", "password", "passwordRepeat", "cityId", "phoneNumber"})
public class SignUpReq {

    @NotBlank
    private String id;

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordRepeat;

    @Positive
    private Long regionId;

    @NotBlank
    private String phoneNumber;
}
