package com.app.conation.requestdto;

import com.app.conation.enums.AdvertisementCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@Getter
@Setter
public class PatchAdvertisementReq {

    @Positive(message = "광고 번호는 양수여야 합니다.")
    @NotNull(message = "광고 번호를 입력해주세요.")
    private Long advertisementId;

    @NotBlank(message = "광고 이름을 입력해주세요.")
    private String advertisementName;

    @NotBlank(message = "광고 URL을 입력해주세요.")
    private String url;

    @Positive(message = "광고 시간(초 단위)은 양수여야 합니다.")
    @NotNull(message = "광고 시간(초 단위)을 입력해주세요.")
    private Long length;

    @Positive(message = "광고 점수는 양수여야 합니다.")
    @NotNull(message = "광고 점수를 입력해주세요.")
    private Long score;

    @Positive(message = "광고주 번호는 양수여야 합니다.")
    @NotNull(message = "광고주 번호를 입력해주세요.")
    private Long advertisementOwnerId;

    @NotNull(message = "광고 카테고리를 입력해주세요.")
    private AdvertisementCategory advertisementCategory;
}
