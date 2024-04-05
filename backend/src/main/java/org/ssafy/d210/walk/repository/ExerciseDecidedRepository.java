package org.ssafy.d210.walk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.walk.entity.ExerciseDecided;
import org.ssafy.d210.walk.entity.ReportPrevious;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseDecidedRepository extends JpaRepository<ExerciseDecided, Long> {

    Optional<ExerciseDecided> findExerciseDecidedByMemberAndExerciseEndIsNull(Members member);

    @Query("SELECT new org.ssafy.d210.walk.entity.ReportPrevious(" +
            "AVG(e.heartRate), " +
            "SUM(e.exerciseDistance), " +
            "SUM(e.steps), " +
            "SUM(e.exerciseMinute), " +
            "SUM(e.calorie)) " +
            "FROM ExerciseDecided e " +
            "WHERE e.member = :member AND e.exerciseDay BETWEEN :startDate AND :endDate " +
            "GROUP BY e.exerciseDay")
    List<ReportPrevious> findReportByMemberAndDateRange(@Param("member") Members member, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<ExerciseDecided> findExerciseDecidedsByMemberAndExerciseDayBetween(Members member, LocalDate startDate, LocalDate endDate);

}
