package org.ssafy.d210.walk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.walk.dto.response.ReportResponseDto;
import org.ssafy.d210.walk.entity.ExerciseDecided;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseDecidedRepository extends JpaRepository<ExerciseDecided, Long> {

    Optional<ExerciseDecided> findExerciseDecidedByMemberAndExerciseEndIsNull(Members member);

    List<ExerciseDecided> findExerciseDecidedsByMemberAndExerciseDayIsAfterOrEqualAndExerciseDayIsBeforeOrEqual(Members member, LocalDate startDate, LocalDate endDate);

    @Query("SELECT new org.ssafy.d210.walk.dto.response.ReportResponseDto(" +
            "e.member, " +
            "FUNCTION('group_concat', FUNCTION('classify_time_of_day', e.exerciseStart)), " + // 주의: 이 부분은 가상의 함수입니다.
            "AVG(e.heartRate), " +
            "AVG(e.exerciseDistance), " +
            "COUNT(DISTINCT e.exerciseDay), " +
            "AVG(e.steps), " +
            "AVG(e.exerciseMinute), " +
            "AVG(e.calorie)) " +
            "FROM ExerciseDecided e " +
            "WHERE e.member = :member AND e.exerciseDay BETWEEN :startDate AND :endDate " +
            "GROUP BY e.member, e.exerciseDay")
    List<ReportResponseDto> findReportByMemberAndDateRange(@Param("member") Members member, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
