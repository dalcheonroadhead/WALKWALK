package org.ssafy.d210.wallets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.wallets.entity.WalletHistory;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletHistoryRepository extends JpaRepository<WalletHistory, Long> {

    List<WalletHistory> findAllByMember(Members member);

    Optional<WalletHistory> findWalletHistoryById(Long id);
}
