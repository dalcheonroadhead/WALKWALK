package org.ssafy.d210.walk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Query("select new org.ssafy.d210.walk.dto.response.StreakRankingResopnseDto(RANK() OVER(ORDER BY e.streak DESC), e.member.id, e.member.nickname, e.member.profileUrl, e.streak)" +
            " from Exercise e where e.member.id = :myId or e.member.id in (select f.receiverId from FriendList f where f.senderId = :myId) order by e.streak desc")
    Page<StreakRankingResopnseDto> findRankingByPage(Long myId, Pageable pageable);


    @Query(value = "SELECT e.member_id, MAX(e.streak) AS max_streak FROM Exercise e GROUP BY e.member_id ORDER BY max_streak DESC",
            countQuery = "SELECT COUNT(DISTINCT e.member_id) FROM Exercise e",
            nativeQuery = true)
    Page<Object[]> findRankedMembers(Pageable pageable);
}
