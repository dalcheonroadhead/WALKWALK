package org.ssafy.d210.wallets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.d210.wallets.entity.MemberAccount;

public interface MemberAccountRepository extends JpaRepository<MemberAccount, Long> {

}
