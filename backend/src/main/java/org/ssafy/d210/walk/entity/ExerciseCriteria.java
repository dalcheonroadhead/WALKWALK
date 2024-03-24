package org.ssafy.d210.walk.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.ssafy.d210.members.entity.Members;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class ExerciseCriteria { // 운동 기준
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1)
    @Column(name = "criteria_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Members member;

    @Column(name = "age_group")
    private Integer ageGroup;

    @Column(name = "is_custom")
    @ColumnDefault("false")
    private Boolean isCustom;

    @Setter
    @ColumnDefault("0")
    private Long steps;

    @Setter
    @Column(name = "exercise_minute")
    @ColumnDefault("0")
    private Long exerciseMinute;

    @Setter
    @Column(name = "heart_rate")
    @ColumnDefault("0")
    private Long heartRate;

    @Column(name = "exercise_distance")
    @ColumnDefault("0")
    private Long exerciseDistance;

    @ColumnDefault("0")
    private Long calorie;

    @Builder
    public ExerciseCriteria(Members member, Boolean isCustom, Long steps, Long exerciseMinute, Long heartRate) {
        this.member = member;
        this.isCustom = isCustom;
        this.steps = steps;
        this.exerciseMinute = exerciseMinute;
        this.heartRate = heartRate;
    }

    public static ExerciseCriteria createDefaultCriteria(Members member) {
        int age = (int) (LocalDate.now().getYear() - member.getBirthYear());
        ExerciseCriteriaBuilder builder = determineDefaultCriteriaByAge(age).member(member);
        return builder.build();
    }

    private static ExerciseCriteriaBuilder determineDefaultCriteriaByAge(int age) {
        if (age < 18) {
            return ExerciseCriteria.builder()
                    .isCustom(false)
                    .exerciseMinute(60L)
                    .steps(10000L)
                    .heartRate(130L);
        } else if (age < 65) {
            return ExerciseCriteria.builder()
                    .isCustom(false)
                    .exerciseMinute(45L)
                    .steps(8000L)
                    .heartRate(110L);
        } else {
            return ExerciseCriteria.builder()
                    .isCustom(false)
                    .exerciseMinute(25L)
                    .steps(6000L)
                    .heartRate(90L);
        }
    }

    //    @Builder
//    public ExerciseCriteria(Boolean isCustom, Long steps, Long exerciseMinute, Long heartRate, Long exerciseDistance, Long calorie) {
//        this.isCustom = isCustom;
//        this.steps = steps;
//        this.exerciseMinute = exerciseMinute;
//        this.heartRate = heartRate;
//        this.exerciseDistance = exerciseDistance;
//        this.calorie = calorie;
//    }
}