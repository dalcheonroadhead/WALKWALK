package org.ssafy.d210.members.dto.response;

import org.ssafy.d210.members.entity.BadgeType;

public interface MemberBadgeInfo {

    Long getMemberId();
    Long getBadgeId();
    BadgeType getType();
    String getIcon();
    String getName();
    String getCriteria();
    String getExplains();

}
