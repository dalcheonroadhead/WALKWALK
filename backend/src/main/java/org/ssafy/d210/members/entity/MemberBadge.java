package org.ssafy.d210.members.entity;

/*
 *   교차 테이블
 *
 * */

import jakarta.persistence.*;
import org.ssafy.d210._common.entity.CreatedAndUpdatedTime;

@Entity
@Table(name = "member_badge")
public class MemberBadge extends CreatedAndUpdatedTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_badge_id")
    private Long memberBadgeId;

//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "badge_id", referencedColumnName = "badge_id")
//    private Badge badge;
//
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name ="member_id", referencedColumnName = "id")
//    private Members members;


}
