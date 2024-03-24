package org.ssafy.d210.walk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.walk.entity.ExerciseCriteria;

import java.util.Optional;

public interface ExerciseCriteriaRepository extends JpaRepository<ExerciseCriteria, Long> {

    ExerciseCriteria findExerciseCriteriaByMemberAndIsCustomIsFalse(Members member);
    Optional<ExerciseCriteria> findExerciseCriteriaByMemberAndIsCustomIsTrue(Members member);
}
