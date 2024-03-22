package org.ssafy.d210.halleyGalley.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ssafy.d210._common.exception.CustomException;
import org.ssafy.d210._common.exception.ErrorType;
import org.ssafy.d210.halleyGalley.dto.HalleyDto;
import org.ssafy.d210.halleyGalley.dto.request.PostGalleyRequest;
import org.ssafy.d210.halleyGalley.dto.request.PutGalleyResponseRequest;
import org.ssafy.d210.halleyGalley.dto.response.GetGalleyListResponse;
import org.ssafy.d210.halleyGalley.dto.response.GetHalleyListResponse;
import org.ssafy.d210.halleyGalley.dto.response.GetHalleyRequestListResponse;
import org.ssafy.d210.halleyGalley.dto.response.PutGalleyResponseResponse;
import org.ssafy.d210.halleyGalley.entity.HalleyGalley;
import org.ssafy.d210.halleyGalley.repository.HalleyGalleyRepository;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.repository.MembersRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HalleyGalleyService {
    private final HalleyGalleyRepository halleyGalleyRepository;
    private final MembersRepository membersRepository;

    @Transactional
    public String postGalleyRequest(Members member, PostGalleyRequest postGalleyRequest){
        Long galleyId = postGalleyRequest.getMemberId();
        if(!halleyGalleyRepository.existsHalleyGalleyByHalleyIdAndGalleyId(member, membersRepository.findById(galleyId).orElse(null))) {
            halleyGalleyRepository.save(HalleyGalley.builder()
                    .halleyId(member)
                    .galleyId(membersRepository.findById(postGalleyRequest.getMemberId()).orElse(null))
                    .reward(0)
                    .missionId(null)
                    .dayoff(0)
                    .isAccepted(false)
                    .build());
        }
        else{
            throw new CustomException(ErrorType.ALREADY_SEND_REQUEST);
        }
        return "";
    }

    @Transactional
    public PutGalleyResponseResponse putGalleyResponse(Members member, PutGalleyResponseRequest putGalleyResponseRequest){
        Long galleyId = putGalleyResponseRequest.getMemberId();
        Boolean isAccept = putGalleyResponseRequest.getIsAccept();

        HalleyGalley halleyGalley = halleyGalleyRepository.findHalleyGalleyByGalleyIdAndHalleyId(member, membersRepository.findById(galleyId).orElse(null));
        halleyGalley.updateIsAccepted(isAccept);
        halleyGalleyRepository.save(halleyGalley);

        return PutGalleyResponseResponse.of(isAccept);
    }

    public List<GetGalleyListResponse> getGalleyList(Members member){
        List<HalleyGalley> galleyList = halleyGalleyRepository.findByHalleyId(member);
        if(galleyList.isEmpty()){
            throw new CustomException(ErrorType.NOT_FOUND_GALLEY);
        }
        return galleyList.stream().map(GetGalleyListResponse::from).collect(Collectors.toList());
    }

    public GetHalleyListResponse getHalleyList(Members member){
        List<HalleyGalley> halleyList = halleyGalleyRepository.findByGalleyId(member);
        if(halleyList.isEmpty()){
            throw new CustomException(ErrorType.NOT_FOUND_HALLEY);
        }
        List<HalleyDto> halleyInfoList = halleyList.stream().map(HalleyDto::from).toList();

        return GetHalleyListResponse.from(member, halleyInfoList);
    }

    public GetHalleyRequestListResponse getHalleyRequestList(Members member){
        List<HalleyGalley> halleyRequestList = halleyGalleyRepository.findByHalleyIdAndIsAcceptedIsFalse(member);
        if(halleyRequestList.isEmpty()){
            throw new CustomException(ErrorType.NOT_FOUND_HALLEY_REQUEST_LIST);
        }
        List<HalleyDto> halleyInfoList = halleyRequestList.stream().map(HalleyDto::from).toList();

        return GetHalleyRequestListResponse.of(halleyInfoList);
    }

}
