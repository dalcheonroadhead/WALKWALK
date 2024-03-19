package org.ssafy.d210._common.dto.request;

import lombok.*;

@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만듦
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성
@Data // data 어노테이션에서 getter와 setter 부분이 작동하지 않아서 아래 추가함.
@Getter
@Setter
public class GoogleRequestAuthorization {

    private String code;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private final String grant_type = "authorization_code";

}
