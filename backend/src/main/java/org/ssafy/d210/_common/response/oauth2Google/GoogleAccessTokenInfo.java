package org.ssafy.d210._common.response.oauth2Google;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * (〜￣△￣)〜    AccessToken DTO -> 전체 요청에서 잘라 씀       〜(￣△￣〜)
 * */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GoogleAccessTokenInfo {

    private int issued_at;
    private String access_token;
    private int expires_in;
}
