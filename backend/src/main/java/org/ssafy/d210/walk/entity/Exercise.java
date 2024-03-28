package org.ssafy.d210.walk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.ssafy.d210.members.entity.Members;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Exercise {

    private LocalDate today = LocalDate.now();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1)
    @Column(name = "exercise_id")
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

    @Column(name = "is_achieved")
    @ColumnDefault("false")
    private Boolean isAchieved;

    private Double calorie;
    private Long streak;

}