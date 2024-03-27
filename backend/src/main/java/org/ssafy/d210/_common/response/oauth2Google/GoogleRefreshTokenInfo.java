package org.ssafy.d210._common.response.oauth2Google;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * (〜￣△￣)〜    Refresh Token Info      〜(￣△￣〜)
 * */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GoogleRefreshTokenInfo {

    private String refresh_token;
    private String token_type;
}
