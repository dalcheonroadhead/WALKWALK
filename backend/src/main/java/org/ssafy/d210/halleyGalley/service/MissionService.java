package org.ssafy.d210.halleyGalley.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ssafy.d210._common.exception.CustomException;
import org.ssafy.d210._common.exception.ErrorType;
import org.ssafy.d210.halleyGalley.dto.request.PostMissionRequest;
import org.ssafy.d210.halleyGalley.entity.HalleyGalley;
import org.ssafy.d210.halleyGalley.entity.Mission;
import org.ssafy.d210.halleyGalley.repository.HalleyGalleyRepository;
import org.ssafy.d210.halleyGalley.repository.MissionRepository;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.repository.MembersRepository;
import org.ssafy.d210.wallets.entity.MemberAccount;
import org.ssafy.d210.wallets.repository.MemberAccountRepository;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final HalleyGalleyRepository halleyGalleyRepository;
    private final MemberAccountRepository memberAccountRepository;
    private final MembersRepository membersRepository;
    @Transactional
    public String postMission(Members member, PostMissionRequest postMissionRequest){
        Long memberId = postMissionRequest.getMemberId();
        Integer reward = postMissionRequest.getQuestMoney();
        Long exerciseMinute = postMissionRequest.getExerciseMinute();
        Integer period = postMissionRequest.getPeriod();

        MemberAccount memberAccount = memberAccountRepository.findMemberAccountById(member.getMemberAccountId().getId())
                .orElseThrow(()->new CustomException(ErrorType.NOT_FOUND_MEMBER_ACCOUNT));

        if(memberAccount.getMoney() < reward){
            throw new CustomException(ErrorType.NOT_ENOUGH_MONEY);
        }

        Members halley = membersRepository.findById(memberId)
                .orElseThrow(()->new CustomException(ErrorType.NOT_FOUND_MEMBER));

        HalleyGalley halleyGalley = halleyGalleyRepository.findHalleyGalleyByGalleyIdAndHalleyId(member, halley)
                .orElseThrow(()->new CustomException(ErrorType.NOT_FOUND_HALLEY_GALLEY));

        Mission mission = missionRepository.findById(halleyGalley.getMissionId().getId())
                .orElseThrow(()->new CustomException(ErrorType.NOT_FOUND_MISSION));

        if(mission.getCreatedAt().getMonthValue()+1 < LocalDate.now().getMonthValue()){
            Mission newMission = Mission
                    .builder()
                    .exerciseMinute(exerciseMinute)
                    .period(period)
                    .build();

            // 미션 저장
            missionRepository.save(newMission);
            // 할리갈리 보상 세팅
            HalleyGalley halleyGalley1 = halleyGalleyRepository.findHalleyGalleyByGalleyIdAndHalleyId(member, halley)
                    .orElseThrow(()->new CustomException(ErrorType.NOT_FOUND_HALLEY_GALLEY));

            halleyGalley1.updateReward(reward);
            halleyGalley1.updateMissionId(mission);
            halleyGalleyRepository.save(halleyGalley1);
        }
        else{
            throw new CustomException(ErrorType.CANT_ADD_MISSION);
        }

        return "";
    }
}
