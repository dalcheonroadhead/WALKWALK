package org.ssafy.d210.members.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String type;

    @Column (length = 500)
    private String icon;

    @Column
    private String name;

    @Column (length = 500)
    private String explains;

    @Column
    private Long criteria;
}
