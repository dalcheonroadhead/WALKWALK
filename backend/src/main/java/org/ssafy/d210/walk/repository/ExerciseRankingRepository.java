package org.ssafy.d210.walk.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.walk.dto.response.FriendRankingResponseDto;

import java.time.LocalDate;

@Repository
public interface ExerciseRankingRepository {
    Slice<FriendRankingResponseDto> findStepsRankingByPage(Long myId, Pageable pageable, LocalDate startDate, LocalDate endDate);

    Slice<FriendRankingResponseDto> findStreakRankingByPage(Long myId, Pageable pageable, LocalDate yesterday);
}
