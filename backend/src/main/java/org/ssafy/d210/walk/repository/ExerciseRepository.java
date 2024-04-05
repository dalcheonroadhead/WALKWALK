package org.ssafy.d210.walk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.walk.entity.Exercise;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    // DB에 하루 치 운동이 저장된 마지막 날짜
    @Query("select max(e.exerciseDay) from Exercise e")
    LocalDate findLastDate();

    // 이번주(어제까지) 운동 데이터(걸음 수) 조회
    @Query("SELECT e FROM Exercise e WHERE e.exerciseDay >= :startOfWeek AND e.exerciseDay < :today AND e.member.id = :myId")
    List<Exercise> findExercisesFromStartOfWeekToYesterday(LocalDate startOfWeek, LocalDate today, Long myId);

    List<Exercise> findExercisesByMemberAndExerciseDayBetween(Members member, LocalDate startDate, LocalDate endDate);

    Optional<Exercise> findExerciseByMemberAndExerciseDay(Members member, LocalDate exerciseDay);

    List<Exercise> findExercisesByMemberAndExerciseDay(Members member, LocalDate exerciseDay);


}
