package org.ssafy.d210.friends.dto;


public interface MemberListDto {

    Long getMemberId();
    String getProfileUrl();
    String getNickname();
    String getComment();
    Boolean getIsFriend();
    Boolean getIsAccepted();
}
