package com.tripload.repository;

import com.tripload.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowRepository extends JpaRepository<Follow, Integer> {

    @Modifying
    @Query(value= "insert into Follow(toMemberId, fromMemberId) VALUES(:toMemberId, :fromMemberId)", nativeQuery = true)
    void saveFollow(@Param("toMemberId") Long toMemberId, @Param("fromMemberId") Long fromMemberId);

    @Modifying
    @Query("delete from Follow where toMemberId = :toMemberId and fromMemberId = :fromMemberId")
    void deleteByToMemberIdAndFromMemberId(@Param("toMemberId") Long toMemberId, @Param("fromMemberId") Long fromMemberId);

    int countByToMemberId(Long toMemberId);

    boolean existsByToMemberIdAndFromMemberId(int toMemberId, int fromMemberId);
}
