package org.ssafy.d210.members.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.wallets.entity.MemberAccount;

@RequiredArgsConstructor
@Getter
@Setter
public class AdditionalInfo {

    @NotNull
    @Size(min = 1, max = 35)
    String nickname;

    @NotNull
    String gender;

    @NotNull
    Long height;

    @NotNull
    Long weight;

    @NotNull
    @Size(min = 1, max = 500)
    String location;

    @NotNull
    Double longitude;

    @NotNull
    Double latitude;


    @NotNull
    Long birthYear;

    @NotNull
    @Size(min = 1)
    String comment;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 50)
    String eoa;

    @NotNull
    @Pattern(
            regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$",
            message = "10 ~ 11 자리의 숫자만 입력 가능합니다."
            )
    String phoneNumber;

    @NotNull
    String publicKey;

    public Members toEntity(Members member, MemberAccount memberAccount) {




        member.setNew(false);
        member.setLocation(this.location);
        member.setComment(this.comment);
        member.setLongitude(this.longitude);
        member.setLatitude(this.latitude);
        member.setGender(this.gender.equals("MALE")? Members.GenderType.MALE : Members.GenderType.FEMALE);
        member.setBirthYear(this.birthYear);
        member.setHeight(this.height);
        member.setWeight(this.weight);
        member.setStreakColor("gray");
        member.setPhoneNumber(this.phoneNumber);
        member.setMemberAccountId(memberAccount);
        member.setMainBadge("https://d210.s3.ap-northeast-2.amazonaws.com/test_badge.png");


        return member;
    }

    @Override
    public String toString() {
        return "AdditionalInfo{" +
                "nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", location='" + location + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", birthYear=" + birthYear +
                ", comment='" + comment + '\'' +
                ", eoa='" + eoa + '\'' +
                ", phone_number='" + phoneNumber + '\'' +
                '}';
    }
}
