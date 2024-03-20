package org.ssafy.d210.members.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AdditionalInfo {
    String nickname;
    String location;
    String longitude;
    String latitude;
    String birthYear;
    String gender;
    String height;
    String weight;
    String BOA;
    String publicKey;

    @Override
    public String toString() {
        return "AdditionalInfo{" +
                "nickname='" + nickname + '\'' +
                ", location='" + location + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", gender='" + gender + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", BOA='" + BOA + '\'' +
                ", publicKey='" + publicKey + '\'' +
                '}';
    }
}
