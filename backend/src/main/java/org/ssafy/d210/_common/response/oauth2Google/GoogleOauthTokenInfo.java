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

    private int issued_at;
    private String scope;
    private String application_name;
    private int refresh_token_issued_at;
    private String status;
    private String refresh_token_status;
    private String api_product_list;
    private int expires_in;
    private String developer_email;
    private String organization_id;
    private String token_type;
    private String refresh_token;
    private String client_id;
    private String access_token;
    private String organization_name;
    private int refresh_token_expires_in;
    private int refresh_count;
}
