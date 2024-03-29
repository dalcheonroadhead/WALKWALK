package org.ssafy.d210.wallets._blockchain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.wallets._blockchain.entity.ServerBlockchainAccount;

import java.util.Optional;

@Repository
public interface BlockchainRepository extends JpaRepository<ServerBlockchainAccount, Long> {
    Optional<ServerBlockchainAccount> findServerBlockchainAccountByChainId(String chainId);
}
