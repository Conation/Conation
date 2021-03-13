package com.app.conation.requestdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@Getter
@Setter
public class ViewAdvertisementReq {

    @Positive(message = "광고 번호는 양수여야 합니다.")
    @NotNull(message = "광고 번호를 입력해주세요.")
    private Long advertisementId;
}
