package org.ssafy.d210.members.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.ssafy.d210.members.entity.BadgeType;

@Getter

public class MemberBadgeInfo {

    private String icon;

    private String name;


    @Builder
    private MemberBadgeInfo (String icon, String name) {
        this.icon = icon;
        this.name = name;
    }


    public static MemberBadgeInfo of (String icon, String name) {
        return  builder()
                .icon(icon)
                .name(name)
                .build();
    }

}
