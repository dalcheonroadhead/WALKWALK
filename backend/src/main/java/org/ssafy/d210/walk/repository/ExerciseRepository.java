package org.ssafy.d210.walk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.d210.walk.entity.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
