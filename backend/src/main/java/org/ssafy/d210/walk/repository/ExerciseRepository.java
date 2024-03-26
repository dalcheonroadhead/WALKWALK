package org.ssafy.d210.walk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.ssafy.d210.walk.dto.response.StreakRankingResopnseDto;
import org.ssafy.d210.walk.dto.response.ThisWeekExerciseResponseDto;
import org.ssafy.d210.walk.entity.Exercise;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    // DB에 하루 치 운동이 저장된 마지막 날짜
    @Query("select max(e.exerciseDay) from Exercise e")
    LocalDate findLastDate();

    // 이번주(어제까지) 운동 데이터(걸음 수) 조회
    @Query("SELECT e FROM Exercise e WHERE e.exerciseDay >= :startOfWeek AND e.exerciseDay < :today AND e.member.id = :myId")
    List<Exercise> findExercisesFromStartOfWeekToYesterday(LocalDate startOfWeek, LocalDate today, Long myId);

    // 스트릭 랭킹 조회 기능 중 1. 페이지네이션
    @Query("select distinct new org.ssafy.d210.walk.dto.response.StreakRankingResopnseDto(m.id, m.nickname, m.profileUrl, e.streak)" +
            " from Exercise e join Members m on e.member.id = m.id" +
            " where (m.id = :myId or m.id in (select f.receiverId.id from FriendList f where f.senderId.id = :myId))" +
            " and e.exerciseDay = :yesterday" +
            " order by e.streak desc")
    Slice<StreakRankingResopnseDto> findRankingByPage(Long myId, Pageable pageable, LocalDate yesterday);

}
