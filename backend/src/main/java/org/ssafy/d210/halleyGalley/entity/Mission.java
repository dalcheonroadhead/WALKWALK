package org.ssafy.d210.halleyGalley.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ssafy.d210._common.entity.OnlyCreatedTime;

@Entity
@Getter
@NoArgsConstructor
public class Mission extends OnlyCreatedTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1)
    @Column(name = "mission_id")
    private Long id;

    @Column(name = "exercise_minute", nullable = false)
    private Long exerciseMinute;

    @Column(name = "period", nullable = false)
    private Integer period;

    @Builder
    private Mission(Long exerciseMinute, Integer period){

        this.exerciseMinute = exerciseMinute;
        this.period = period;
    }

    public static Mission of(Long exerciseMinute, Integer period){

        return builder()
                .exerciseMinute(exerciseMinute)
                .period(period)
                .build();
    }
}
