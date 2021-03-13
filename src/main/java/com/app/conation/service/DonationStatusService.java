package com.app.conation.service;

import com.app.conation.domain.TodayDonationStatus;
import com.app.conation.repository.TodayDonationStatusRepository;
import com.app.conation.domain.Region;
import com.app.conation.provider.TodayDonationStatusProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DonationStatusService {

    private final TodayDonationStatusRepository donationStatusRepository;
    private final TodayDonationStatusProvider donationStatusProvider;

    @Autowired
    public DonationStatusService(TodayDonationStatusRepository donationStatusRepository, TodayDonationStatusProvider donationStatusProvider) {
        this.donationStatusRepository = donationStatusRepository;
        this.donationStatusProvider = donationStatusProvider;
    }

    public Long addTodayDonationScore(Region region, Long donationScore) {
        TodayDonationStatus donationStatus = donationStatusProvider.getDonationStatusByRegionId(region);
        Long todayDonationScore = donationStatus.getTodayDonationScore();
        donationStatus.setTodayDonationScore(todayDonationScore + donationScore);
        donationStatusRepository.save(donationStatus);
        return donationStatus.getId();
    }

    public void resetDonationScore() {
        donationStatusRepository.findAll().stream()
                .forEach(todayDonationStatus -> {
                    Region region = todayDonationStatus.getRegionId();
                    Long accumulatedDonationScore = region.getAccumulatedDonationScore();
                    region.setAccumulatedDonationScore(accumulatedDonationScore + todayDonationStatus.getTodayDonationScore());
                    todayDonationStatus.setTodayDonationScore(0L);
                    donationStatusRepository.save(todayDonationStatus);
                });
    }
}
