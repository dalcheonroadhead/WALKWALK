package org.ssafy.d210.members.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.ssafy.d210.members.entity.Members;

@Getter
@Setter
public class ReqMyPageDetailInfo {

    @NotNull
    @Size(min = 1, max = 500)
    String profileUrl;

    @NotNull
    @Size(min = 1, max = 12)
    String nickname;


    @NotNull
    @Range(min = 1940, max = 2024)
    Long birthYear;

    @NotNull
    @Range(min = 1, max = 300)
    double height;

    @NotNull
    double weight;

    @NotNull
    String gender;

    @NotNull
    String location;

    @NotNull
    double longitude;

    @NotNull
    double latitude;

    @NotNull
    String comment;


    @NotNull
    @Pattern(
            regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$",
            message = "10 ~ 11 자리의 숫자만 입력 가능합니다."
    )
    String phoneNumber;

    @Builder
    public ReqMyPageDetailInfo(String profileUrl, String nickname, Long birthYear, double height, double weight, String gender, String location, double longitude, double latitude, String comment, Long dailyCriteria, String phoneNumber) {
        this.profileUrl = profileUrl;
        this.nickname = nickname;
        this.birthYear = birthYear;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        this.comment = comment;
        this.phoneNumber = phoneNumber;
    }

    public Members ToEntity(Members me){
        me.setProfileUrl(this.profileUrl);
        me.setNickname(this.nickname);
        me.setBirthYear(this.birthYear);
        me.setHeight(this.height);
        me.setWeight(this.weight);
        me.setGender(this.gender.equals("MALE")? Members.GenderType.MALE : Members.GenderType.FEMALE);
        me.setLocation(this.location);
        me.setLongitude(this.longitude);
        me.setLatitude(this.latitude);
        me.setComment(this.comment);
        me.setPhoneNumber(this.phoneNumber);

        return me;
    }
}
