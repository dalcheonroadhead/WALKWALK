package org.ssafy.d210.wallets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.d210.wallets.entity.BlockAddress;

public interface BlockAddressRepository extends JpaRepository<BlockAddress, Long> {
}
