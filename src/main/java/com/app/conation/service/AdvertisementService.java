package com.app.conation.service;

import com.app.conation.domain.*;
import com.app.conation.provider.AdvertisementProvider;
import com.app.conation.provider.RegionProvider;
import com.app.conation.repository.AdvertisementRepository;
import com.app.conation.repository.UserRepository;
import com.app.conation.requestdto.PatchAdvertisementReq;
import com.app.conation.requestdto.PostAdvertisementReq;
import com.app.conation.requestdto.ViewAdvertisementReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.app.conation.util.Constant.DEFAULT_VIEW_COUNT;
import static com.app.conation.util.Constant.ONE;

@Transactional
@Service
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementProvider advertisementProvider;
    private final DonationStatusService donationStatusService;
    private final UserRepository userRepository;

    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository, AdvertisementProvider advertisementProvider
        , DonationStatusService donationStatusService, UserRepository userRepository, RegionProvider regionProvider) {
        this.advertisementRepository = advertisementRepository;
        this.advertisementProvider = advertisementProvider;
        this.donationStatusService = donationStatusService;
        this.userRepository = userRepository;
    }

    public Long createAdvertisement(PostAdvertisementReq request) {
        Advertisement advertisement = Advertisement.builder()
                .advertisementName(request.getAdvertisementName())
                .url(request.getUrl())
                .length(request.getLength())
                .score(request.getScore())
                .advertisementOwnerId(request.getAdvertisementOwnerId())
                .advertisementCategory(request.getAdvertisementCategory())
                .state(State.ACTIVE)
                .viewCount(DEFAULT_VIEW_COUNT)
                .build();
        Advertisement savedAdvertisement = advertisementRepository.save(advertisement);
        return savedAdvertisement.getId();
    }

    public Long updateAdvertisement(PatchAdvertisementReq request) {
        Long advertisementId = request.getAdvertisementId();
        Advertisement advertisement = advertisementProvider.getAdvertisementById(advertisementId);
        advertisement.setAdvertisementCategory(request.getAdvertisementCategory())
                .setAdvertisementName(request.getAdvertisementName())
                .setAdvertisementOwnerId(request.getAdvertisementOwnerId())
                .setLength(request.getLength())
                .setScore(request.getScore())
                .setUrl(request.getUrl());
        advertisementRepository.save(advertisement);
        return request.getAdvertisementId();
    }

    public Long deleteAdvertisement(Long advertisementId) {
        Advertisement advertisement = advertisementProvider.getAdvertisementById(advertisementId);
        advertisement.setState(State.INACTIVE);
        return advertisementId;
    }

    public Long viewAdvertisement(ViewAdvertisementReq viewAdvertisementRequest) {
        Advertisement advertisement = advertisementProvider
            .getAdvertisementById(viewAdvertisementRequest.getAdvertisementId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((User) authentication.getPrincipal());
        Region region = user.getRegionId();

        donationStatusService.addTodayDonationScore(region, advertisement.getScore());
        advertisement.setViewCount(advertisement.getViewCount() + ONE);
        advertisementRepository.save(advertisement);

        long totalMyPoint = user.getExperiencePoint() + advertisement.getScore();
        userRepository.updateExperiencePoint(totalMyPoint, user.getId());

        return viewAdvertisementRequest.getAdvertisementId();
    }
}
