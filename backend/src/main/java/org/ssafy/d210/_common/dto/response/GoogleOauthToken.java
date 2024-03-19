package org.ssafy.d210._common.dto.response;

import lombok.*;

@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만듦
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성
@Data // data 어노테이션에서 getter와 setter 부분이 작동하지 않아서 아래 추가함.
@Getter
@Setter
public class GoogleOauthToken {

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
