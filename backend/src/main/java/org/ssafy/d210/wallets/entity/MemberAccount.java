package org.ssafy.d210.wallets.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.ssafy.d210._common.entity.BaseTime;
import org.ssafy.d210.members.entity.Members;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MemberAccount extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1)
    @Column(name = "member_account_id")
    private Long id;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String eoa;

    @Column(name = "public_key", columnDefinition = "LONGTEXT", nullable = false)
    private String publicKey;

    @Column
    @ColumnDefault("0")
    private Integer egg;

    @Column
    @ColumnDefault("0")
    private Integer money;

    @OneToMany(mappedBy = "memberAccountId", cascade = CascadeType.PERSIST)
    private List<Members> members = new ArrayList<>();
}
