package org.ssafy.d210.walk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.ssafy.d210.members.entity.Members;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ExerciseDecided {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1)
    @Column(name = "exercise_decided_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Members member;

    @ColumnDefault("0")
    private Long steps;

    @Column(name = "exercise_minute")
    @ColumnDefault("0")
    private Long exerciseMinute;

    @Column(name = "heart_rate")
    @ColumnDefault("0")
    private Double heartRate;

    @Column(name = "exercise_distance")
    @ColumnDefault("0")
    private Double exerciseDistance;

    @Column(name = "exercise_day")
    private LocalDate exerciseDay = LocalDate.now();

    @Column(name = "exercise_start")
    private LocalDateTime exerciseStart = LocalDateTime.now();

    @Column(name = "exercise_end")
    private LocalDateTime exerciseEnd;

    private Double calorie;
}
