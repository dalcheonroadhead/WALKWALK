package org.ssafy.d210.members.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.members.dto.response.MemberBadgeInfo;
import org.ssafy.d210.members.entity.MemberBadge;

import java.util.List;

@Repository
public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Long> {

//    public List<MemberBadgeInfo> findAllByMember_Id(long memeber_id);
}
