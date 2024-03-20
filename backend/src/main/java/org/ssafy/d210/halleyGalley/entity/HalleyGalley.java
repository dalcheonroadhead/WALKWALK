package org.ssafy.d210.halleyGalley.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.ssafy.d210._common.entity.OnlyCreatedTime;
import org.ssafy.d210.members.entity.Members;

@Entity
@Getter
@NoArgsConstructor
public class HalleyGalley extends OnlyCreatedTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1)
    @Column(name = "halley_galley_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "galley_id", nullable = false)
    private Members galleyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "halley_id", nullable = false)
    private Members halleyId;

    @OneToOne
    @JoinColumn(name = "mission_id")
    private Mission missionId;

    @Column(name = "reward")
    @ColumnDefault("0")
    private Integer reward;

    @Column(name = "dayoff")
    @ColumnDefault("0")
    private Integer dayoff;
}
