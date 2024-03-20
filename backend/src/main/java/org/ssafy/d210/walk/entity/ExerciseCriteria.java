package org.ssafy.d210.walk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor
public class ExerciseCriteria { // 운동 기준
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1)
    @Column(name = "criteria_id")
    private Long id;

    @Column(name = "age_group")
    private Long ageGroup;

    @Column(name = "steps")
    @ColumnDefault("0")
    private Long steps;

    @Column(name = "exercise_minute")
    @ColumnDefault("0")
    private Long exerciseMinute;

    @Column(name = "heart_rate")
    @ColumnDefault("0")
    private Long heartRate;

    @Column(name = "exercise_distance")
    @ColumnDefault("0")
    private Long exerciseDistance;

    @Column(name = "calorie")
    private Long calorie;
}