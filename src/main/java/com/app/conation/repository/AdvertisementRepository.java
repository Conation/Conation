package com.app.conation.repository;

import com.app.conation.domain.Advertisement;
import com.app.conation.domain.State;
import com.app.conation.enums.AdvertisementCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Query(value = "select a from Advertisement a where a.advertisementCategory = :category and a.state = :state")
    Page<Advertisement> findAdvertisementsInCategory(Pageable pageable,
                                                     @Param("category") AdvertisementCategory category,
                                                     @Param("state") State state);

    @Query(value = "select a from Advertisement a where a.state = :state")
    Page<Advertisement> findAdvertisements(Pageable pageable, @Param("state") State state);
}
