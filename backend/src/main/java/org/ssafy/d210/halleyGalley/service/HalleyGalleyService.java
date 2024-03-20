package org.ssafy.d210.halleyGalley.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ssafy.d210.halleyGalley.dto.response.GetGalleyListResponse;
import org.ssafy.d210.halleyGalley.repository.HalleyGalleyRepository;
import org.ssafy.d210.members.entity.Members;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HalleyGalleyService {
    private final HalleyGalleyRepository halleyGalleyRepository;

    public List<HalleyGalleyRepository.HalleyGalleyProjection> getGalleyList(Members member){
        List<HalleyGalleyRepository.HalleyGalleyProjection> galleyInfoDtoList =  halleyGalleyRepository.findHalleyGalleysByHalleyId(member);
        log.warn("이히히힣: " + galleyInfoDtoList.get(0).toString());
        return galleyInfoDtoList;
    }
}
