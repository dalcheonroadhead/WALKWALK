package org.ssafy.d210.items.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ssafy.d210._common.entity.OnlyCreatedTime;
import org.ssafy.d210.members.entity.Members;

@Entity
@Getter
@NoArgsConstructor
public class MemberItemHistory extends OnlyCreatedTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_item_history_id")
    private Long id;

    @Column(nullable = false)
    private Integer cnt;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_type")
    private ItemsType itemType;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Members member;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Items item;

    @Builder
    private MemberItemHistory(Integer cnt, ItemsType itemType, Members member, Items item) {
        this.cnt = cnt;
        this.itemType = itemType;
        this.member = member;
        this.item = item;
    }

    public static MemberItemHistory of(Integer cnt, ItemsType itemType, Members member, Items item) {
        return builder()
                .cnt(cnt)
                .itemType(itemType)
                .member(member)
                .item(item)
                .build();
    }
}
