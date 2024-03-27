package org.ssafy.d210.wallets._payment.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ssafy.d210.members.entity.Members;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @Column(name = "cid")
    private String cid;

    @Column(name = "partner_order_id")
    private String partner_order_id;

    @Column(name = "partner_user_id")
    private String partner_user_id;

    @Column(name = "total_amount")
    private Integer total_amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Members member;
}