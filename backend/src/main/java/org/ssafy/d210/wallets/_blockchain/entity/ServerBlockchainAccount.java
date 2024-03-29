package org.ssafy.d210.wallets._blockchain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ServerBlockchainAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "server_account_id")
    private Long id;

    @Column(nullable = false)
    private String chainId;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String eoa;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String keyId;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String publicKey;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String krn;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;
}
