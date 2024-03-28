package org.ssafy.d210.items.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.items.entity.MemberItemHistory;

@Repository
public interface MemberItemHistoryRepository extends JpaRepository<MemberItemHistory, Long> {

}
