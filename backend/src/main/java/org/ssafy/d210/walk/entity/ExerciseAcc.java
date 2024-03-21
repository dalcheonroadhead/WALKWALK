package org.ssafy.d210.walk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.ssafy.d210.members.entity.Members;

@Entity
@Getter
@NoArgsConstructor
public class ExerciseAcc { // 운동 누적
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1)
    @Column(name = "exercise_acc_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Members member;

    @ColumnDefault("0")
    private Long steps;

    @Column(name = "exercise_minute")
    @ColumnDefault("0")
    private Long exerciseMinute;

    @Column(name = "exercise_distance")
    @ColumnDefault("0")
    private Long exerciseDistance;

    private Long calorie;
}
