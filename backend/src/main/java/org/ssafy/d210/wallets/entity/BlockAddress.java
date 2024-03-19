package org.ssafy.d210.wallets.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ssafy.d210.members.entity.Members;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "block_address")
public class BlockAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1)
    @Column(name = "block_address_id")
    private Long id;

    @Column(name = "block_address", columnDefinition = "LONGTEXT", nullable = false)
    private String blockAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "block_type")
    private BlockType blockType;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Members members;
}