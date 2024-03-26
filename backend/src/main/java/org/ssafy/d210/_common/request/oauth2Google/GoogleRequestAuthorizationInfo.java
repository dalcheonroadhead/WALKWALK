package org.ssafy.d210._common.request.oauth2Google;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/*
 * (〜￣△￣)〜   AccessToken 요청 DTO       〜(￣△￣〜)
 * */

@Getter
@Setter

public class GoogleRequestAuthorizationInfo {

    private String code;
    @Value("${google.client-id}")
    private static String client_id;
    @Value("${google.client-secret}")
    private static String client_secret;
    private String redirect_uri;
    private final String grant_type = "authorization_code";

}
