package com.app.conation.provider;

import com.app.conation.domain.Region;
import com.app.conation.domain.TodayDonationStatus;
import com.app.conation.repository.TodayDonationStatusRepository;
import com.app.conation.advice.exceptions.BaseException;
import com.app.conation.response.BaseResponseStatus;
import com.app.conation.response.dto.GetDonationStatusRes;
import com.app.conation.response.RegionDonationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Transactional(readOnly = true)
@Service
public class TodayDonationStatusProvider {

    private final TodayDonationStatusRepository donationStatusRepository;

    @Autowired
    public TodayDonationStatusProvider(TodayDonationStatusRepository donationStatusRepository) {
        this.donationStatusRepository = donationStatusRepository;
    }

    public TodayDonationStatus getDonationStatusByRegionId(Region region) {
        Optional<TodayDonationStatus> donationStatus = donationStatusRepository.findTodayDonationStatusByRegionId(region);
        if (donationStatus.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_DONATION_STATUS);
        }
        return donationStatus.get();
    }

    public GetDonationStatusRes retrieveDonationStatus() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<TodayDonationStatus> todayDonationStatuses = donationStatusRepository.findAll();
        Long todayDonationScore = getTodayDonationScore(todayDonationStatuses); // 오늘의 기부액
        Long accumulatedTotalScore = getAccumulatedTotalScore(todayDonationStatuses); // 어제까지 각 지역의 누적 기부액 총합
        Long totalScore = accumulatedTotalScore + todayDonationScore; // 조회시점까지 누적된 기부액 총합
        List<RegionDonationStatus> regionDonationStatuses = getRegionDonationStatuses(todayDonationStatuses);
        return new GetDonationStatusRes(totalScore, today, todayDonationScore, regionDonationStatuses);
    }

    private long getTodayDonationScore(List<TodayDonationStatus> todayDonationStatuses) {
        return todayDonationStatuses.stream()
                .mapToLong(TodayDonationStatus::getTodayDonationScore)
                .sum();
    }

    private long getAccumulatedTotalScore(List<TodayDonationStatus> todayDonationStatuses) {
        return todayDonationStatuses.stream()
                .map(TodayDonationStatus::getRegionId)
                .mapToLong(Region::getAccumulatedDonationScore)
                .sum();
    }

    private List<RegionDonationStatus> getRegionDonationStatuses(List<TodayDonationStatus> todayDonationStatuses) {
        return IntStream.range(0, todayDonationStatuses.size())
                .mapToObj(index -> {
                    TodayDonationStatus todayDonationStatus = todayDonationStatuses.get(index);
                    Region region = todayDonationStatus.getRegionId();
                    Long accumulatedRegionDonationScore = todayDonationStatus.getTodayDonationScore() + region.getAccumulatedDonationScore();
                    return new RegionDonationStatus(region.getRegionName(), accumulatedRegionDonationScore);
                })
                .sorted(Comparator.comparing(RegionDonationStatus::getAccumulatedRegionDonationScore).reversed())
                .collect(toList());
    }
}
