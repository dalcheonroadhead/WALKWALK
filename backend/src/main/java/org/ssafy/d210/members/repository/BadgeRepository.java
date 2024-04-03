package org.ssafy.d210.members.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.members.dto.response.MemberBadgeInfo;
import org.ssafy.d210.members.entity.Badge;
import org.ssafy.d210.members.entity.MemberBadge;

import java.util.List;
import java.util.Optional;

@Repository
public interface  BadgeRepository extends JpaRepository<Badge,Long> {


    @Query(value = "select b.badge_id, b.icon, b.name, b.explains, b.type, b.criteria from badge b, members m, member_badge mb " +
            "where m.member_id = :memberId AND m.member_id = mb.member_id  AND  b.badge_id = mb.badge_id;", nativeQuery = true)
    public List<Badge> getMembersBadge(@Param("memberId") long member_id);

}
