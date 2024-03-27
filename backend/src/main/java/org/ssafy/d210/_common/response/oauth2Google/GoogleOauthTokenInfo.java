package org.ssafy.d210._common.response.oauth2Google;

import lombok.*;

/*
* (〜￣△￣)〜    Token 발급 요청에 대한 답변       〜(￣△￣〜)
* */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GoogleOauthTokenInfo {

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String scope;
    private String token_type;
}
