package org.ssafy.d210.walk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.walk.entity.ExerciseDecided;

import java.util.Optional;

@Repository
public interface ExerciseDecidedRepository extends JpaRepository<ExerciseDecided, Long> {

    Optional<ExerciseDecided> findExerciseDecidedByMemberAndAndExerciseEndIsNull(Members member);
}
