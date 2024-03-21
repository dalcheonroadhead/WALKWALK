package org.ssafy.d210.halleyGalley.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ssafy.d210._common.exception.CustomException;
import org.ssafy.d210._common.exception.ErrorType;
import org.ssafy.d210.halleyGalley.dto.HalleyDto;
import org.ssafy.d210.halleyGalley.dto.response.GetGalleyListResponse;
import org.ssafy.d210.halleyGalley.dto.response.GetHalleyListResponse;
import org.ssafy.d210.halleyGalley.entity.HalleyGalley;
import org.ssafy.d210.halleyGalley.repository.HalleyGalleyRepository;
import org.ssafy.d210.members.entity.Members;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HalleyGalleyService {
    private final HalleyGalleyRepository halleyGalleyRepository;

    public List<GetGalleyListResponse> getGalleyList(Members member){
        List<HalleyGalley> galleyList =  halleyGalleyRepository.findByHalleyId(member);
        if(galleyList.isEmpty()){
            throw new CustomException(ErrorType.NOT_FOUND_GALLEY);
        }
        return galleyList.stream().map(GetGalleyListResponse::of).collect(Collectors.toList());
    }

    public GetHalleyListResponse getHalleyList(Members member){
        List<HalleyGalley> halleyList = halleyGalleyRepository.findByGalleyId(member);
        if(halleyList.isEmpty()){
            throw new CustomException(ErrorType.NOT_FOUND_HALLEY);
        }
        List<HalleyDto> halleyInfoList = halleyList.stream().map(HalleyDto::of).toList();

        return GetHalleyListResponse.from(member, halleyInfoList);
    }
}
