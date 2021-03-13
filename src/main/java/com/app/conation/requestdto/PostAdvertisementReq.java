package com.app.conation.requestdto;

import com.app.conation.enums.AdvertisementCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class PostAdvertisementReq {

    @NotBlank(message = "광고 이름을 입력해주세요.")
    private String advertisementName;

    @NotBlank(message = "광고 URL을 입력해주세요.")
    private String url;

    @NotNull(message = "광고 시간(초 단위)를 입력해주세요.")
    @Positive(message = "광고 시간(초 단위)는 양수여야 합니다.")
    private Long length;

    @NotNull(message = "광고 점수를 입력해주세요.")
    @Positive(message = "광고 점수는 양수여야 합니다.")
    private Long score;

    @NotNull(message = "광고주 번호를 입력해주세요.")
    @Positive(message = "광고주 번호는 양수여야 합니다.")
    private Long advertisementOwnerId;

    @NotNull(message = "광고 카테고리를 입력해주세요.")
    private AdvertisementCategory advertisementCategory;
}
