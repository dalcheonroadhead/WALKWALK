package org.ssafy.d210.members.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageInfo {

    String profileUrl;
    String nickname;
    String comment;


    @Builder
    private MyPageInfo(String profileUrl, String nickname, String comment){
        this.profileUrl = profileUrl;
        this.nickname = nickname;
        this.comment = comment;
    }

    public static MyPageInfo of (String profileUrl, String nickname, String comment){
        return builder()
                .profileUrl(profileUrl)
                .nickname(nickname)
                .comment(comment)
                .build();
    }
}
