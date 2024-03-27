package org.ssafy.d210.wallets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.wallets.entity.BlockAddress;

@Repository
public interface BlockAddressRepository extends JpaRepository<BlockAddress, Long> {

}
