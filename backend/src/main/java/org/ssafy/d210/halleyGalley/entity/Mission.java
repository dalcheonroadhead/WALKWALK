package org.ssafy.d210.halleyGalley.entity;

import jakarta.persistence.*;
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
    private Integer exerciseMinute;

    @Column(name = "period", nullable = false)
    private Integer period;
}
