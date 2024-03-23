package org.ssafy.d210.members.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "badge")
@NoArgsConstructor
@Getter
@Setter
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id")
    private Long badgeId;

    @Column(nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private BadgeType type;

    @Column (length = 500)
    private String icon;

    @Column
    private String name;

    @Column (length = 500)
    private String explains;

    @Column
    private Long criteria;

    @OneToMany(mappedBy = "badge", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MemberBadge> memberBadges = new ArrayList<>();
}
