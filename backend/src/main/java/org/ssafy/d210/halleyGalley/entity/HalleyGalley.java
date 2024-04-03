package org.ssafy.d210.halleyGalley.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.ssafy.d210._common.entity.OnlyCreatedTime;
import org.ssafy.d210.members.entity.Members;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class HalleyGalley extends OnlyCreatedTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1)
    @Column(name = "halley_galley_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "galley_id", nullable = false)
    private Members galleyId;

    @ManyToOne
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

    @Column(name = "is_accepted")
    @ColumnDefault("false")
    private Boolean isAccepted;

    @Column(name = "get_reward_at", nullable = true)
    private LocalDate getRewardAt;

    @Builder
    private HalleyGalley(Members galleyId, Members halleyId, Mission missionId, Integer reward, Integer dayoff, Boolean isAccepted, LocalDate getRewardAt){

        this.galleyId = galleyId;
        this.halleyId = halleyId;
        this.missionId = missionId;
        this.reward = reward;
        this.dayoff = dayoff;
        this.isAccepted = isAccepted;
        this.getRewardAt = getRewardAt;
    }

    public static HalleyGalley of(Members galleyId, Members halleyId, Mission missionId, Integer reward, Integer dayoff, Boolean isAccepted, LocalDate getRewardAt){

        return builder()
                .galleyId(galleyId)
                .halleyId(halleyId)
                .missionId(missionId)
                .reward(reward)
                .dayoff(dayoff)
                .isAccepted(isAccepted)
                .getRewardAt(LocalDate.now())
                .build();
    }

    public void updateIsAccepted(Boolean isAccepted){
        this.isAccepted = isAccepted;
    }

    public void updateMissionId(Mission missionId){
        this.missionId = missionId;
    }

    public void updateReward(Integer reward){
        this.reward = reward;
    }

    public void updateDayoff(Integer dayoff){
        this.dayoff = dayoff;
    }

    public void updateGetRewardAt(LocalDate getRewardAt) { this.getRewardAt = getRewardAt; }
}
