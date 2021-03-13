package com.app.conation.repository;

import com.app.conation.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

    @Modifying
    @Query("UPDATE User u SET u.experiencePoint = :discountedPoint WHERE u.id = :id")
    Integer updateExperiencePoint(@Param("discountedPoint") Long discountedPoint,
                                  @Param("id") Long id);
}
