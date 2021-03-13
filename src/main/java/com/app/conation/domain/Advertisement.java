package com.app.conation.domain;

import com.app.conation.enums.AdvertisementCategory;
import com.app.conation.response.dto.GetAdvertisementRes;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "advertisement")
public class Advertisement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "advertisementName")
    private String advertisementName;

    @Column(name = "url")
    private String url;

    @Column(name = "length")
    private Long length;

    @Column(name = "score")
    private Long score;

    @Column(name = "advertisementOwnerId")
    private Long advertisementOwnerId;

    @Column(name = "advertisementCategory")
    @Enumerated(EnumType.STRING)
    private AdvertisementCategory advertisementCategory;

    @Column(name = "viewCount")
    private Long viewCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    public GetAdvertisementRes toGetAdvertisementRes() {
        Long advertisementId = this.id;
        String advertisementName = this.advertisementName;
        String url = this.url;
        Long minutes = this.length / 60;
        Long seconds = this.length % 60;
        Long score = this.score;
        Long viewCount = this.viewCount;
        return new GetAdvertisementRes(advertisementId, advertisementName, url, minutes, seconds, score, viewCount);
    }

}