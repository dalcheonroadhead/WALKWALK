package org.ssafy.d210._common.response.oauth2Google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/*
 * (〜￣△￣)〜    Profile 요청(ResourceAPI)에 대한 답변       〜(￣△￣〜)
 * */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleProfileInfo {

    private String id;
    private String name;
    private String email;
    private String picture;

    @Override
    public String toString() {
        return "GoogleProfileInfo{" +
                "id='" + id + '\'' +
                ", nickname='" + name + '\'' +
                ", email='" + email + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
