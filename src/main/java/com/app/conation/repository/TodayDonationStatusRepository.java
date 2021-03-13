package com.app.conation.repository;

import com.app.conation.domain.Region;
import com.app.conation.domain.TodayDonationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodayDonationStatusRepository extends JpaRepository<TodayDonationStatus, Long> {

    @Query(value = "select tds from TodayDonationStatus tds where tds.regionId = :regionId")
    Optional<TodayDonationStatus> findTodayDonationStatusByRegionId(@Param("regionId") Region regionId);
}
