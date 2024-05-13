package org.ssafy.d210.halleyGalley.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ssafy.d210._common.exception.CustomException;
import org.ssafy.d210._common.exception.ErrorType;
import org.ssafy.d210.halleyGalley.dto.HalleyDto;
import org.ssafy.d210.halleyGalley.dto.request.PostGalleyRequest;
import org.ssafy.d210.halleyGalley.dto.request.PutDayoffRequest;
import org.ssafy.d210.halleyGalley.dto.request.PutGalleyResponseRequest;
import org.ssafy.d210.halleyGalley.dto.request.PutMissionRequest;
import org.ssafy.d210.halleyGalley.dto.response.*;
import org.ssafy.d210.halleyGalley.entity.HalleyGalley;
import org.ssafy.d210.halleyGalley.repository.HalleyGalleyRepository;
import org.ssafy.d210.halleyGalley.repository.MissionRepository;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.repository.MembersRepository;
import org.ssafy.d210.notifications.entity.NotiType;
import org.ssafy.d210.notifications.entity.Notification;
import org.ssafy.d210.notifications.service.NotificationService;
import org.ssafy.d210.wallets.dto.request.PutMoneyRequest;
import org.ssafy.d210.wallets.entity.MemberAccount;
import org.ssafy.d210.wallets.repository.MemberAccountRepository;
import org.ssafy.d210.wallets.service.WalletsService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HalleyGalleyService {
    private final HalleyGalleyRepository halleyGalleyRepository;
    private final MembersRepository membersRepository;
    private final NotificationService notificationService;
    private final MemberAccountRepository memberAccountRepository;

    @Transactional
    public String postGalleyRequest(Members member, PostGalleyRequest request){
        Long galleyId = request.getMemberId();
        Members galley = membersRepository.findById(galleyId)
                .orElseThrow(() -> new CustomException(ErrorType.NOT_FOUND_MEMBER));

        if(!halleyGalleyRepository.existsHalleyGalleyByHalleyIdAndGalleyId(member, galley)) {
            halleyGalleyRepository.save(HalleyGalley.builder()
                    .halleyId(member)
                    .galleyId(galley)
                    .reward(0)
                    .missionId(null)
                    .dayoff(0)
                    .isAccepted(false)
                    .getRewardAt(LocalDate.now().minusDays(1))
                    .build());
            Notification notification = Notification.builder()
                    .senderId(member)
                    .receiverId(galley)
                    .isChecked(false)
                    .notiType(NotiType.HALLEY_GALLEY)
                    .notiContent(member.getNickname() + "님으로 부터 할리 수락 요청이 도착했습니다")
                    .build();
            notificationService.insertNotification(notification);
            notificationService.notify(galleyId, notification);
        }
        else{
            throw new CustomException(ErrorType.ALREADY_SEND_REQUEST);
        }
        return "";
    }

    @Transactional
    public PutGalleyResponseResponse putGalleyResponse(Members member, PutGalleyResponseRequest request){
        Long galleyId = request.getMemberId();
        Boolean isAccept = request.getIsAccept();

        Members galley = membersRepository.findById(galleyId)
                .orElseThrow(()->new CustomException(ErrorType.NOT_FOUND_MEMBER));

        HalleyGalley halleyGalley = halleyGalleyRepository.findHalleyGalleyByGalleyIdAndHalleyId(member, galley)
                .orElseThrow(()->new CustomException(ErrorType.NOT_FOUND_HALLEY_GALLEY));

        halleyGalley.updateIsAccepted(isAccept);
        if(!isAccept){
            halleyGalleyRepository.delete(halleyGalley);
        }
        else{
            halleyGalleyRepository.save(halleyGalley);
        }

        return PutGalleyResponseResponse.of(isAccept);
    }

    public List<GetGalleyListResponse> getGalleyList(Members member){
        List<HalleyGalley> galleyList = halleyGalleyRepository.findByHalleyId(member)
                .orElseThrow(()->new CustomException(ErrorType.NOT_FOUND_GALLEY));
        return galleyList.stream().map(GetGalleyListResponse::from).collect(Collectors.toList());
    }

    public GetHalleyListResponse getHalleyList(Members member){
        List<HalleyGalley> halleyList = halleyGalleyRepository.findByGalleyId(member)
                .orElseThrow(()->new CustomException(ErrorType.NOT_FOUND_HALLEY));
        List<HalleyDto> halleyInfoList = halleyList.stream().map(HalleyDto::from).toList();

        return GetHalleyListResponse.from(member, halleyInfoList);
    }

    public GetHalleyRequestListResponse getHalleyRequestList(Members member){
        List<HalleyGalley> halleyRequestList = halleyGalleyRepository.findByHalleyIdAndIsAcceptedIsFalse(member)
                .orElseThrow(()->new CustomException(ErrorType.NOT_FOUND_HALLEY_REQUEST_LIST));
        List<HalleyDto> halleyInfoList = halleyRequestList.stream().map(HalleyDto::from).toList();

        return GetHalleyRequestListResponse.of(halleyInfoList);
    }

    public GetHalleyResponse getHalley(Members member, Long halleyId){
        Members members = membersRepository.findById(halleyId).orElseThrow(()->new CustomException(ErrorType.NOT_FOUND_MEMBER));
        HalleyGalley halley = halleyGalleyRepository.findHalleyGalleyByGalleyIdAndHalleyId(member, members).orElseThrow(()->new CustomException(ErrorType.NOT_FOUND_HALLEY));

        return GetHalleyResponse.from(halley, halley.getMissionId());
    }

    public GetGalleyResponse getGalley(Members member, Long galleyId){
        Members members = membersRepository.findById(galleyId).orElseThrow(()->new CustomException(ErrorType.NOT_FOUND_MEMBER));
        HalleyGalley galley = halleyGalleyRepository.findHalleyGalleyByGalleyIdAndHalleyId(members, member).orElseThrow(()->new CustomException(ErrorType.NOT_FOUND_GALLEY));

        return GetGalleyResponse.from(galley, galley.getMissionId());
    }

    @Transactional
    public String putMission(Members member, PutMissionRequest request){
        List<Long> halleyIdList = request.getMemberIdList();

        if(!halleyIdList.isEmpty()) {
            int totalMoney = 0;

            for (Long halleyId : halleyIdList) {
                Members halley = membersRepository.findById(halleyId).orElseThrow(() -> new CustomException(ErrorType.NOT_FOUND_MEMBER));
                HalleyGalley halleyGalley = halleyGalleyRepository.findHalleyGalleyByGalleyIdAndHalleyId(member, halley).orElseThrow(() -> new CustomException(ErrorType.NOT_FOUND_HALLEY));
                halleyGalley.updateGetRewardAt(LocalDate.now());
                Integer reward = halleyGalley.getReward();
                totalMoney += reward;
                halleyGalleyRepository.save(halleyGalley);
            }
            MemberAccount memberAccount = memberAccountRepository.findMemberAccountById(member.getMemberAccountId().getId())
                    .orElseThrow(() -> new CustomException(ErrorType.NOT_FOUND_MEMBER_ACCOUNT));
            memberAccount.setMoney(memberAccount.getMoney() + totalMoney);
            memberAccountRepository.save(memberAccount);
            return "success";
        }
        return "fail";
    }

    @Transactional
    public String putDayoff(Members member, PutDayoffRequest request){
        Long halleyId = request.getHalleyId();
        Members halley = membersRepository.findById(halleyId).
                orElseThrow(()->new CustomException(ErrorType.NOT_FOUND_MEMBER));
        HalleyGalley halleyGalley = halleyGalleyRepository.findHalleyGalleyByGalleyIdAndHalleyId(member, halley)
                .orElseThrow(()->new CustomException(ErrorType.NOT_FOUND_HALLEY_GALLEY));
        halleyGalley.updateGetRewardAt(LocalDate.now());
        halleyGalley.updateDayoff(halleyGalley.getDayoff()-1);
        halleyGalleyRepository.save(halleyGalley);

        MemberAccount memberAccount = memberAccountRepository.findMemberAccountById(member.getMemberAccountId().getId())
                .orElseThrow(() -> new CustomException(ErrorType.NOT_FOUND_MEMBER_ACCOUNT));
        memberAccount.setMoney(memberAccount.getMoney() + halleyGalley.getReward());
        memberAccountRepository.save(memberAccount);
        return "";
    }
}
